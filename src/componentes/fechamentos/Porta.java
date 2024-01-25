package componentes.fechamentos;

import componentes.AbstractComponenteFechamento;
import componentes.Dimensoes;
import componentes.Folgas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class Porta extends AbstractComponenteFechamento {

    private final TipoPorta tipoPorta;
    private final Folgas folgas;

    public Porta(double altura, double largura, double espessura, PadraoDeFitagem padraoDeFitagem, TipoPorta tipoPorta,
                 Folgas folgas) {
        super(padraoDeFitagem);
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
        this.tipoPorta = tipoPorta;
        this.folgas = folgas;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {

    }
}
