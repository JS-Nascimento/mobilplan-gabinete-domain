package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

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
        this.altura = profundidadeEspecifica;
        this.area = largura * profundidadeEspecifica;
        this.metragemFita = calcularMetragemFita(largura, profundidadeEspecifica, padraoDeFitagem);
        this.descricao = "Travessa Horizontal";
    }
}