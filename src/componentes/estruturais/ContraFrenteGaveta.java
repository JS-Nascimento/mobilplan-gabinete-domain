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

public class ContraFrenteGaveta implements Estrutural {
    private double altura;
    private double profundidade;
    private String descricao;
    private double metragemFita;
    private final FolgasGavetas folgasGavetas;
    private final PadraoDeFitagem padraoDeFitagem;

    private double area;

    public ContraFrenteGaveta(double altura, FolgasGavetas folgasGavetas) {
        this.altura = altura;
        this.padraoDeFitagem =folgasGavetas.padraoDeFitagem();
        this.folgasGavetas = folgasGavetas;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaContraFrenteGaveta(this, dimensoes, this.padraoDeFitagem);
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {

    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> materiaPrimas) {

    }


    public void setDimensoes(double altura, double largura,  double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.altura = altura;
        this.profundidade = largura;
        this.area = altura * largura;
        this.metragemFita = calcularMetragemFita(largura, altura, padraoDeFitagem);
        this.descricao = "Contra Frente Gaveta: " + altura + "mm x " + largura + "mm x " + espessura
                + "mm (" + NumberHelper.mmSqParaMetrosSq(area) + " m²)- Metragem Fita: "
                + NumberHelper.mmParaMetros(metragemFita) + "m x "
                + FitaHelper.larguraDaFita(espessura) + "mm";
    }

    public double getAltura() {
        return altura;
    }

    public FolgasGavetas getFolgasGavetas() {
        return folgasGavetas;
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