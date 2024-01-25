package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class Base extends AbstractComponente {
    public Base(PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaBase(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double largura, double profundidade, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.espessura = espessura;
        this.area = largura * profundidade;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem);
        this.descricao = "Base";
    }
}