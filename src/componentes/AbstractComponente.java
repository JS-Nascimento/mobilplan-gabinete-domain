package componentes;

import java.util.ArrayList;
import java.util.List;
import materiaPrima.MateriaPrima;

public abstract class AbstractComponente implements Estrutural{

    protected String descricao;
    protected double largura;
    protected double altura;
    protected double profundidade;
    protected double espessura;

    protected double area;

    protected double metragemFita;

    protected final PadraoDeFitagem padraoDeFitagem ;

    private final List<MateriaPrima> materiaPrimas;

    protected AbstractComponente(PadraoDeFitagem padraoDeFitagem) {
        this.padraoDeFitagem = padraoDeFitagem;
        this.materiaPrimas = new ArrayList<>();
    }

    public List<MateriaPrima> materiaPrimas() {
        return materiaPrimas;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public double getMetragemLinear() {
        return this.metragemFita;
    }

    @Override
    public List<MateriaPrima> getMateriasPrima() {
        return this.materiaPrimas();
    }

    @Override
    public PadraoDeFitagem getPadraoDeFitagem() {
        return padraoDeFitagem;
    }

    public double largura() {
        return largura;
    }

    public double altura() {
        return altura;
    }

    public double profundidade() {
        return profundidade;
    }

    public double espessura() {
        return espessura;
    }

    public void adicionarAcabamentos(List<MateriaPrima> novosAcabamentos) {
        for (MateriaPrima materiaPrima : novosAcabamentos) {
            if (!materiaPrimas.contains(materiaPrima)) {
                materiaPrimas.add(materiaPrima);
            }
        }
    }
}
