package componentes.fechamentos;

import componentes.AbstractComponenteFechamento;
import componentes.PadraoDeFitagem;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import estrategias.EstrategiaDeConstrucao;
import java.util.Optional;
import materiaPrima.acessorios.Puxador;

public class Porta extends AbstractComponenteFechamento {
    private final TipoPorta tipoPorta;
    private final Folgas folgas;
    private Optional<Puxador> puxador;

    public Porta(double altura, double largura, double espessura, PadraoDeFitagem padraoDeFitagem, TipoPorta tipoPorta,
                 Folgas folgas, Puxador puxador) {
        super(padraoDeFitagem);
        this.tipoPorta = tipoPorta;
        this.puxador = Optional.ofNullable(puxador);
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
        this.folgas = folgas;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {

    }

    public TipoPorta tipoPorta() {
        return tipoPorta;
    }

    public Folgas folgas() {
        return folgas;
    }

    public void definirPuxador(Puxador novoPuxador) {
        this.puxador = Optional.ofNullable(novoPuxador);
    }
    public Optional<Puxador> puxador() {
        return puxador;
    }
}
