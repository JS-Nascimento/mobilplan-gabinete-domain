package componentes.estruturais;

import static componentes.config.ConfigFabricacao.DESCONTO_PROFUNDIDADE_DIVISORIA_HORIZONTAL_INTERNA;
import static componentes.config.ConfigFabricacao.DESCONTO_PROFUNDIDADE_FIXA;
import static componentes.config.ConfigFabricacao.DESCONTO_PROFUNDIDADE_MOVEL;
import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class PrateleiraInterna extends AbstractComponente {

    private final TipoPrateleira tipoPrateleira;
    private double descontoProfundidade;

    public PrateleiraInterna(TipoPrateleira tipoPrateleira, double espessura, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.tipoPrateleira = tipoPrateleira;
        this.descontoProfundidade = verificarDescontoProfundidade(tipoPrateleira);
        this.espessura = espessura;
    }

    private double verificarDescontoProfundidade(TipoPrateleira tipoPrateleira) {
        return switch (tipoPrateleira) {
            case MOVEL -> DESCONTO_PROFUNDIDADE_MOVEL;
            case FIXA -> DESCONTO_PROFUNDIDADE_FIXA;
            case DIVISORIA_HORIZONTAL -> DESCONTO_PROFUNDIDADE_DIVISORIA_HORIZONTAL_INTERNA;
        };
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaPrateleiraInterna(this,
                dimensoes);
    }

    public void setDimensoes(double largura, double altura, double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.altura = altura;
        this.espessura = espessura;
        this.area = largura * altura;
        this.metragemFita = calcularMetragemFita(largura, altura, padraoDeFitagem);
        this.descricao = "Prateleira "
                + this.tipoPrateleira.toString() ;
    }

    public double descontoProfundidade() {
        return descontoProfundidade;
    }
}