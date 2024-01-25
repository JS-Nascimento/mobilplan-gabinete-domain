package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class PrateleiraInterna extends AbstractComponente {

    private final TipoPrateleira tipoPrateleira;
    private final Folgas folgas;

    public PrateleiraInterna(TipoPrateleira tipoPrateleira, double espessura, Folgas folgas, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.tipoPrateleira = tipoPrateleira;
        this.folgas = folgas;
        this.espessura = espessura;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaPrateleiraInterna(this,
                dimensoes,
                this.padraoDeFitagem,
                this.tipoPrateleira,
                this.folgas);
    }

    public void setDimensoes(double largura, double profundidade, double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.area = largura * profundidade;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem);
        this.descricao = "Prateleira "
                + this.tipoPrateleira.toString() ;
    }
}