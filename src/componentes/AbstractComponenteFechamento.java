package componentes;

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
