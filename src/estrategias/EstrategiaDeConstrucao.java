package estrategias;

import componentes.Dimensoes;
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
import componentes.fechamentos.FrenteGaveta;
import componentes.fechamentos.Gaveta;
import componentes.fechamentos.Porta;
import componentes.fechamentos.TipoPorta;
import componentes.Folgas;
import componentes.PadraoDeFitagem;

public interface EstrategiaDeConstrucao {
    void aplicarParaBase(Base base, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaPrateleiraInterna(PrateleiraInterna prateleiraInterna, Dimensoes dimensoes,
                                      PadraoDeFitagem padraoDeFitagem, TipoPrateleira tipoPrateleira,
                                      Folgas folgas);

    void aplicarParaPorta(Porta porta, Dimensoes dimensoes,
                          PadraoDeFitagem padraoDeFitagem, TipoPorta tipoPorta, Folgas folgas);

    void aplicarParaTravessa(Travessa travessa, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaFundo(Fundo fundo, Dimensoes dimensoes, double espessuraFundo,
                          double valorVariavel, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaLateral(Lateral lateral, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaFundoGaveta(FundoGaveta fundoGaveta, Dimensoes dimensoes, double espessura, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaLateralGaveta(LateralGaveta lateralGaveta, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaContraFrenteGaveta(ContraFrenteGaveta contraFrenteGaveta, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaTraseiroGaveta(TraseiroGaveta traseiroGaveta, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaGaveta(Gaveta gaveta, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);

    void aplicarParaFrenteGaveta(FrenteGaveta gaveta, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem);
}

