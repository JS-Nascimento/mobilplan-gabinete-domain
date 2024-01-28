package helpers;

import static componentes.fechamentos.TipoPorta.DIREITA;
import static componentes.fechamentos.TipoPorta.ESQUERDA;
import static helpers.DescontosPadroes.verificaAlturaMinimaGaveta;
import static helpers.NumberHelper.roundDouble;
import static java.util.Collections.nCopies;

import componentes.config.Dimensoes;
import componentes.config.Folgas;
import componentes.fechamentos.Porta;
import componentes.fechamentos.Portas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FechamentosHelper {

    public static List<Porta> calcularPortas(Portas portas, Dimensoes dimensoes) {

        List<Porta> portasList = new ArrayList<>();
        var folgas = portas.folgas();

        var largura = dimensoes.getLargura() - folgas.direita() - folgas.esquerda();
        var altura = dimensoes.getAltura() - folgas.superior() - folgas.inferior();

        switch (portas.tipoPorta()) {

            case DUPLA -> {
                largura = (largura - folgas.entreComponentes()) / 2;

                portasList.add(
                        new Porta(
                                altura,
                                largura,
                                portas.espessura(),
                                portas.getPadraoDeFitagem(),
                                ESQUERDA,
                                folgas,
                                portas.getPuxador().orElseGet(() -> null)
                        )
                );
                portasList.add(
                        new Porta(
                                altura,
                                largura,
                                portas.espessura(),
                                portas.getPadraoDeFitagem(),
                                DIREITA,
                                folgas,
                                portas.getPuxador().orElseGet(() -> null)
                        )
                );
            }
            case DIREITA, ESQUERDA -> {

                portasList.add(new Porta(altura, largura, portas.espessura(), portas.getPadraoDeFitagem(),
                        portas.tipoPorta(), folgas,
                        portas.getPuxador().orElseGet(() -> null)));
            }

            case BASCULA, BASCULA_INVERSA -> {

                portasList.add(new Porta(largura, altura, portas.espessura(), portas.getPadraoDeFitagem(),
                        portas.tipoPorta(), folgas,
                        portas.getPuxador().orElseGet(() -> null)));

            }
        }
        return portasList;
    }

    private static void validarAlturaFrentes(final double alturaGabinete,
                                      List<Double> alturasDasFrentesSolicitadas,
                                      int quantidadeGavetas,
                                      Folgas folgas) {


        if (alturasDasFrentesSolicitadas.size() >= quantidadeGavetas) {
            throw new IllegalArgumentException("A quantidade de gavetas informada é diferente da quantidade de alturas informadas");
        }

        var alturaTotalFrentes = alturasDasFrentesSolicitadas.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        alturaTotalFrentes += (folgas.entreComponentes() * (quantidadeGavetas - 1));
        alturaTotalFrentes += folgas.superior() + folgas.inferior();

        if (alturaTotalFrentes > alturaGabinete){
            throw new IllegalArgumentException("A soma das alturas das gavetas excede a altura do gabinete");
        }
        if (!verificaAlturaMinimaGaveta(alturaGabinete - alturaTotalFrentes)){
            throw new IllegalArgumentException("Altura minima da gavetas não atingida.");
        }

    }
    public static List<Double> calcularAlturasDasFrentes(Dimensoes dimensoes,
                                                          List<Double> alturasDasFrentesSolicitadas,
                                                          int quantidadeGavetas,
                                                          Folgas folgas) {

        validarAlturaFrentes(dimensoes.getAltura(),
                             alturasDasFrentesSolicitadas,
                             quantidadeGavetas,
                             folgas);

        var totalFolgas =(folgas.entreComponentes() * (quantidadeGavetas - 1)) + folgas.superior() + folgas.inferior();
        var alturaLivreGabinete = dimensoes.getAltura() - totalFolgas;

        if (quantidadeGavetas == 1) {
          return List.of(alturaLivreGabinete);
        }

        var alturaTotalFrentesGaveta = alturasDasFrentesSolicitadas.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        var gavetasRestantes = quantidadeGavetas - alturasDasFrentesSolicitadas.size();

        var alturaRestante = alturaLivreGabinete - alturaTotalFrentesGaveta;

        var alturaDasFrentesRestantes = roundDouble(( alturaRestante / gavetasRestantes),1);

        var alturaFrentesCalculadas = new ArrayList<>(alturasDasFrentesSolicitadas);

        alturaFrentesCalculadas.addAll(nCopies(gavetasRestantes, alturaDasFrentesRestantes));

        return alturaFrentesCalculadas;
    }
}
