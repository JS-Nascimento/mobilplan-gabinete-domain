package estrategias;

import static helpers.DescontosPadroes.descontoAlturaFrente;
import static helpers.PortasHelper.calcularPortas;

import componentes.config.Dimensoes;
import componentes.config.Folgas;
import componentes.PadraoDeFitagem;
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
import componentes.fechamentos.Gavetas;
import componentes.fechamentos.Porta;
import componentes.fechamentos.Portas;

class BaseSobreLaterais implements EstrategiaDeConstrucao {

    @Override
    public void aplicarParaBase(Base base, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {
        double largura = dimensoes.getLargura();
        double profundidade = dimensoes.getProfundidade();
        base.setDimensoes(largura, profundidade, dimensoes.getEspessura(), padraoDeFitagem);
    }

    @Override
    public void aplicarParaPrateleiraInterna(PrateleiraInterna prateleiraInterna, Dimensoes dimensoes,
                                             PadraoDeFitagem padraoDeFitagem, TipoPrateleira tipoPrateleira,
                                             Folgas folgas) {
        var dimensoesInternas = dimensoes.calcularDimensoesInternas();
        double largura = dimensoesInternas.getLargura() - folgas.direita() - folgas.esquerda();
        double profundidade = dimensoesInternas.getProfundidade() - folgas.inferior() - folgas.superior();

        prateleiraInterna.setDimensoes(largura, profundidade, dimensoes.getEspessura(), padraoDeFitagem);
    }

    @Override
    public void aplicarParaPortas(Portas portas, Dimensoes dimensoes) {

        var listaPortas = calcularPortas(portas, dimensoes);

        listaPortas.forEach(portas::adicionarPorta);
    }

    @Override
    public void aplicarParaPorta(Porta porta, Dimensoes dimensoes) {

    }


    @Override
    public void aplicarParaTravessa(Travessa travessa, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {
        double largura = dimensoes.getLargura();
        travessa.setDimensoes(largura, dimensoes.getEspessura(), padraoDeFitagem);
    }

    @Override
    public void aplicarParaLateral(Lateral lateral, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {

        double altura = dimensoes.getAltura() - 2 * dimensoes.getEspessura();
        double profundidade = dimensoes.getProfundidade();
        lateral.setDimensoes(altura, profundidade, dimensoes.getEspessura(), padraoDeFitagem);
    }

    @Override
    public void aplicarParaFundoGaveta(FundoGaveta fundoGaveta, Dimensoes dimensoes, double espessura,
                                       PadraoDeFitagem padraoDeFitagem) {

    }

    @Override
    public void aplicarParaLateralGaveta(LateralGaveta lateralGaveta, Dimensoes dimensoes,
                                         PadraoDeFitagem padraoDeFitagem) {

        lateralGaveta.setDimensoes(lateralGaveta.altura(), lateralGaveta.profundidade(), lateralGaveta.espessura(),
                padraoDeFitagem);
    }

    @Override
    public void aplicarParaContraFrenteGaveta(ContraFrenteGaveta contraFrenteGaveta, Dimensoes dimensoes,
                                              PadraoDeFitagem padraoDeFitagem) {

        var largura = dimensoes.calcularDimensoesInternas().getLargura()
                - contraFrenteGaveta.getFolgasGavetas().folgaTrilhos()
                - (2 * contraFrenteGaveta.getFolgasGavetas().espessuraCorpo());

        contraFrenteGaveta.setDimensoes(contraFrenteGaveta.altura(), largura,
                contraFrenteGaveta.getFolgasGavetas().espessuraCorpo(), padraoDeFitagem);
    }

    @Override
    public void aplicarParaTraseiroGaveta(TraseiroGaveta traseiroGaveta, Dimensoes dimensoes,
                                          PadraoDeFitagem padraoDeFitagem) {
        var largura = dimensoes.calcularDimensoesInternas().getLargura()
                - traseiroGaveta.getFolgasGavetas().folgaTrilhos()
                - (2 * traseiroGaveta.getFolgasGavetas().espessuraCorpo());

        traseiroGaveta.setDimensoes(traseiroGaveta.altura(), largura,
                traseiroGaveta.getFolgasGavetas().espessuraCorpo(), padraoDeFitagem);

    }

    @Override
    public void aplicarParaGaveta(Gavetas gavetas, Dimensoes dimensoes,
                                  PadraoDeFitagem padraoDeFitagem) {

        gavetas.alturaDeTodasAsFrentes().forEach (frente ->{
            var novaFrenteGaveta = new FrenteGaveta(
                    descontoAlturaFrente(gavetas.tipoFrente(), frente),
                    gavetas.espessura(),
                    gavetas.tipoFrente(),
                    gavetas.folgas(),
                    gavetas.folgasGavetas(),
                    gavetas.getPadraoDeFitagem());
            gavetas.adicionarFrenteGaveta(novaFrenteGaveta);
        });

    }

    @Override
    public void aplicarParaFrenteGaveta(FrenteGaveta gaveta, Dimensoes dimensoes,
                                        PadraoDeFitagem padraoDeFitagem) {

        var largura =
                dimensoes.getLargura() - gaveta.folgas().direita() - gaveta.folgas().esquerda();

        gaveta.setDimensoes(largura, descontoAlturaFrente(gaveta.tipoFrente(), gaveta.altura()),
                gaveta.espessura(), gaveta.getPadraoDeFitagem());

    }

    @Override
    public void aplicarParaFundo(Fundo fundo, Dimensoes dimensoes, double espessuraFundo, double valorVariavel,
                                 PadraoDeFitagem padraoDeFitagem) {
        double largura = 0;
        double altura = 0;

        switch (fundo.getTipoFundo()) {
            case SOBREPOSTO:
                largura = dimensoes.getLargura() - 2 * valorVariavel;
                altura = dimensoes.getAltura() - 2 * valorVariavel;
                break;
            case ENCAIXADO:
                largura = (dimensoes.getLargura() - 2 * dimensoes.getEspessura()) + 2 * valorVariavel;
                altura = (dimensoes.getAltura() - 2 * dimensoes.getEspessura()) + 2 * valorVariavel;
                break;
        }
        fundo.setDimensoes(largura, altura, espessuraFundo, padraoDeFitagem);
    }
}
