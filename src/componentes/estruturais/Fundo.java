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

public class Fundo extends AbstractComponente {
    private final TipoFundo tipoFundo;
    private final double valorVariavel;

    public Fundo(TipoFundo tipoFundo, double espessura , double valorVariavel, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.tipoFundo = tipoFundo;
        this.espessura = espessura;
        this.valorVariavel = valorVariavel;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaFundo(this, dimensoes, this.espessura, this.valorVariavel, this.padraoDeFitagem);
    }

    public void setDimensoes(double largura, double altura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.altura = altura;
        this.espessura = espessura;
        this.area = largura * altura;
        this.metragemFita = calcularMetragemFita(largura, altura, padraoDeFitagem);
        if(metragemFita > 0) {
            this.descricao = "Fundo: " + largura + "mm x " + altura + "mm x " + espessura
                    + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                    + NumberHelper.mmParaMetros(metragemFita) + "m x "
                    + FitaHelper.larguraDaFita(espessura) + "mm";
        }
        else {
            this.descricao = "Fundo " + tipoFundo + ": " + largura + " x " + altura + ", Espessura: " + espessura + " mm - (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)";
        }


    }

    public TipoFundo getTipoFundo() {
        return tipoFundo;
    }

    public  double getValorVariavel() {
        return valorVariavel;
    }

}