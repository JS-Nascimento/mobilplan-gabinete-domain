package materiaPrima.acabamento;

import materiaPrima.MateriaPrima;
import materiaPrima.TipoPrecificacao;

public interface Acabamento extends MateriaPrima {

    final TipoPrecificacao precificacao = TipoPrecificacao.M2;
    TipoAcabamento getTipoAcabamento();
}

