package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class Lateral extends AbstractComponente {
    public Lateral(PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
    }
    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaLateral(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double altura, double profundidade, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.altura = altura;
        this.profundidade = profundidade;
        this.area = altura * profundidade;
        this.espessura = espessura;
        this.metragemFita = calcularMetragemFita(altura, profundidade, padraoDeFitagem);
        this.descricao = "Lateral";
    }
}