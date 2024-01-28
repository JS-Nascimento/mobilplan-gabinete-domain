package componentes.fechamentos;

import componentes.AbstractComponenteFechamento;
import componentes.Gabinete;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import componentes.config.FolgasGavetas;
import componentes.PadraoDeFitagem;
import componentes.estruturais.TipoFundo;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import materiaPrima.acessorios.Puxador;

public class Gaveteiro extends AbstractComponenteFechamento{

    private final Folgas folgas;
    private final FolgasGavetas folgasGavetas;
    private final int quantidadeGavetas;
    private final List<Double> alturasDasFrentesSolicitadas;
    private final List<Gaveta> gavetas;
    private final TipoFrente tipoFrente;


    public Gaveteiro(final TipoFrente tipoFrente,
                     final int quantidadeGavetas,
                     List<Double> alturasDasFrentesSolicitadas,
                     final PadraoDeFitagem padraoDeFitagem,
                     double espessura) {
        super(padraoDeFitagem);
        this.quantidadeGavetas = quantidadeGavetas;
        this.alturasDasFrentesSolicitadas = alturasDasFrentesSolicitadas;
        this.folgas = folgas();
        this.folgasGavetas = folgasGavetas();
        this.tipoFrente = tipoFrente;
        this.espessura = espessura;
        this.gavetas = new ArrayList<>();
        this.puxador = Optional.empty();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaGaveta(this, dimensoes);

        gavetas.forEach(gaveta -> {
            gaveta.setGabinete(this.gabinete);
            gaveta.aceitar(estrategia, dimensoes);
        });
    }

    public Folgas folgas() {
        //TODO: Refatorar para Buscar configurações de folgas no banco de dados
        return new Folgas(3, 3, 3, 3, 3);
    }

    public TipoFrente tipoFrente() {
        return tipoFrente;
    }

    public int quantidadeGavetas() {
        return quantidadeGavetas;
    }

    public List<Gaveta> gavetas() {
        return gavetas;
    }

    public FolgasGavetas folgasGavetas() {

        return new FolgasGavetas(TipoFundo.ENCAIXADO, 26, 350, 30, 15, 6, 10, PadraoDeFitagem.UMA_ALTURA);
    }

    public void adicionarGavetas(Gaveta gaveta) {
        this.gavetas.add(gaveta);
    }
    @Override
    public String getDescricao() {
        this.descricao = "Gaveteiro com " + this.quantidadeGavetas + " gavetas\n";
        return descricao;
    }

    public void definirPuxador(Puxador novoPuxador, Gabinete gabinete) {
        this.gabinete = gabinete;
        this.puxador = Optional.ofNullable(novoPuxador);

        gabinete.getDimensoes().ifPresent(dimensoes -> {

            for (Gaveta gaveta : gavetas) {
                removerPuxador(novoPuxador);
                gaveta.setGabinete(this.gabinete);
            }
        });
    }
    private void removerPuxador(Puxador puxador) {
        if (materiaPrimas().contains(puxador)) {
            materiaPrimas().remove(puxador);
        }
    }
    @Override
    public Optional<Puxador> getPuxador() {
        return puxador;
    }

    public List<Double> alturaDeTodasAsFrentes() {
        return alturasDasFrentesSolicitadas;
    }
}
