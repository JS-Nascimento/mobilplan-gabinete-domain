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

    public static double calcularMetragemFita(double largura, double profundidade, PadraoDeFitagem padraoDeFitagem) {

        if (isNull(padraoDeFitagem)) {
            return 0.0;
        }

        return switch (padraoDeFitagem) {
            case NENHUM -> 0.0;
            case UMA_ALTURA -> largura;
            case UMA_ALTURA_UMA_LARGURA -> largura + profundidade;
            case UMA_ALTURA_DUAS_LARGURAS -> largura + profundidade * 2;
            case DUAS_ALTURAS -> largura * 2;
            case DUAS_ALTURAS_UMA_LARGURA -> (largura * 2) + profundidade;
            case QUATRO_LADOS -> (largura + profundidade) * 2;
        };
    }
}
