package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.Dimensoes;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;

public class FundoGaveta extends AbstractComponente {
    private final FolgasGavetas folgasGavetas;

    public FundoGaveta(FolgasGavetas folgasGavetas, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.folgasGavetas = folgasGavetas;
        this.espessura = folgasGavetas.espessuraFundo();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaFundoGaveta(this, dimensoes, this.espessura, this.padraoDeFitagem);
    }

    public void setDimensoes(double profundidade, double largura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.altura = profundidade;
        this.largura = largura;
        this.area = profundidade * largura;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem);
        this.descricao = "Fundo Gaveta: " + profundidade + "mm x " + largura + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    public FolgasGavetas getFolgasGavetas() {
        return folgasGavetas;
    }
}