package proposta.item;

import componentes.Gabinete;
import helpers.NumberHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import materiaPrima.acabamento.Unidade;
import precificacao.Precificar;

public class Item {

    private final Gabinete gabinete;
    private final List<MateriaPrima> materiaPrimas;
    private final List<SubItem> subItens;
    private double total;

    public Item(Gabinete gabinete) {
        this.gabinete = gabinete;
        this.materiaPrimas = new ArrayList<>();
        this.subItens = new ArrayList<>();
        this.calcularTotal();
    }

    public void calcularTotal() {

        this.total = 0.0;
        gabinete.aplicarAcabamentos();

        gabinete.ferragens().forEach((ferragem, quantidade) -> {
            var precificar = new Precificar(ferragem, quantidade);
            var descricao = ferragem.getDescricao() + " - "
                    + ferragem.getCor();
            var subItem = new SubItem(descricao, quantidade, ferragem.getPreco(), precificar.getValorTotal());
            this.subItens.add(subItem);
        });

        gabinete.acabamentos().forEach((acabamento, quantidade) -> {

            var quantidadeConvertida = acabamento.getUnidade() ==
                    Unidade.METRO_LINEAR ? NumberHelper.mmParaMetros(quantidade)
                    : NumberHelper.mmSqParaMetrosSq(quantidade);

            var precificar = new Precificar(acabamento, quantidadeConvertida);
            var descricao = acabamento.getDescricao() + " - " + acabamento.getCor();


            var subItem = new SubItem(descricao, quantidadeConvertida, acabamento.getPreco(), precificar.getValorTotal());
            this.subItens.add(subItem);
        });

        this.subItens.forEach(subItem -> {
            this.total += subItem.total();
        });

    }

    @Override
    public String toString() {
        StringBuilder descricao = new StringBuilder();

        descricao.append(gabinete.descricao()).append("\n");
        descricao.append("====================================================================").append("\n");
        descricao.append("Componentes: ").append("\n");
        gabinete.caixa().componentes().forEach(componente -> {
            descricao.append(componente.getDescricao()).append("\n");
        });
        descricao.append("====================================================================").append("\n");
        descricao.append("Material: ").append("\n");
        subItens.sort(Comparator.comparing(SubItem::descricao));
        subItens.forEach(subItem -> {
            descricao.append(subItem.toString()).append("\n");
        });

        descricao.append("Total: ").append(NumberHelper.formatCurrency(total));
        return descricao.toString();
    }
}
