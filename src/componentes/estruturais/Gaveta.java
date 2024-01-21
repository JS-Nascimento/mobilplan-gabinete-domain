package componentes.estruturais;


import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.List;

public class Gaveta implements Estrutural {
    private final List<Estrutural> componentesEstruturais;
    private final FolgasGavetas folgas;
    private final double altura;
    private double largura;
    private double profundidade;
    private final String descricao;
    private final double espessura;
    private double metragemFita;
    private final PadraoDeFitagem padraoDeFitagem;

    public Gaveta(Dimensoes dimensoesGabinete, FolgasGavetas folgas, double altura) {
        this.folgas = folgas;
        this.altura = altura;
        this.espessura =  folgas.espessuraCorpo();
        this.padraoDeFitagem = folgas.padraoDeFitagem();
        this.componentesEstruturais = new ArrayList<>();

        this.componentesEstruturais.add(new FundoGaveta(folgas, PadraoDeFitagem.NENHUM));
        this.componentesEstruturais.add(new LateralGaveta(folgas,
                new Dimensoes(0.0, altura, 0.0,this.espessura),
                padraoDeFitagem));
        this.componentesEstruturais.add(new LateralGaveta(folgas,
                new Dimensoes(0.0, altura, folgas.profundidadeGaveta(), this.espessura),
                padraoDeFitagem));
        this.componentesEstruturais.add(new ContraFrenteGaveta(altura, folgas));

        this.componentesEstruturais.add(new TraseiroGaveta(altura, folgas));

        this.descricao = "Gaveta com folga de " + folgas + "mm";
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        for (Estrutural componente : componentesEstruturais) {
            componente.aceitar(estrategia, dimensoes);
        }
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {

    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> materiaPrimas) {

    }

    public void setDimensoes(double altura, double largura, double profundidade, double espessura,
                             PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.profundidade = profundidade;
        //TODO: Refatorar para usar o padraoDeFitagem e metragemFita
    }

    public String listarComponentes() {

        StringBuilder descricaoComponentes = new StringBuilder();
        for (Estrutural componente : componentesEstruturais) {
            descricaoComponentes.append(" --> ")
                    .append(componente.getDescricao())
                    .append("\n");
        }
        return descricaoComponentes.toString();
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
        return 0;
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

