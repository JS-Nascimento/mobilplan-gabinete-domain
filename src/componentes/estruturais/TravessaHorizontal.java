package componentes.estruturais;

import static componentes.config.ConfigFabricacao.PROFUNDIDADE_TRAVESSA_HORIZONTAL;
import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class TravessaHorizontal extends AbstractComponente {
    private final double profundidadeEspecifica;

    public TravessaHorizontal(PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.profundidadeEspecifica = PROFUNDIDADE_TRAVESSA_HORIZONTAL;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaTravessa(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double largura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.espessura = espessura;
        this.altura = profundidadeEspecifica;
        this.area = largura * profundidadeEspecifica;
        this.metragemFita = calcularMetragemFita(largura, profundidadeEspecifica, padraoDeFitagem);
        this.descricao = "Travessa Horizontal";
    }
}