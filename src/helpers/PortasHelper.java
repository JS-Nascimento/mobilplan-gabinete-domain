package helpers;

import static componentes.fechamentos.TipoPorta.PORTA_DIREITA;
import static componentes.fechamentos.TipoPorta.PORTA_ESQUERDA;

import componentes.Dimensoes;
import componentes.fechamentos.Porta;
import componentes.fechamentos.Portas;
import java.util.ArrayList;
import java.util.List;

public class PortasHelper {

    public static List<Porta> calcularPortas (Portas portas, Dimensoes dimensoes) {

        List<Porta> portasList = new ArrayList<>();
        var folgas = portas.folgas();

        var largura = dimensoes.getLargura() - folgas.direita() - folgas.esquerda();
        var altura = dimensoes.getAltura() - folgas.superior() - folgas.inferior();

        switch (portas.tipoPorta()) {

            case PORTA_DUPLA -> {
                largura = (largura - folgas.entreComponentes()) / 2;

                portasList.add(new Porta(altura, largura, portas.espessura(), portas.getPadraoDeFitagem(), PORTA_ESQUERDA, folgas));
                portasList.add(new Porta(altura, largura, portas.espessura(), portas.getPadraoDeFitagem(), PORTA_DIREITA, folgas));

                return portasList;
            }
            case PORTA_DIREITA, PORTA_ESQUERDA -> {

                portasList.add(new Porta(altura, largura, portas.espessura(), portas.getPadraoDeFitagem(),
                        portas.tipoPorta(), folgas));

                return portasList;
            }

            case PORTA_BASCULA -> {

                portasList.add(new Porta(largura, altura, portas.espessura(), portas.getPadraoDeFitagem(),
                        portas.tipoPorta(), folgas));

                return portasList;
            }
            //TODO: Implementar para outros tipos de portas
        }
        return portasList;
    }
}
