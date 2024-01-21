package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.Dimensoes;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;

public class Travessa extends AbstractComponente {
    private final double profundidadeEspecifica;

    public Travessa(double profundidadeEspecifica, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.profundidadeEspecifica = profundidadeEspecifica;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaTravessa(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double largura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.espessura = espessura;
        this.area = largura * profundidadeEspecifica;
        this.metragemFita = calcularMetragemFita(largura, profundidadeEspecifica, padraoDeFitagem);
        this.descricao = "Travessa: " + largura + "mm x " + profundidadeEspecifica + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }
}