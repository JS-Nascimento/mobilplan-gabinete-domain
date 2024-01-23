package componentes.fechamentos;

import static helpers.DescontosPadroes.verificaAlturaMinimaGaveta;
import static helpers.FitaHelper.calcularMetragemFita;
import static helpers.NumberHelper.roundDouble;

import componentes.AbstractComponenteFechamento;
import componentes.Dimensoes;
import componentes.Fechamento;
import componentes.Folgas;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import componentes.estruturais.CorpoGaveta;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;
import java.util.ArrayList;
import java.util.List;

public class Gaveta extends AbstractComponenteFechamento {

    private final Folgas folgas;
    private final FolgasGavetas folgasGavetas;
    private final int quantidadeGavetas;
    private final List<Double> alturasDasFrentes;
    private final List<Fechamento> frenteGavetas;
    private final List<CorpoGaveta> corpoGavetas;
    private Gaveteiro gaveteiro;
    private final TipoFrente tipoFrente;
    private Dimensoes dimensoes;

    public Gaveta(final Folgas folgas,
                  final TipoFrente tipoFrente,
                  final int quantidadeGavetas,
                  List<Double> alturasDasFrentes,
                  final FolgasGavetas folgasGavetas,
                  final PadraoDeFitagem padraoDeFitagem,
                  double espessura) {
        super(padraoDeFitagem);
        this.folgas = folgas;
        this.quantidadeGavetas = quantidadeGavetas;
        this.alturasDasFrentes = alturasDasFrentes;
        this.folgasGavetas = folgasGavetas;
        this.tipoFrente = tipoFrente;
        this.espessura = espessura;
        this.frenteGavetas = new ArrayList<>();
        this.corpoGavetas = new ArrayList<>();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {

        this.gaveteiro = new Gaveteiro(quantidadeGavetas,
                folgasGavetas,
                calcularAlturasDasFrentes(alturasDasFrentes, dimensoes.getAltura()));

        estrategia.aplicarParaFrenteGaveta(this, dimensoes, this.padraoDeFitagem);
        gaveteiro.aceitar(estrategia, dimensoes);


    }

    private void validarAlturaFrentes(final double alturaGabinete) {

        var alturaTotalFrentes = alturasDasFrentes.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        alturaTotalFrentes += (folgas.entreComponentes() * (quantidadeGavetas - 1));
        alturaTotalFrentes += folgas.superior() + folgas.inferior();

        if (alturaTotalFrentes > alturaGabinete){
            throw new IllegalArgumentException("A soma das alturas das gavetas excede a altura do gabinete");
        }
        if (!verificaAlturaMinimaGaveta(alturaGabinete - alturaTotalFrentes)){
            throw new IllegalArgumentException("Altura minima da gaveta não atingida.");
        }

    }

    private List<Double> calcularAlturasDasFrentes(final List<Double> alturasDasFrentes, final double alturaGabinete) {

        if (alturasDasFrentes.size() >= quantidadeGavetas) {
            throw new IllegalArgumentException("A quantidade de frentes informada é diferente da quantidade de alturas informadas");
        }

        validarAlturaFrentes(alturaGabinete);

        var totalFolgas =(folgas.entreComponentes() * (quantidadeGavetas - 1)) + folgas.superior() + folgas.inferior();
        var alturaLivreGabinete = alturaGabinete - totalFolgas;

        if (quantidadeGavetas == 1) {
            return List.of(alturaLivreGabinete);
        }

        var alturaTotalFrentesGaveta = alturasDasFrentes.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        var gavetasRestantes = quantidadeGavetas - alturasDasFrentes.size();

        var alturaRestante = alturaLivreGabinete - alturaTotalFrentesGaveta;

        var alturaDasFrentesRestantes = roundDouble(( alturaRestante / gavetasRestantes),1);

        var alturaFrentesCalculadas = new ArrayList<Double>();

        alturaFrentesCalculadas.addAll(alturasDasFrentes);

        for (int i = 0; i < gavetasRestantes; i++) {
            alturaFrentesCalculadas.add(alturaDasFrentesRestantes);
        }
        return alturaFrentesCalculadas;
    }
    public void setDimensoes(double largura, double profundidade, double espessura,  PadraoDeFitagem padraoDeFitagem) {

        this.descricaoCurta = "com " + quantidadeGavetas + " gaveta(s)";
        this.largura = largura;
        this.profundidade = profundidade;
        this.area = (largura * profundidade) ;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem) ;
        this.descricao = setDescricao(area, metragemFita, largura, profundidade, espessura);

        //descricoesFrentes.add(descricao);
    }
    private String setDescricao(double area, double metragemFita, double largura, double profundidade, double espessura) {
        return "Frente de Gaveta: "+ tipoFrente + " - " + largura + "mm x " + profundidade + "mm x " + espessura +
                "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²) - Metragem Fita: " +
                NumberHelper.mmParaMetros(metragemFita) + "m x " + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    public Folgas folgas() {
        return folgas;
    }

    public TipoFrente tipoFrente() {
        return tipoFrente;
    }

    public int quantidadeGavetas() {
        return quantidadeGavetas;
    }

    public void adicionarFrentes(List<Fechamento> frentes) {
        this.frenteGavetas.clear();
        this.frenteGavetas.addAll(frentes);
    }
    public List<Fechamento> frentes() {
        return frenteGavetas;
    }
}
