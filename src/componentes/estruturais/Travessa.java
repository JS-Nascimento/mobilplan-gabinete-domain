package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import estrategias.EstrategiaDePrecificacao;
import helpers.FitaHelper;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.NumberHelper;
import java.util.List;

public class Travessa implements Estrutural {
    private double largura;
    private final double profundidadeEspecifica;
    private String descricao;

    private  double metragemFita;
    private final PadraoDeFitagem padraoDeFitagem;

    private double area;

    public Travessa(double profundidadeEspecifica, PadraoDeFitagem padraoDeFitagem) {
        this.profundidadeEspecifica = profundidadeEspecifica;
        this.padraoDeFitagem = padraoDeFitagem;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaTravessa(this, dimensoes, this.padraoDeFitagem);
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {

    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> materiaPrimas) {

    }

    public void setDimensoes(double largura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.area = largura * profundidadeEspecifica;
        this.metragemFita = calcularMetragemFita(largura, profundidadeEspecifica, padraoDeFitagem);
        this.descricao = "Travessa: " + largura + "mm x " + profundidadeEspecifica + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
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
        return 0;
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