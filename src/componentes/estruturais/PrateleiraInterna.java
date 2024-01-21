package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

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

public class PrateleiraInterna implements Estrutural {

    private final TipoPrateleira tipoPrateleira;
    private final Folgas folgas;
    private double largura;
    private double profundidade;
    private final double  espessura;
    private String descricao;
    private double area;
    private double metragemFita;
    private final PadraoDeFitagem padraoDeFitagem;

    public PrateleiraInterna(TipoPrateleira tipoPrateleira, double espessura, Folgas folgas, PadraoDeFitagem padraoDeFitagem) {
        this.tipoPrateleira = tipoPrateleira;
        this.folgas = folgas;
        this.espessura = espessura;
        this.padraoDeFitagem = padraoDeFitagem;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaPrateleiraInterna(this,
                dimensoes,
                this.padraoDeFitagem,
                this.tipoPrateleira,
                this.folgas);
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {

    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> novosAcabamentos) {

    }

    public void setDimensoes(double largura, double profundidade, double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.area = largura * profundidade;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem);
        this.descricao = "Prateleira "
                + this.tipoPrateleira.toString() + ": "  + largura + " x " + profundidade + " ("
                + NumberHelper.mmSqParaMetrosSq(area) + " m²) - Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
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
}