package proposta.item;

import componentes.Componente;
import helpers.NumberHelper;
import java.util.List;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Unidade;
import materiaPrima.acessorios.Puxador;

public class SubItem {

    private final Componente componente;
    private final List<MateriaPrima> materiaPrimas;

    public SubItem(Componente componente, List<MateriaPrima> materiaPrimas) {
        this.componente = componente;
        this.materiaPrimas = materiaPrimas;
    }

    public Componente componente() {
        return componente;
    }

    public List<MateriaPrima> materiaPrimas() {
        return materiaPrimas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(componente.getDescricao()).append(" - ");
        sb.append(componente.altura()).append(" x ");
        sb.append(componente.largura()).append(" x ");
        sb.append(componente.espessura()).append(" : ");

        for (MateriaPrima materiaPrima : componente.getMateriasPrima()) {
            sb.append('\n');
            sb.append(" ==> ");
            sb.append(materiaPrima.getDescricao()).append(" ").append(materiaPrima.getCor());

            switch (materiaPrima.getUnidade()) {
                case METRO_QUADRADO -> sb.append(" - ")
                        .append(NumberHelper.mmSqParaMetrosSq(componente.getArea()))
                        .append(materiaPrima.getUnidade().getDescricao())
                        .append(" x ").append(materiaPrima.getPreco()).append(" = ")
                        .append(NumberHelper.formatCurrency(
                                materiaPrima.getPreco() * NumberHelper.mmSqParaMetrosSq(componente.getArea())));
                case METRO_LINEAR -> {
                        sb.append(" - ")
                                .append(NumberHelper.mmParaMetros(componente.getMetragemLinear()))
                                .append(materiaPrima.getUnidade().getDescricao())
                                .append(" x ").append(materiaPrima.getPreco()).append(" = ")
                                .append(NumberHelper.formatCurrency(materiaPrima.getPreco() *
                                        NumberHelper.mmParaMetros(componente.getMetragemLinear())));
                }
            }
        }
        sb.append('\n');
        sb.append('\n');
        return sb.toString();
    }
}
