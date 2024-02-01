package componentes;

import estrategias.EstrategiaDeConstrucao;
import java.util.Optional;
import materiaPrima.acessorios.Puxador;
import modelos.Gabinete;

public abstract class AbstractComponenteFechamento extends AbstractComponente implements Fechamento {

    protected String descricaoCurta;
    protected Gabinete gabinete;

    protected Optional<Puxador> puxador;

    protected EstrategiaDeConstrucao estrategia;



    protected AbstractComponenteFechamento(PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
    }


    @Override
    public String getDescricaoCurta() {
        return descricaoCurta;
    }


}
