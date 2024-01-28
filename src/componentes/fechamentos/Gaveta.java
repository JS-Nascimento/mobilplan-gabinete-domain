package componentes.fechamentos;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponenteFechamento;
import componentes.Gabinete;
import componentes.PadraoDeFitagem;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import componentes.config.FolgasGavetas;
import componentes.estruturais.CorpoGaveta;
import estrategias.EstrategiaDeConstrucao;
import java.util.Optional;
import materiaPrima.acessorios.Puxador;

public class Gaveta extends AbstractComponenteFechamento implements ComPuxador {
    private Optional<CorpoGaveta> corpoGaveta;
    private final TipoFrente tipoFrente;
    private final Folgas folgas;
    private final FolgasGavetas folgasGavetas;

    public Gaveta(double altura, double largura, double espessura, TipoFrente tipoFrente, Folgas folgas,
                  FolgasGavetas folgasGavetas, PadraoDeFitagem padraoDeFitagem, Puxador puxador) {
        super(padraoDeFitagem);
        this.corpoGaveta = Optional.empty();
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
        this.tipoFrente = tipoFrente;
        this.folgas = folgas;
        this.folgasGavetas = folgasGavetas;
        this.puxador = Optional.ofNullable(puxador);
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaPuxador(this, dimensoes, gabinete);
        aplicarPuxador();
        corpoGaveta.ifPresent(corpoGaveta -> {
            corpoGaveta.aceitar(estrategia, dimensoes);
        });
    }

    public void setDimensoes(double altura, double largura, double espessura,
                             PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.altura = altura;
        this.area = (largura * altura);
        this.metragemFita = calcularMetragemFita(altura, largura, padraoDeFitagem);
        StringBuilder sb = new StringBuilder();
        sb.append("Frente Gaveta ")
                .append(tipoFrente.toString());

        puxador.ifPresent(puxador -> {
            sb.append(" - ");
            sb.append(puxador.getTipoPuxador().toString());
        });

        this.descricao = sb.toString();

    }

    public TipoFrente tipoFrente() {
        return tipoFrente;
    }

    public Folgas folgas() {
        return folgas;
    }

    private void aplicarPuxador() {

        puxador.ifPresent(puxador -> {
            if (!this.materiaPrimas().contains(puxador)){
                this.materiaPrimas().add(puxador);
            }
        });

    }
    @Override
    public Optional<Puxador> getPuxador() {
        return puxador;
    }

    public void definirPuxador(Puxador novoPuxador) {
        this.puxador = Optional.ofNullable(novoPuxador);
    }

    public void adicionarCorpoGaveta(CorpoGaveta corpoGaveta) {
        this.corpoGaveta = Optional.ofNullable(corpoGaveta);
    }

    public Optional<CorpoGaveta> corpoGaveta() {
        return corpoGaveta;
    }

    public void setGabinete(Gabinete gabinete) {
        this.gabinete = gabinete;
    }
}
