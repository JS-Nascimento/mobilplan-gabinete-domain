package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.config.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class FundoGaveta extends AbstractComponente {
    private final FolgasGavetas folgasGavetas;

    public FundoGaveta(FolgasGavetas folgasGavetas, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.folgasGavetas = folgasGavetas;
        this.espessura = folgasGavetas.espessuraFundo();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaFundoGaveta(this, dimensoes, this.espessura, this.padraoDeFitagem);
    }

    public void setDimensoes(double profundidade, double largura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.altura = profundidade;
        this.largura = largura;
        this.area = profundidade * largura;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem);
        this.descricao = "Fundo Gaveta" ;
    }

    public FolgasGavetas getFolgasGavetas() {
        return folgasGavetas;
    }
}