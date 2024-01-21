package materiaPrima.acessorios;

import materiaPrima.acabamento.Unidade;

public class Ferragem implements Acessorio{

    private final String descricao;
    private final String cor;
    private final Unidade unidade ;
    private final double preco;

    public Ferragem(String descricao, String cor, Unidade unidade, double preco) {
        this.descricao = descricao;
        this.cor = cor;
        this.unidade = unidade;
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

    @Override
    public double getPreco() {
        return preco;
    }
    @Override
    public String getCor() {
        return cor;
    }
}
