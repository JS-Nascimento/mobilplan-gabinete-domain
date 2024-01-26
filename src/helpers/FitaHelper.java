package helpers;

import static java.util.Objects.isNull;

import componentes.PadraoDeFitagem;

public class FitaHelper {


    public static double larguraDaFita(double espessuraDaChapa) {

        if(espessuraDaChapa <= 6.0) return 0.0;

        return switch ((int) espessuraDaChapa) {
                case (int) 15.0 -> 16.0;
                case (int) 18.0 -> 22.0;
                case (int) 25.0 -> 35.0;
                default -> 64.0;
        };
    }

    public static double calcularMetragemFita(double altura, double largura, PadraoDeFitagem padraoDeFitagem) {

        if (isNull(padraoDeFitagem)) {
            return 0.0;
        }

        return switch (padraoDeFitagem) {
            case NENHUM -> 0.0;
            case UMA_ALTURA -> altura;
            case UMA_ALTURA_UMA_LARGURA -> altura + largura;
            case UMA_ALTURA_DUAS_LARGURAS -> altura + (largura * 2);
            case DUAS_ALTURAS -> altura * 2;
            case DUAS_ALTURAS_UMA_LARGURA -> (altura * 2) + largura;
            case QUATRO_LADOS -> (largura + altura) * 2;
        };
    }
}
