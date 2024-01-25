package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.config.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class LateralGaveta extends AbstractComponente {

    public LateralGaveta(FolgasGavetas folgasGavetas, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.altura = dimensoes.getAltura();
        this.profundidade = folgasGavetas.profundidadeGaveta();
        this.espessura = folgasGavetas.espessuraCorpo();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaLateralGaveta(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double altura, double profundidade, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.altura = altura;
        this.profundidade = profundidade;
        this.area = altura * profundidade;
        this.metragemFita = calcularMetragemFita(altura, profundidade, padraoDeFitagem);
        this.descricao = "Lateral Gaveta" ;
    }
}