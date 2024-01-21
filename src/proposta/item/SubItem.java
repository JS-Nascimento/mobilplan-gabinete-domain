package proposta.item;

import helpers.NumberHelper;

public class SubItem {

    private final String descricao;
    private final double quantidade;
    private final double valor;
    private final double total;

    public SubItem(String descricao, double quantidade, double valor, double total) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.total = total;
    }

    public String descricao() {
        return descricao;
    }

    public double quantidade() {
        return quantidade;
    }

    public double valor() {
        return valor;
    }

    public double total() {
        return total;
    }

    @Override
    public String toString() {
        return   descricao + ", qtd : " + quantidade +
                ", vl Unit. " + NumberHelper.formatNumber( valor, 2) +
                ", total=" + NumberHelper.formatCurrency(total) ;
    }
}