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
import java.util.ArrayList;
import java.util.List;

public class Lateral implements Estrutural {
    private double altura;
    private double profundidade;
    private double espessura;
    private String descricao;
    private String precificacao;
    private double metragemFita;
    private final PadraoDeFitagem padraoDeFitagem;
    private final List<MateriaPrima> materiaPrimas;

    private double area;

    public Lateral(PadraoDeFitagem padraoDeFitagem) {
        this.materiaPrimas = new ArrayList<>();
        this.padraoDeFitagem = padraoDeFitagem;
    }
    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaLateral(this, dimensoes, this.padraoDeFitagem);
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao estrategiaDePrecificacao) {
        estrategiaDePrecificacao.calcularPrecoEstrutural(this, materiaPrimas);
    }
    @Override
    public void adicionarAcabamentos(List<MateriaPrima> materiaPrimas) {
        for (MateriaPrima materiaPrima : materiaPrimas) {
            if (!this.materiaPrimas.contains(materiaPrima)) {
                this.materiaPrimas.add(materiaPrima);
            }
        }
    }


    public void setDimensoes(double altura, double profundidade, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.altura = altura;
        this.profundidade = profundidade;
        this.area = altura * profundidade;
        this.espessura = espessura;
        this.metragemFita = calcularMetragemFita(altura, profundidade, padraoDeFitagem);
        this.descricao = "Lateral: " + altura + "mm x " + profundidade + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    @Override
    public List<MateriaPrima> getMateriasPrima() {
        return materiaPrimas;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getPrecificacao() {
        return precificacao;
    }

    public void setPrecificacao(String precificacao) {
        this.precificacao = precificacao;
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
    public PadraoDeFitagem getPadraoDeFitagem() {
        return padraoDeFitagem;
    }
}