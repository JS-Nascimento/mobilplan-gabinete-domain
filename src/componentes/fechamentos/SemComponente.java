package componentes.fechamentos;

import static java.util.Optional.empty;

import componentes.AbstractComponenteFechamento;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import estrategias.EstrategiaDeConstrucao;
import java.util.Optional;
import materiaPrima.acessorios.Puxador;

public class SemComponente extends AbstractComponenteFechamento{

    public SemComponente() {
        super(null);
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        this.estrategia = estrategia;
    }

    //TODO: Implementar
    public Folgas folgas() {
        return null;
    }

    @Override
    public String getDescricaoCurta() {
        this.descricao = "Sem Fechamento" ;
        return descricao;
    }

    @Override
    public Optional<Puxador> getPuxador() {
        return empty();
    }

}
