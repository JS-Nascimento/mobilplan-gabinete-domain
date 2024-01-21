package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import estrategias.EstrategiaDeConstrucao;
import estrategias.EstrategiaDePrecificacao;
import helpers.FitaHelper;
import helpers.NumberHelper;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import java.util.List;

public class LateralGaveta implements Estrutural {
    private double altura;
    private double profundidade;
    private final double espessura;
    private String descricao;
    private double metragemFita;
    private final PadraoDeFitagem padraoDeFitagem;

    private double area;

    public LateralGaveta(FolgasGavetas folgasGavetas, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {
        this.altura = dimensoes.getAltura();
        this.profundidade = folgasGavetas.profundidadeGaveta();
        this.espessura = folgasGavetas.espessuraCorpo();
        this.padraoDeFitagem = padraoDeFitagem;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaLateralGaveta(this, dimensoes, this.padraoDeFitagem);
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {

    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> materiaPrimas) {

    }


    public void setDimensoes(double altura, double profundidade,  double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.altura = altura;
        this.profundidade = profundidade;
        this.area = altura * profundidade;
        this.metragemFita = calcularMetragemFita(altura, profundidade, padraoDeFitagem);
        this.descricao = "Lateral Gaveta: " + altura + "mm x " + profundidade + "mm x " + espessura
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
        return metragemFita;
    }

    @Override
    public List<MateriaPrima> getMateriasPrima() {
        return null;
    }

    public double getProfundidade() {
        return profundidade;
    }

    public double getAltura() {
        return altura;
    }

    public double getEspessura() {
        return espessura;
    }

    @Override
    public PadraoDeFitagem getPadraoDeFitagem() {
        return padraoDeFitagem;
    }
}