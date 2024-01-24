package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.Folgas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;
import java.util.List;

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