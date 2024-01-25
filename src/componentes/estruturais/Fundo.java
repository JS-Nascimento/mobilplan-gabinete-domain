package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

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
            this.descricao = "Fundo " + tipoFundo;
    }

    public TipoFundo getTipoFundo() {
        return tipoFundo;
    }

    public  double getValorVariavel() {
        return valorVariavel;
    }

}