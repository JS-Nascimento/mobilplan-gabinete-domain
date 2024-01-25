package materiaPrima.acessorios;

import componentes.config.DimensoesAcessorio;
import componentes.fechamentos.TipoPuxador;
import materiaPrima.acabamento.Unidade;

public class Puxador implements Acessorio {

    private final boolean perfil;
    private final TipoPuxador tipoPuxador;
    private final String descricao;
    private final String cor;
    private final Unidade unidade;
    private final DimensoesAcessorio dimensoesAcessorio;
    private final double preco;

    public Puxador(boolean perfil, TipoPuxador tipoPuxador, String descricao, String cor, DimensoesAcessorio dimensoesAcessorio, double preco) {
        this.perfil = perfil;
        this.tipoPuxador = tipoPuxador;
        this.descricao = descricao;
        this.cor = cor;
        this.unidade = perfil ? Unidade.METRO_LINEAR : Unidade.UNIDADE;
        this.dimensoesAcessorio = dimensoesAcessorio;
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

    public DimensoesAcessorio getDimensoesAcessorio() {
        return dimensoesAcessorio;
    }

    public TipoPuxador getTipoPuxador() {
        return tipoPuxador;
    }

    public boolean isPerfil() {
        return perfil;
    }
}
