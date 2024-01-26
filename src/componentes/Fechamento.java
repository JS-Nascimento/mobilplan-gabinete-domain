package componentes;

import java.util.Optional;
import materiaPrima.acessorios.Puxador;

public interface Fechamento extends Componente {

    String getDescricaoCurta();

    Optional<Puxador> getPuxador();
}