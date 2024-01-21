package materiaPrima.acabamento;

import materiaPrima.TipoPrecificacao;

public class FitaDeBorda implements Acabamento{

    private final TipoPrecificacao precificacao = TipoPrecificacao.ML;
    private final TipoAcabamento tipoAcabamento = TipoAcabamento.FITA_DE_BORDA;
    private final String descricao;
    private final String cor;
    private final double largura;
    private final Unidade unidade = Unidade.METRO_LINEAR ;
    private final double preco;

    public FitaDeBorda(
                String descricao,
                String cor,
                double largura,
                double preco) {
        this.descricao = descricao;
        this.cor = cor;
        this.largura = largura;
        this.preco = preco;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public Unidade getUnidade() {
        return unidade;
    }

    public double getLargura() {
        return largura;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public TipoAcabamento getTipoAcabamento() {
        return tipoAcabamento;
    }

    @Override
    public String getCor() {
        return cor;
    }
}
