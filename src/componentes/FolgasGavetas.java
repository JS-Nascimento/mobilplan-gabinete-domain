package componentes;

import componentes.estruturais.TipoFundo;

public class FolgasGavetas {

    private final TipoFundo tipoFundo;
    private final int folgaTrilhos;
    private final int profundidadeGaveta;
    private final int corpoEmRelacaoFrente;
    private final int espessuraCorpo;
    private final int espessuraFundo;
    private final int rebaixoFundo;

    private final PadraoDeFitagem padraoDeFitagem;

    //TODO: Refatorar para tratar valores invalidos
    public FolgasGavetas(TipoFundo tipoFundo, int folgaTrilhos, int profundidadeGaveta, int corpoEmRelacaoFrente, int espessuraCorpo, int espessuraFundo,
                         int rebaixoFundo,
                         PadraoDeFitagem padraoDeFitagem) {
        this.tipoFundo = tipoFundo;
        this.folgaTrilhos = folgaTrilhos;
        this.profundidadeGaveta = profundidadeGaveta;
        this.corpoEmRelacaoFrente = corpoEmRelacaoFrente;
        this.espessuraCorpo = espessuraCorpo;
        this.espessuraFundo = espessuraFundo;
        this.padraoDeFitagem = padraoDeFitagem;
        this.rebaixoFundo = rebaixoFundo;
    }

    public TipoFundo tipoFundo() {
        return tipoFundo;
    }

    public int rebaixoFundo() {
        return rebaixoFundo;
    }

    public int folgaTrilhos() {
        return folgaTrilhos;
    }

    public int profundidadeGaveta() {
        return profundidadeGaveta;
    }

    public int corpoEmRelacaoFrente() {
        return corpoEmRelacaoFrente;
    }

    public int espessuraCorpo() { return espessuraCorpo; }

    public int espessuraFundo() { return espessuraFundo; }

    public PadraoDeFitagem padraoDeFitagem() { return padraoDeFitagem; }
}
