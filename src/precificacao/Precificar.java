package precificacao;

import static helpers.NumberHelper.formatCurrency;
import static materiaPrima.TipoPrecificacao.M2;
import static materiaPrima.acabamento.Acabamento.precificacao;

import materiaPrima.TipoPrecificacao;
import materiaPrima.acabamento.Acabamento;
import helpers.NumberHelper;
import materiaPrima.MateriaPrima;
import materiaPrima.acessorios.Acessorio;
import materiaPrima.acessorios.Ferragem;
import materiaPrima.acessorios.Puxador;

public class Precificar {

    private final MateriaPrima materiaPrima;
    private String descricao;
    private final double quantidade;
    private double valorTotal;

    public Precificar(MateriaPrima materiaPrima, double quantidade) {
        this.materiaPrima = materiaPrima;
        this.quantidade = quantidade;
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        if (materiaPrima instanceof Acabamento acabamento) {

            switch (acabamento.precificacao) {
                case M2, ML, UNIDADE ->  this.valorTotal = acabamento.getPreco() * quantidade;

            }
        }

        if (materiaPrima instanceof Ferragem acessorio) {
            this.valorTotal = materiaPrima.getPreco() * quantidade;
        }

        if (materiaPrima instanceof Puxador acessorio) {
            this.valorTotal = materiaPrima.getPreco() * quantidade;
        }

    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

}
