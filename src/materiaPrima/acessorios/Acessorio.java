package materiaPrima.acessorios;

import static materiaPrima.TipoPrecificacao.UNIDADE;

import materiaPrima.MateriaPrima;
import materiaPrima.TipoPrecificacao;

public interface Acessorio extends MateriaPrima {

    final TipoPrecificacao precificacao = UNIDADE;


}
