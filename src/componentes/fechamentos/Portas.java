package componentes.fechamentos;

import componentes.AbstractComponenteFechamento;
import componentes.Dimensoes;
import componentes.Folgas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.List;

public class Portas extends AbstractComponenteFechamento {

    private final List<Porta> portas;
    private final TipoPorta tipoPorta;

    public Portas(TipoPorta tipoPorta, double espessura, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.tipoPorta = tipoPorta;
        this.espessura = espessura;
        this.portas = new ArrayList<>();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaPortas(this, dimensoes);

        for (Porta porta : portas) {
            porta.aceitar(estrategia, dimensoes);
        }
    }
    public Folgas folgas() {
        return new Folgas(3, 3, 3, 3, 3);
    }

    public TipoPorta tipoPorta() {
        return tipoPorta;
    }

    public List<Porta> portas() {
        return portas;
    }
    public void adicionarPorta(Porta porta) {
        this.portas.add(porta);
    }
}
