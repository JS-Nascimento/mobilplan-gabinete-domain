package componentes.fechamentos;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponenteFechamento;
import componentes.PadraoDeFitagem;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import componentes.config.FolgasGavetas;
import componentes.estruturais.CorpoGaveta;
import estrategias.EstrategiaDeConstrucao;
import java.util.List;
import java.util.Optional;
import materiaPrima.acessorios.Puxador;

public class FrenteGaveta extends AbstractComponenteFechamento implements ComPuxador {

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
        this.puxador = Optional.empty();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
      //  estrategia.aplicarParaFrenteGaveta(this, dimensoes, this.padraoDeFitagem);
    }

    public void setDimensoes(double altura, double largura, double espessura,
                             PadraoDeFitagem padraoDeFitagem) {
//        this.largura = largura;
//        this.altura = altura;
//        this.area = (largura * altura);
//        this.metragemFita = calcularMetragemFita(altura, largura, padraoDeFitagem);
//        StringBuilder sb = new StringBuilder();
//        sb.append("Frente Gaveta ")
//                .append(tipoFrente.toString());
//
//        puxador.ifPresent(puxador -> {
//            sb.append(" - ");
//            sb.append(puxador.getTipoPuxador().toString());
//        });
//
//        this.descricao = sb.toString();

    }

    public TipoFrente tipoFrente() {
        return tipoFrente;
    }

    public Folgas folgas() {
        return folgas;
    }

    @Override
    public Optional<Puxador> getPuxador() {
        return puxador;
    }
}
