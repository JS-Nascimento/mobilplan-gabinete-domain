package componentes;

import java.util.ArrayList;
import java.util.List;
import materiaPrima.MateriaPrima;

public abstract class AbstractComponenteFechamento extends AbstractComponente implements Fechamento {

    protected String descricaoCurta;

    protected AbstractComponenteFechamento(PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
    }


    @Override
    public String getDescricaoCurta() {
        return descricaoCurta;
    }
}
