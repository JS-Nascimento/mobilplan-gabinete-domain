package componentes.config;

public class DimensoesAcessorio {

    private final double altura;
    private final double largura;
    private final double espessura;


    public DimensoesAcessorio(double altura, double largura, double espessura) {
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
    }

    public double altura() {
        return altura;
    }

    public double largura() {
        return largura;
    }

    public double espessura() {
        return espessura;
    }
}
