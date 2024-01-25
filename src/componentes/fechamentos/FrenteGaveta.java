package componentes.fechamentos;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponenteFechamento;
import componentes.Dimensoes;
import componentes.Folgas;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class FrenteGaveta extends AbstractComponenteFechamento {

    private final TipoFrente tipoFrente;
    private final Folgas folgas;
    private final FolgasGavetas folgasGavetas;

    public FrenteGaveta(double altura, double espessura, TipoFrente tipoFrente, Folgas folgas,
                        FolgasGavetas folgasGavetas, PadraoDeFitagem padraoDeFitagem) {
        super(padraoDeFitagem);
        this.altura = altura;
        this.espessura = espessura;
        this.tipoFrente = tipoFrente;
        this.folgas = folgas;
        this.folgasGavetas = folgasGavetas;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaFrenteGaveta(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double largura, double profundidade, double espessura,  PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.area = (largura * profundidade) ;
        this.metragemFita = calcularMetragemFita(largura, profundidade, padraoDeFitagem) ;
        this.descricao = "Frente de Gaveta " + tipoFrente.toString();
    }

    public TipoFrente tipoFrente() {
        return tipoFrente;
    }

    public Folgas folgas() {
        return folgas;
    }
}
