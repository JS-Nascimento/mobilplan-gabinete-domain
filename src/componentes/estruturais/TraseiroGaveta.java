package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.Dimensoes;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;

public class TraseiroGaveta extends AbstractComponente {
    private final FolgasGavetas folgasGavetas;

    public TraseiroGaveta(double altura, FolgasGavetas folgasGavetas) {
        super(folgasGavetas.padraoDeFitagem());
        this.altura = altura;
        this.folgasGavetas = folgasGavetas;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaTraseiroGaveta(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double altura, double largura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.altura = altura;
        this.profundidade = largura;
        this.area = altura * largura;
        this.metragemFita = calcularMetragemFita(largura, altura, padraoDeFitagem);
        this.descricao = "Traseiro Gaveta: " + altura + "mm x " + largura + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    public FolgasGavetas getFolgasGavetas() {
        return folgasGavetas;
    }
}