package helpers;

import componentes.fechamentos.TipoFrente;

public class DescontosPadroes {

    private static final double DESCONTO_PUXADOR_PERFIL_MM = 35;
    private static final double ALTURA_MINIMA_GAVETA_MM = 100;

    public static double descontoAlturaFrente(TipoFrente tipoFrente, double alturaFrente) {
        return switch (tipoFrente) {
            case NORMAL, PUXADOR_USINADO -> alturaFrente;
            case PUXADOR_PERFIL -> (alturaFrente - DESCONTO_PUXADOR_PERFIL_MM);
        };
    }

    public static Boolean verificaAlturaMinimaGaveta(Double alturaFrente) {
        return alturaFrente >= ALTURA_MINIMA_GAVETA_MM;
    }
}
