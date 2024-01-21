package componentes.fechamentos;

import static componentes.fechamentos.TipoPorta.PORTA_DUPLA;
import static helpers.FitaHelper.calcularMetragemFita;

import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Dimensoes;
import componentes.Fechamento;
import componentes.Folgas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;
import java.util.List;

public class Porta implements Fechamento {

    private final TipoPorta tipoPorta;
    private final Folgas folgas;
    private double largura;
    private double profundidade;
    private final double  espessura;
    private String descricao;
    private double area;
    private double metragemFita;
    private final PadraoDeFitagem padraoDeFitagem;

    public Porta(TipoPorta tipoPorta, double espessura, Folgas folgas, PadraoDeFitagem padraoDeFitagem) {
        this.tipoPorta = tipoPorta;
        this.folgas = folgas;
        this.espessura = espessura;
        this.padraoDeFitagem = padraoDeFitagem;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaPorta(this, dimensoes,this.padraoDeFitagem, this.tipoPorta, this.folgas);
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {

    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> materiaPrimas) {

    }

    public void setDimensoes(double largura, double profundidade, double espessura,  PadraoDeFitagem padraoDeFitagem) {

        int quantidadePortas = this.tipoPorta == PORTA_DUPLA ? 2 : 1;
        this.largura = largura;
        this.profundidade = profundidade;
        this.area = (largura * profundidade) * quantidadePortas;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem) * quantidadePortas;
        this.descricao = setDescricao(quantidadePortas, area, metragemFita, largura, profundidade);
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getPrecificacao() {
        return null;
    }

    @Override
    public void setPrecificacao(String precificacao) {

    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double getMetragemLinear() {
        return metragemFita;
    }

    @Override
    public List<MateriaPrima> getMateriasPrima() {
        return null;
    }

    @Override
    public PadraoDeFitagem getPadraoDeFitagem() {
        return padraoDeFitagem;
    }

    private String setDescricao(int quantidadePortas, double area, double metragemFita, double largura, double profundidade) {
        if (quantidadePortas == 2) {
            return "Porta Esquerda: " + largura + " x " + profundidade +
                    "\nPorta Direita: " + largura + " x " + profundidade +
                    "\nArea Total " + NumberHelper.mmSqParaMetrosSq(area) + " m² - " +
                    "\nMetragem Fita Total: " + NumberHelper.mmParaMetros(metragemFita) + "m x " +
                    FitaHelper.larguraDaFita(espessura) + "mm";
        } else {
            return this.tipoPorta.toString() + ": " + largura + " x " + profundidade +
                    " (" + NumberHelper.mmSqParaMetrosSq(area) + " m²) - Metragem Fita: " +
                    NumberHelper.mmParaMetros(metragemFita) + "m x " + FitaHelper.larguraDaFita(espessura) + "mm";
        }
    }


}
