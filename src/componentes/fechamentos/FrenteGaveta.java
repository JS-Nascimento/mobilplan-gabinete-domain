package componentes.fechamentos;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponenteFechamento;
import componentes.Dimensoes;
import componentes.Folgas;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;

public class FrenteGaveta extends AbstractComponenteFechamento {

    private final TipoFrente tipoFrente;
    private final Folgas folgas;
    private final FolgasGavetas folgasGavetas;

    public FrenteGaveta(double altura, double espessura, TipoFrente tipoFrente, Folgas folgas,
                        FolgasGavetas folgasGavetas, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.altura = altura;
        this.espessura = espessura;
        this.tipoFrente = tipoFrente;
        this.folgas = folgas;
        this.folgasGavetas = folgasGavetas;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaFrenteGaveta(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double largura, double profundidade, double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.area = (largura * profundidade) ;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem) ;
        this.descricao = setDescricao(area, metragemFita, largura, profundidade, espessura);
    }

    private String setDescricao(double area, double metragemFita, double largura, double profundidade, double espessura) {
        return "Frente de Gaveta: "+ tipoFrente + " - " + largura + "mm x " + profundidade + "mm x " + espessura +
                "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²) - Metragem Fita: " +
                NumberHelper.mmParaMetros(metragemFita) + "m x " + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    public TipoFrente tipoFrente() {
        return tipoFrente;
    }

    public Folgas folgas() {
        return folgas;
    }
}
