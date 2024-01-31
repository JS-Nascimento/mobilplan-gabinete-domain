package componentes.fechamentos;

import componentes.AbstractComponenteFechamento;
import componentes.Gabinete;
import componentes.PadraoDeFitagem;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import materiaPrima.acessorios.Puxador;

public class Portas extends AbstractComponenteFechamento{

    private final List<Porta> portas;
    private final TipoPorta tipoPorta;

    public Portas(TipoPorta tipoPorta, double espessura, PadraoDeFitagem padraoDeFitagem,
                  Puxador puxador) {
        super(padraoDeFitagem);
        this.tipoPorta = tipoPorta;
        this.puxador = Optional.ofNullable(puxador);
        this.espessura = espessura;
        this.portas = new ArrayList<>();
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        estrategia.aplicarParaPortas(this, dimensoes);
        this.estrategia = estrategia;

        portas.forEach(porta -> {
            porta.setGabinete(this.gabinete);
            porta.aceitar(estrategia, dimensoes);
        });
    }

    //TODO: Implementar
    public Folgas folgas() {
        return new Folgas(3, 3, 3, 3, 3);
    }

    public TipoPorta tipoPorta() {
        return tipoPorta;
    }

    public List<Porta> portas() {
        return portas;
    }

    public void adicionarPorta(Porta porta) {
        this.portas.add(porta);
    }

    public void definirPuxador(Puxador novoPuxador, Gabinete gabinete) {
        this.gabinete = gabinete;
        this.puxador = Optional.ofNullable(novoPuxador);

        gabinete.getDimensoes().ifPresent(dimensoes -> {

            for (Porta porta : portas) {
                removerPuxador(novoPuxador);
                porta.setGabinete(this.gabinete);
            }
        });
    }

    private void removerPuxador(Puxador puxador) {
        if (materiaPrimas().contains(puxador)) {
            materiaPrimas().remove(puxador);
        }
    }

    @Override
    public String getDescricaoCurta() {
        this.descricao = "Porta " +  tipoPorta.toString() ;
        return descricao;
    }

    @Override
    public Optional<Puxador> getPuxador() {
        return puxador;
    }

}
