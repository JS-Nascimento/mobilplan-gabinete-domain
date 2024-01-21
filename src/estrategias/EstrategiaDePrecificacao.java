package estrategias;

import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Estrutural;
import java.util.List;

public interface EstrategiaDePrecificacao {

    void calcularPrecoEstrutural(Estrutural estrutural, List<MateriaPrima> materiaPrimas);
}
