package materiaPrima.acabamento;

import static java.util.Objects.isNull;

import helpers.NumberHelper;

public class DimensoesChapa {

    private final double altura;
    private final double largura;
    private final double espessura;
    private double quantidadePorChapa;


    public DimensoesChapa(double altura, double largura, double espessura) {
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
    }

    public double getAltura() {
        return altura;
    }

    public double getLargura() {
        return largura;
    }

    public double getEspessura() {
        return espessura;
    }

    public double quantidadePorChapa() {

        if ((isNull(altura) || altura == 0 ) || (isNull(largura) || largura == 0 ) ||(isNull(espessura) || espessura == 0 )) {
            return 0.0;
        }
        return NumberHelper.roundDouble(altura * largura, 2);
    }
}
