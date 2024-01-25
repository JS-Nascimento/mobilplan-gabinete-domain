package componentes.fechamentos;

import static helpers.DescontosPadroes.verificaAlturaMinimaGaveta;
import static helpers.NumberHelper.roundDouble;

import componentes.AbstractComponenteFechamento;
import componentes.Dimensoes;
import componentes.Folgas;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import componentes.estruturais.CorpoGaveta;
import componentes.estruturais.TipoFundo;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.List;

public class Gavetas extends AbstractComponenteFechamento {

    private final Folgas folgas;
    private final FolgasGavetas folgasGavetas;
    private final int quantidadeGavetas;
    private final List<Double> alturasDasFrentes;
    private List<Double> alturaDeTodasAsFrentes;
    private final List<FrenteGaveta> frenteGavetas;
    private final List<CorpoGaveta> corpoGavetas;
    private final TipoFrente tipoFrente;

    public Gavetas(final TipoFrente tipoFrente,
                   final int quantidadeGavetas,
                   List<Double> alturasDasFrentes,
                   final PadraoDeFitagem padraoDeFitagem,
                   double espessura) {
        super(padraoDeFitagem);
        this.quantidadeGavetas = quantidadeGavetas;
        this.alturasDasFrentes = alturasDasFrentes;
        this.folgas = folgas();
        this.folgasGavetas = folgasGavetas();
        this.tipoFrente = tipoFrente;
        this.espessura = espessura;
        this.frenteGavetas = new ArrayList<>();
        this.corpoGavetas = new ArrayList<>();
        this.alturaDeTodasAsFrentes = new ArrayList<>();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {

        calcularAlturasDasFrentes(dimensoes);

        estrategia.aplicarParaGaveta(this, dimensoes, this.padraoDeFitagem);

        for (FrenteGaveta frenteGaveta : frenteGavetas) {
            frenteGaveta.aceitar(estrategia, dimensoes);
        }
        for (CorpoGaveta corpoGaveta : corpoGavetas) {
            corpoGaveta.adicionarAcabamentos(this.materiaPrimas());
            corpoGaveta.aceitar(estrategia, dimensoes);
        }
    }

    private void validarAlturaFrentes(final double alturaGabinete) {

        var alturaTotalFrentes = alturasDasFrentes.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        alturaTotalFrentes += (folgas().entreComponentes() * (quantidadeGavetas - 1));
        alturaTotalFrentes += folgas().superior() + folgas().inferior();

        if (alturaTotalFrentes > alturaGabinete){
            throw new IllegalArgumentException("A soma das alturas das gavetas excede a altura do gabinete");
        }
        if (!verificaAlturaMinimaGaveta(alturaGabinete - alturaTotalFrentes)){
            throw new IllegalArgumentException("Altura minima da gaveta não atingida.");
        }

    }

    private void calcularAlturasDasFrentes(Dimensoes dimensoes) {

        if (alturasDasFrentes.size() >= quantidadeGavetas) {
            throw new IllegalArgumentException("A quantidade de frentes informada é diferente da quantidade de alturas informadas");
        }

        validarAlturaFrentes(dimensoes.getAltura());

        var totalFolgas =(folgas().entreComponentes() * (quantidadeGavetas - 1)) + folgas().superior() + folgas().inferior();
        var alturaLivreGabinete = dimensoes.getAltura() - totalFolgas;

        if (quantidadeGavetas == 1) {
            this.alturaDeTodasAsFrentes = List.of(alturaLivreGabinete);
            return ;
        }

        var alturaTotalFrentesGaveta = alturasDasFrentes.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        var gavetasRestantes = quantidadeGavetas - alturasDasFrentes.size();

        var alturaRestante = alturaLivreGabinete - alturaTotalFrentesGaveta;

        var alturaDasFrentesRestantes = roundDouble(( alturaRestante / gavetasRestantes),1);

        var alturaFrentesCalculadas = new ArrayList<>(alturasDasFrentes);

        for (int i = 0; i < gavetasRestantes; i++) {
            alturaFrentesCalculadas.add(alturaDasFrentesRestantes);
        }
        this.alturaDeTodasAsFrentes = alturaFrentesCalculadas;
    }

    public Folgas folgas() {
        //TODO: Refatorar para Buscar configurações de folgas no banco de dados
        return new Folgas(3, 3, 3, 3, 3);
    }

    public TipoFrente tipoFrente() {
        return tipoFrente;
    }

    public int quantidadeGavetas() {
        return quantidadeGavetas;
    }

    public List<FrenteGaveta> frentes() {
        return frenteGavetas;
    }

    public List<CorpoGaveta> corpoGavetas() {
        return corpoGavetas;
    }

    public List<Double> alturaDeTodasAsFrentes() {
        return alturaDeTodasAsFrentes;
    }

    public FolgasGavetas folgasGavetas() {

        return new FolgasGavetas(TipoFundo.ENCAIXADO, 26, 350, 30, 15, 6, 10, PadraoDeFitagem.UMA_ALTURA);
    }

    public void adicionarFrenteGaveta(FrenteGaveta frenteGaveta) {
        this.frenteGavetas.add(frenteGaveta);
    }
    @Override
    public String getDescricao() {
        this.descricao = "";
        var gavetas = this.corpoGavetas;

        frenteGavetas.forEach(
              frente -> this.descricao += frente.getDescricao() + "\n"
                      + "Componentes: \n"
                      + gavetas.get(frenteGavetas.indexOf(frente)).listarComponentes() + "\n"
        );
        return descricao;
    }
    public void adicionarCorpoGaveta(CorpoGaveta corpoGaveta) {
        this.corpoGavetas.add(corpoGaveta);
    }
}
