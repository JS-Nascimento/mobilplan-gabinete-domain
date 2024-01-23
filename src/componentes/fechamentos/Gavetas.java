package componentes.fechamentos;

import static helpers.DescontosPadroes.verificaAlturaMinimaGaveta;
import static helpers.FitaHelper.calcularMetragemFita;
import static helpers.NumberHelper.roundDouble;

import componentes.AbstractComponenteFechamento;
import componentes.Fechamento;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;
import componentes.Dimensoes;
import componentes.Folgas;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import java.util.ArrayList;
import java.util.List;

public class Gavetas extends AbstractComponenteFechamento {

    private final Folgas folgas;
    private final FolgasGavetas folgasGavetas;
    private final int quantidadeDeFrentes;
    private final List<Double> alturasDasFrentes;
    private final List<Fechamento> frentes ;
    private List<Double> alturasFinalDasFrentes;
    private Gaveteiro gaveteiro;
    private final TipoFrente tipoFrente;
    private Dimensoes dimensoes;
    private final List<String> descricoesFrentes = new ArrayList<>();

    public Gavetas(final Folgas folgas,
                   final TipoFrente tipoFrente,
                   final int quantidadeDeFrentes,
                   List<Double> alturasDasFrentes,
                   final FolgasGavetas folgasGavetas,
                   final PadraoDeFitagem padraoDeFitagem,
                   double espessura) {
        super(padraoDeFitagem);
        this.folgas = folgas;
        this.quantidadeDeFrentes = quantidadeDeFrentes;
        this.alturasDasFrentes = alturasDasFrentes;
        this.folgasGavetas = folgasGavetas;
        this.tipoFrente = tipoFrente;
        this.espessura = espessura;
        this.frentes = new ArrayList<>();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        this.alturasFinalDasFrentes = calcularAlturasDasFrentes(alturasDasFrentes, dimensoes.getAltura());
        this.gaveteiro = new Gaveteiro(quantidadeDeFrentes, folgasGavetas, alturasFinalDasFrentes);

        //estrategia.aplicarParaFrenteGaveta(this, dimensoes, this.padraoDeFitagem);
        gaveteiro.aceitar(estrategia, dimensoes);
    }

    public void setDimensoes(double largura, double profundidade, double espessura,  PadraoDeFitagem padraoDeFitagem) {

        this.descricaoCurta = "com " + quantidadeDeFrentes + " gaveta(s)";
        this.largura = largura;
        this.profundidade = profundidade;
        this.area = (largura * profundidade) ;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem) ;
        this.descricao = setDescricao(area, metragemFita, largura, profundidade, espessura);

        descricoesFrentes.add(descricao);
    }

    private String setDescricao(double area, double metragemFita, double largura, double profundidade, double espessura) {
        return "Frente de Gaveta: "+ tipoFrente + " - " + largura + "mm x " + profundidade + "mm x " + espessura +
                "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²) - Metragem Fita: " +
                NumberHelper.mmParaMetros(metragemFita) + "m x " + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    public List<String> listarFrentes() {
        return descricoesFrentes;
    }
    @Override
    public String getDescricao() {
        this.descricao = "";
        var gavetas = this.gaveteiro.getGavetas();

        descricoesFrentes.forEach(
                frente -> this.descricao += frente + "\n"
                        + "Componentes: \n"
                        + gavetas.get(descricoesFrentes.indexOf(frente)).listarComponentes() + "\n"
        );
        return descricao;
    }

    public Folgas getFolgas() {
        return folgas;
    }

    public FolgasGavetas getFolgasGavetas() {
        return folgasGavetas;
    }

    public int getQuantidadeDeFrentes() {
        return quantidadeDeFrentes;
    }

    public List<Double> getAlturasDasFrentes() {
        return alturasFinalDasFrentes;
    }

    public TipoFrente getTipoFrente() {
        return tipoFrente;
    }

    public List<String> getDescricoesFrentes() {
        return descricoesFrentes;
    }

    private String setDescricao(double area, double metragemFita, double largura, double profundidade) {

        return this.descricao = "Frente de Gaveta: " + largura + " x " + profundidade +
                " (" + NumberHelper.mmSqParaMetrosSq(area) + " m²) - Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x " + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    private void validarAlturaFrentes(final double alturaGabinete) {

        var alturaTotalFrentes = alturasDasFrentes.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        alturaTotalFrentes += (folgas.entreComponentes() * (quantidadeDeFrentes - 1));
        alturaTotalFrentes += folgas.superior() + folgas.inferior();

        if (alturaTotalFrentes > alturaGabinete){
            throw new IllegalArgumentException("A soma das alturas das gavetas excede a altura do gabinete");
        }
        if (!verificaAlturaMinimaGaveta(alturaGabinete - alturaTotalFrentes)){
            throw new IllegalArgumentException("Altura minima da gaveta não atingida.");
        }

    }

    private List<Double> calcularAlturasDasFrentes(final List<Double> alturasDasFrentes, final double alturaGabinete) {

        validarAlturaFrentes(alturaGabinete);

        var totalFolgas =(folgas.entreComponentes() * (quantidadeDeFrentes - 1)) + folgas.superior() + folgas.inferior();
        var alturaLivreGabinete = alturaGabinete - totalFolgas;

        var alturaTotalFrentes = alturasDasFrentes.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        var frentesRestantes = quantidadeDeFrentes - alturasDasFrentes.size();

        var alturaRestante = alturaLivreGabinete - alturaTotalFrentes;

        var alturaDasFrentesRestantes = roundDouble(( alturaRestante / frentesRestantes),1);

        var alturaFrentesCalculadas = new ArrayList<Double>();

        alturaFrentesCalculadas.addAll(alturasDasFrentes);

        for (int i = 0; i < frentesRestantes; i++) {
            alturaFrentesCalculadas.add(alturaDasFrentesRestantes);
        }
        return alturaFrentesCalculadas;
    }

    public Gaveteiro gaveteiro() {
        return gaveteiro;
    }
}