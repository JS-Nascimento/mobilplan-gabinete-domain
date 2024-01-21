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

public class Fundo implements Estrutural {
    private final TipoFundo tipoFundo;
    private final double valorVariavel;
    private String descricao;
    private String precificacao;
    private final double espessura;
    private double largura;
    private double altura;
    private double area;
    private  double metragemFita;
    private final PadraoDeFitagem padraoDeFitagem;
    private final List<MateriaPrima> materiaPrimas;

    public Fundo(TipoFundo tipoFundo, double espessura , double valorVariavel, PadraoDeFitagem padraoDeFitagem) {
        this.materiaPrimas = new ArrayList<>();
        this.tipoFundo = tipoFundo;
        this.espessura = espessura;
        this.valorVariavel = valorVariavel;
        this.padraoDeFitagem = padraoDeFitagem;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaFundo(this, dimensoes, this.espessura, this.valorVariavel, this.padraoDeFitagem);
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

    public void setDimensoes(double largura, double altura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.altura = altura;
        this.area = largura * altura;
        this.metragemFita = calcularMetragemFita(largura, altura, padraoDeFitagem);
        if(metragemFita > 0) {
            this.descricao = "Fundo: " + largura + "mm x " + altura + "mm x " + espessura
                    + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                    + NumberHelper.mmParaMetros(metragemFita) + "m x "
                    + FitaHelper.larguraDaFita(espessura) + "mm";
        }
        else {
            this.descricao = "Fundo " + tipoFundo + ": " + largura + " x " + altura + ", Espessura: " + espessura + " mm - (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)";
        }


    }

    public TipoFundo getTipoFundo() {
        return tipoFundo;
    }

    public  double getValorVariavel() {
        return valorVariavel;
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

    public double getEspessura() {
        return espessura;
    }

    @Override
    public PadraoDeFitagem getPadraoDeFitagem() {
        return padraoDeFitagem;
    }

    @Override
    public List<MateriaPrima> getMateriasPrima() {
        return materiaPrimas;
    }

}