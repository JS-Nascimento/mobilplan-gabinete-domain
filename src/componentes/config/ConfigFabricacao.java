package componentes.config;

import static componentes.PadraoDeFitagem.QUATRO_LADOS;
import static componentes.PadraoDeFitagem.UMA_ALTURA;

import componentes.PadraoDeFitagem;

public class ConfigFabricacao {

    // Dimensoes padrao
    public static final double PROFUNDIDADE_TRAVESSA_HORIZONTAL = 75;
    public static final double DESCONTO_PROFUNDIDADE_MOVEL = 30;
    public static final double DESCONTO_PROFUNDIDADE_FIXA = 20;
    public static final double DESCONTO_PROFUNDIDADE_DIVISORIA_HORIZONTAL_INTERNA = 5;

    // Padroes de fitagem
    public static final PadraoDeFitagem PADRAO_ACABAMENTO_BASE = UMA_ALTURA;
    public static final PadraoDeFitagem PADRAO_ACABAMENTO_LATERAL = UMA_ALTURA;
    public static final PadraoDeFitagem PADRAO_ACABAMENTO_TRAVESSA_HORIZONTAL = UMA_ALTURA;
    public static final PadraoDeFitagem PADRAO_ACABAMENTO_PRATELEIRA_INTERNA = UMA_ALTURA;
    public static final PadraoDeFitagem PADRAO_ACABAMENTO_PORTA = QUATRO_LADOS;
    public static final PadraoDeFitagem PADRAO_ACABAMENTO_FRENTE_GAVETA = QUATRO_LADOS;


}
