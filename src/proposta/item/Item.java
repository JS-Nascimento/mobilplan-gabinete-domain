package proposta.item;

import componentes.Gabinete;
import componentes.fechamentos.Gaveteiro;
import componentes.fechamentos.Portas;
import helpers.NumberHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Unidade;
import precificacao.Precificar;

public class Item {

    private final Gabinete gabinete;
    private final List<MateriaPrima> materiaPrimas;
    private final List<TotalPorMaterial> totalPorMaterial;

    private final List<SubItem> subItems;
    private double total;

    public Item(Gabinete gabinete) {
        this.gabinete = gabinete;
        this.materiaPrimas = new ArrayList<>();
        this.totalPorMaterial = new ArrayList<>();
        this.subItems = new ArrayList<>();
        this.calcularTotal();
    }

    public List<SubItem> subItems() {
        return subItems;
    }

    private void adicionarSubItens() {
        gabinete.caixa().componentes().forEach(componente -> {
            var subItem = new SubItem(componente, componente.getMateriasPrima());
            this.subItems.add(subItem);
        });

        gabinete.fechamento().ifPresent(fechamento -> {

            if (fechamento instanceof Gaveteiro gaveteiro) {
                gaveteiro.gavetas().forEach(gaveta -> {
                    var subItemFrente = new SubItem(gaveta, gaveta.getMateriasPrima());
                    this.subItems.add(subItemFrente);

                    gaveta.corpoGaveta().ifPresent(corpoGaveta -> {
                        corpoGaveta.componentes().forEach(componente -> {
                            var subItemComponente = new SubItem(componente, componente.getMateriasPrima());
                            this.subItems.add(subItemComponente);
                        });
                    });
                });
            } else if (fechamento instanceof Portas portas) {
                portas.portas().forEach(porta -> {
                    var subItemFrente = new SubItem(porta, porta.getMateriasPrima());
                    this.subItems.add(subItemFrente);
                });
            }
        });

    }



    public void calcularTotal() {

        this.total = 0.0;
        gabinete.aplicarEstrategiaComDimensoes();
        gabinete.aplicarAcabamentos();

        gabinete.ferragens().forEach((ferragem, quantidade) -> {
            var precificar = new Precificar(ferragem, quantidade);
            var descricao = ferragem.getDescricao() + " - "
                    + ferragem.getCor();
            var subItem = new TotalPorMaterial(descricao, quantidade, ferragem.getPreco(), precificar.getValorTotal());
            this.totalPorMaterial.add(subItem);
        });

        gabinete.acabamentos().forEach((acabamento, quantidade) -> {

            var quantidadeConvertida = acabamento.getUnidade() ==
                    Unidade.METRO_LINEAR ? NumberHelper.mmParaMetros(quantidade)
                    : NumberHelper.mmSqParaMetrosSq(quantidade);

            var precificar = new Precificar(acabamento, quantidadeConvertida);
            var descricao = acabamento.getDescricao() + " - " + acabamento.getCor();


            var material = new TotalPorMaterial(descricao, quantidadeConvertida, acabamento.getPreco(), precificar.getValorTotal());
            this.totalPorMaterial.add(material);
        });

        this.totalPorMaterial.forEach(totalPorMaterial -> {
            this.total += totalPorMaterial.total();
        });

        adicionarSubItens();
    }

    @Override
    public String toString() {
        StringBuilder descricao = new StringBuilder();

        descricao.append("====================================================================").append("\n");
        descricao.append("Item: ").append(gabinete.descricao()).append("\n");
        descricao.append("====================================================================").append("\n");
        descricao.append("Componentes: ").append("\n");

          subItems.stream()
                //.sorted(Comparator.comparing(subItem -> subItem.componente().getDescricao()))
                .forEach(subItem -> {
                    descricao.append(subItem.toString());
                });

        descricao.append("====================================================================").append("\n");
        descricao.append("Material: ").append("\n");
        totalPorMaterial.sort(Comparator.comparing(TotalPorMaterial::descricao));
        totalPorMaterial.forEach(totalPorMaterial -> {
            descricao.append(totalPorMaterial.toString()).append("\n");
        });

        descricao.append("Total: ").append(NumberHelper.formatCurrency(total));
        return descricao.toString();
    }
}
