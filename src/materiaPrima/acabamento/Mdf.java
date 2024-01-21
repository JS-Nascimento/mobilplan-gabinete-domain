package materiaPrima.acabamento;

import materiaPrima.TipoPrecificacao;

public class Mdf implements Acabamento{

    private final TipoAcabamento tipoAcabamento = TipoAcabamento.MELAMINICO;
    private final String descricao;
    private final String cor;
    private final CalculaPorLado calculaPorLado;
    private final DimensoesChapa dimensoesChapa;
    private final Unidade unidade = Unidade.METRO_QUADRADO ;
    private final double preco;

    public Mdf(
               String descricao,
               String cor,
               CalculaPorLado calculaPorLado,
               DimensoesChapa dimensoesChapa,
               double preco) {
        this.descricao = descricao;
        this.cor = cor;
        this.calculaPorLado = calculaPorLado;
        this.dimensoesChapa = dimensoesChapa;
        this.preco = preco;
    }
    @Override
    public TipoAcabamento getTipoAcabamento() {
        return tipoAcabamento;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
    @Override
    public String getCor() {
        return cor;
    }

    public CalculaPorLado getCalculaPorLado() {
        return calculaPorLado;
    }

    public DimensoesChapa getDimensoesChapa() {
        return dimensoesChapa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public double getPreco() {
        return preco;
    }
}
