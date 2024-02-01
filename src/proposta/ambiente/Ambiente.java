package proposta.ambiente;

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
        this.id = Math.abs(new Random().nextLong(16));
        this.descricao = descricao;
        this.detalhamento = detalhamento;
        this.observacao = observacao;
        this.itens = itens;
        this.calcularTotal();
    }

    private void calcularTotal() {
        this.total = this.itens.stream().mapToDouble(Item::total).sum();
    }

    @Override
    public String toString() {
        return "Ambiente{" + "id=" + id + ", descricao=" + descricao + ", detalhamento=" + detalhamento +
                ", observacao=" + observacao + ", total=" + total + '}';
    }


}
