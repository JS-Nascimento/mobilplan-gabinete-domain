package proposta.ambiente;

import helpers.NumberHelper;
import java.util.List;
import java.util.Random;
import proposta.item.Item;

public class Ambiente {

    private long id;
    private String descricao;
    private String detalhamento;
    private String observacao;
    private List<Item> itens;
    private double total;

    public Ambiente(String descricao, String detalhamento, String observacao, List<Item> itens) {
        this.id = Math.abs(new Random().nextLong(64));
        this.descricao = descricao;
        this.detalhamento = detalhamento;
        this.observacao = observacao;
        this.itens = itens;
        this.calcularTotal();
    }

    private void calcularTotal() {
        this.total = this.itens.stream().mapToDouble(Item::total).sum();
    }

    public double total() {
        return total;
    }

    public String descricao() {
        return descricao;
    }

    public long id() {
        return id;
    }

    @Override
    public String toString() {

        var sb = new StringBuilder();

        sb.append("Ambiente : \n");
        sb.append("# ").append(id).append("\n");
        sb.append("Descrição : ").append(descricao).append("\n");
        sb.append("Detalhamento : ").append(detalhamento).append("\n");
        sb.append("--------------------------------------------------------------\n");
        sb.append("Itens : \n");
        itens.forEach(item -> {
            sb.append(item.gabinete().descricao()).append(" : ").append(NumberHelper.formatCurrency(item.total())).append("\n");
        });
        sb.append("--------------------------------------------------------------\n");
        sb.append("Total : ").append(NumberHelper.formatCurrency(total)).append("\n");
        sb.append("Observação : ").append(observacao).append("\n");

        return sb.toString();
    }


}
