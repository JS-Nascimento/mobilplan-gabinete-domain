package componentes.estruturais;

import static componentes.config.ConfigFabricacao.PADRAO_ACABAMENTO_BASE;
import static helpers.FitaHelper.calcularMetragemFita;
import static java.util.Objects.isNull;

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

    public void setDimensoes(double largura, double altura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.altura = altura;
        this.espessura = espessura;
        this.area = largura * altura;
        this.metragemFita = calcularMetragemFita(largura, altura, padraoDeFitagem);
        this.descricao = "Base";
    }
}