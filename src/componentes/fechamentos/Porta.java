package componentes.fechamentos;

import static componentes.fechamentos.TipoPorta.PORTA_DUPLA;
import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponenteFechamento;
import componentes.Dimensoes;
import componentes.Folgas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;

public class Porta extends AbstractComponenteFechamento {

    private final TipoPorta tipoPorta;
    private final Folgas folgas;
    private int quantidadePortas;

    public Porta(TipoPorta tipoPorta, double espessura, Folgas folgas, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.tipoPorta = tipoPorta;
        this.folgas = folgas;
        this.espessura = espessura;

    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaPorta(this, dimensoes, this.padraoDeFitagem, this.tipoPorta, this.folgas);
    }

    public void setDimensoes(double largura, double profundidade, double espessura, PadraoDeFitagem padraoDeFitagem) {

        this.quantidadePortas = this.tipoPorta == PORTA_DUPLA ? 2 : 1;
        this.descricaoCurta = this.tipoPorta == PORTA_DUPLA ? "Porta Dupla" : "Porta Simples";
        this.largura = largura;
        this.profundidade = profundidade;
        this.espessura = espessura;
        this.area = (largura * profundidade) * quantidadePortas;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem) * quantidadePortas;
        this.descricao = setDescricao();
    }

    private String setDescricao() {
        if (quantidadePortas == 2) {
            return "Portas : " + quantidadePortas + " und. x " + largura + "mm x " + profundidade + "mm x " +
                    espessura + "mm (cada) - (" +
                    NumberHelper.mmSqParaMetrosSq(area) + " m²) - " +
                    "Metragem Fita : " + NumberHelper.mmParaMetros(metragemFita) + "m x " +
                    FitaHelper.larguraDaFita(espessura) + "mm";
        } else {
            return this.tipoPorta.toString() + ": " + largura + " x " + profundidade +
                    " (" + NumberHelper.mmSqParaMetrosSq(area) + " m²) - Metragem Fita: " +
                    NumberHelper.mmParaMetros(metragemFita) + "m x " + FitaHelper.larguraDaFita(espessura) + "mm";
        }
    }

}
