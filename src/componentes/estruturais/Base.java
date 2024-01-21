package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import helpers.FitaHelper;
import helpers.NumberHelper;
import java.util.ArrayList;
import java.util.List;

public class Base implements Estrutural {
    private double largura ;
    private double profundidade;
    private String descricao;
    private String precificacao;
    private double area;
    private double metragemFita;
    private final PadraoDeFitagem padraoDeFitagem;
    private final List<MateriaPrima> materiaPrimas;

    public Base(PadraoDeFitagem padraoDeFitagem) {
        this.materiaPrimas = new ArrayList<>();
        this.padraoDeFitagem = padraoDeFitagem;
    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> novosAcabamentos) {
        for (MateriaPrima materiaPrima : novosAcabamentos) {
            if (!materiaPrimas.contains(materiaPrima)) {
                materiaPrimas.add(materiaPrima);
            }
        }
    }
    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaBase(this, dimensoes, this.padraoDeFitagem);
    }
    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao estrategiaDePrecificacao) {
        estrategiaDePrecificacao.calcularPrecoEstrutural(this, materiaPrimas);
    }

    public void setDimensoes(double largura, double profundidade, double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.area = largura * profundidade;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem);
        this.descricao = "Base: " + largura + "mm x " + profundidade + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    public void setPrecificacao(String precificacao) {
        this.precificacao = precificacao;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getPrecificacao() {
        return precificacao;
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
        return materiaPrimas;
    }

    @Override
    public PadraoDeFitagem getPadraoDeFitagem() {
        return padraoDeFitagem;
    }

}