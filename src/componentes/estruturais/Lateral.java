package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import estrategias.EstrategiaDePrecificacao;
import helpers.FitaHelper;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.NumberHelper;
import java.util.ArrayList;
import java.util.List;

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
        this.descricao = "Lateral: " + altura + "mm x " + profundidade + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }
}