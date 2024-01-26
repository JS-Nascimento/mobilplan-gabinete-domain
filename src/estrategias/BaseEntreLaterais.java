package estrategias;

import static helpers.DescontosPadroes.descontoAlturaFrente;
import static helpers.FechamentosHelper.calcularPortas;

import componentes.Gabinete;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import componentes.PadraoDeFitagem;
import componentes.estruturais.Base;
import componentes.estruturais.ContraFrenteGaveta;
import componentes.estruturais.CorpoGaveta;
import componentes.estruturais.Fundo;
import componentes.estruturais.FundoGaveta;
import componentes.estruturais.Lateral;
import componentes.estruturais.LateralGaveta;
import componentes.estruturais.PrateleiraInterna;
import componentes.estruturais.TipoPrateleira;
import componentes.estruturais.TraseiroGaveta;
import componentes.estruturais.Travessa;
import componentes.fechamentos.ComPuxador;
import componentes.fechamentos.FrenteGaveta;
import componentes.fechamentos.Gavetas;
import componentes.fechamentos.Portas;
import java.util.Map;

public class BaseEntreLaterais implements EstrategiaDeConstrucao {

    @Override
    public void aplicarParaBase(Base base, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {
        double largura = dimensoes.getLargura() - 2 * dimensoes.getEspessura();
        double profundidade = dimensoes.getProfundidade();
        base.setDimensoes(largura, profundidade, dimensoes.getEspessura(), padraoDeFitagem);
    }

    @Override
    public void aplicarParaPrateleiraInterna(PrateleiraInterna prateleiraInterna, Dimensoes dimensoes,
                                             PadraoDeFitagem padraoDeFitagem,
                                             TipoPrateleira tipoPrateleira, Folgas folgas) {
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
    public void aplicarParaPuxador(ComPuxador fechamento, Dimensoes dimensoes, Gabinete gabinete) {

        fechamento.getPuxador().ifPresent(puxador -> {
            if (puxador.isPerfil()) {
                switch (puxador.getDirecao()) {
                    case HORIZONTAL -> {
                        var altura = fechamento.altura() - puxador.getDimensoesAcessorio().altura();
                        var quantidadePuxador = Map.of(puxador, fechamento.largura());
                        gabinete.adicionarFerragem(puxador, fechamento.largura());
                        fechamento.setDimensoes(
                                altura,
                                fechamento.largura(),
                                fechamento.espessura(),
                                fechamento.getPadraoDeFitagem());

                    }
                    case VERTICAL -> {
                        var largura = fechamento.largura() - puxador.getDimensoesAcessorio().altura();
                        gabinete.adicionarFerragem(puxador, fechamento.altura());
                        fechamento.setDimensoes(
                                fechamento.altura(),
                                largura,
                                fechamento.espessura(),
                                fechamento.getPadraoDeFitagem());
                    }
                }
            } else {
                gabinete.adicionarFerragem(puxador, 1.0);
                fechamento.setDimensoes(
                        fechamento.altura(),
                        fechamento.largura(),
                        fechamento.espessura(),
                        fechamento.getPadraoDeFitagem());
            }
        });
    }

    @Override
    public void aplicarParaLateral(Lateral lateral, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {

        double altura = dimensoes.getAltura();
        double profundidade = dimensoes.getProfundidade();
        lateral.setDimensoes(altura, profundidade, dimensoes.getEspessura(), padraoDeFitagem);

    }

    @Override
    public void aplicarParaFundoGaveta(FundoGaveta fundoGaveta, Dimensoes dimensoes,
                                       double espessura, PadraoDeFitagem padraoDeFitagem) {

        double largura;
        double altura = switch (fundoGaveta.getFolgasGavetas().tipoFundo()) {
            case SOBREPOSTO -> {
                largura = dimensoes.calcularDimensoesInternas().getLargura()
                        - fundoGaveta.getFolgasGavetas().folgaTrilhos()
                        - (2 * fundoGaveta.getFolgasGavetas().espessuraCorpo());
                yield fundoGaveta.getFolgasGavetas().profundidadeGaveta();
            }
            case ENCAIXADO -> {
                largura = dimensoes.calcularDimensoesInternas().getLargura()
                        - fundoGaveta.getFolgasGavetas().folgaTrilhos()
                        - (2 * (fundoGaveta.getFolgasGavetas().espessuraCorpo()))
                        + (2 * fundoGaveta.getFolgasGavetas().rebaixoFundo());
                yield fundoGaveta.getFolgasGavetas().profundidadeGaveta()
                        - (fundoGaveta.getFolgasGavetas().espessuraCorpo() -
                        fundoGaveta.getFolgasGavetas().rebaixoFundo());
            }
        };

        fundoGaveta.setDimensoes(altura, largura, fundoGaveta.getFolgasGavetas().espessuraFundo(), padraoDeFitagem);
    }

    @Override
    public void aplicarParaLateralGaveta(LateralGaveta lateralGaveta, Dimensoes dimensoes,
                                         PadraoDeFitagem padraoDeFitagem) {

        lateralGaveta.setDimensoes(lateralGaveta.altura(), lateralGaveta.profundidade(),
                lateralGaveta.espessura(), padraoDeFitagem);
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

        gavetas.alturaDeTodasAsFrentes().forEach(frente -> {
            var novaFrenteGaveta = new FrenteGaveta(
                    descontoAlturaFrente(gavetas.tipoFrente(), frente),
                    gavetas.espessura(),
                    gavetas.tipoFrente(),
                    gavetas.folgas(),
                    gavetas.folgasGavetas(),
                    gavetas.getPadraoDeFitagem());
            gavetas.adicionarFrenteGaveta(novaFrenteGaveta);

            var corpoGaveta = new CorpoGaveta(dimensoes, gavetas.folgasGavetas(),
                    (frente - gavetas.folgasGavetas().corpoEmRelacaoFrente()));
            gavetas.adicionarCorpoGaveta(corpoGaveta);
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
    public void aplicarParaTravessa(Travessa travessa, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {
        double largura = dimensoes.getLargura() - (2 * dimensoes.getEspessura());
        travessa.setDimensoes(largura, dimensoes.getEspessura(), padraoDeFitagem);
    }

    @Override
    public void aplicarParaFundo(Fundo fundo, Dimensoes dimensoes, double espessuraFundo, double valorVariavel,
                                 PadraoDeFitagem padraoDeFitagem) {
        double largura;
        double altura = switch (fundo.getTipoFundo()) {
            case SOBREPOSTO -> {
                largura = dimensoes.getLargura() - 2 * valorVariavel;
                yield dimensoes.getAltura() - 2 * valorVariavel;
            }
            case ENCAIXADO -> {
                largura = (dimensoes.getLargura() - 2 * dimensoes.getEspessura()) + 2 * valorVariavel;
                yield (dimensoes.getAltura() - 2 * dimensoes.getEspessura()) + 2 * valorVariavel;
            }
        };

        fundo.setDimensoes(largura, altura, espessuraFundo, padraoDeFitagem);
    }

}
