package componentes.estruturais;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponente;
import componentes.config.Dimensoes;
import componentes.config.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;

public class TraseiroGaveta extends AbstractComponente {
    private final FolgasGavetas folgasGavetas;

    public TraseiroGaveta(double altura, FolgasGavetas folgasGavetas) {
        super(folgasGavetas.padraoDeFitagem());
        this.altura = altura;
        this.folgasGavetas = folgasGavetas;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaTraseiroGaveta(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double altura, double largura, double espessura, PadraoDeFitagem padraoDeFitagem) {
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
        this.area = altura * largura;
        this.metragemFita = calcularMetragemFita(largura, altura, padraoDeFitagem);
        this.descricao = "Traseiro Gaveta" ;
    }

    public FolgasGavetas getFolgasGavetas() {
        return folgasGavetas;
    }
}