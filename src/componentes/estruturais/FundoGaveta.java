package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import estrategias.EstrategiaDePrecificacao;
import helpers.FitaHelper;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.NumberHelper;
import java.util.List;

public class FundoGaveta implements Estrutural {
    private double largura;
    private double altura;
    private String descricao;
    private final double espessura;
    private final PadraoDeFitagem padraoDeFitagem;

    private final FolgasGavetas folgasGavetas;
    private double area;
    private double metragemFita;

    public FundoGaveta(FolgasGavetas folgasGavetas, PadraoDeFitagem padraoDeFitagem) {
        this.folgasGavetas = folgasGavetas;
        this.espessura = folgasGavetas.espessuraFundo();
        this.padraoDeFitagem = padraoDeFitagem;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaFundoGaveta(this, dimensoes, this.espessura, this.padraoDeFitagem);
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {

    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> materiaPrimas) {

    }

    public void setDimensoes(double profundidade, double largura,  double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.altura = profundidade;
        this.largura = largura;
        this.area = profundidade * largura;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem);
        this.descricao = "Fundo Gaveta: " + profundidade + "mm x " + largura + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    public FolgasGavetas getFolgasGavetas() {
        return folgasGavetas;
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

    public double getEspessura() {
        return espessura;
    }
    @Override
    public PadraoDeFitagem getPadraoDeFitagem() {
        return padraoDeFitagem;
    }
}