package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;
import java.util.ArrayList;
import java.util.List;

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
        this.descricao = "Base: " + largura + "mm x " + profundidade + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }
}