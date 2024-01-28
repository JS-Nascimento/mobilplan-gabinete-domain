package estrategias;

import componentes.Gabinete;
import componentes.PadraoDeFitagem;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import componentes.estruturais.Base;
import componentes.estruturais.ContraFrenteGaveta;
import componentes.estruturais.Fundo;
import componentes.estruturais.FundoGaveta;
import componentes.estruturais.Lateral;
import componentes.estruturais.LateralGaveta;
import componentes.estruturais.PrateleiraInterna;
import componentes.estruturais.TipoPrateleira;
import componentes.estruturais.TraseiroGaveta;
import componentes.estruturais.Travessa;
import componentes.fechamentos.ComPuxador;
import componentes.fechamentos.Gaveteiro;
import componentes.fechamentos.Portas;

public interface EstrategiaDeConstrucao {
    void aplicarParaBase(Base base, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaPrateleiraInterna(PrateleiraInterna prateleiraInterna, Dimensoes dimensoes,
                                      PadraoDeFitagem padraoDeFitagem, TipoPrateleira tipoPrateleira,
                                      Folgas folgas);

    void aplicarParaPortas(Portas portas, Dimensoes dimensoes);

    void aplicarParaPuxador(ComPuxador fechamento, Dimensoes dimensoes, Gabinete gabinete);

    void aplicarParaTravessa(Travessa travessa, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaFundo(Fundo fundo, Dimensoes dimensoes, double espessuraFundo,
                          double valorVariavel, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaLateral(Lateral lateral, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaFundoGaveta(FundoGaveta fundoGaveta, Dimensoes dimensoes, double espessura, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaLateralGaveta(LateralGaveta lateralGaveta, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaContraFrenteGaveta(ContraFrenteGaveta contraFrenteGaveta, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaTraseiroGaveta(TraseiroGaveta traseiroGaveta, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaGaveta(Gaveteiro gaveteiro, Dimensoes dimensoes);

}

