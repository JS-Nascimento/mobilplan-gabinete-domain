package materiaPrima.acessorios;

import static materiaPrima.TipoPrecificacao.UNIDADE;

import componentes.config.Dimensoes;
import estrategias.EstrategiaDeConstrucao;
import materiaPrima.MateriaPrima;
import materiaPrima.TipoPrecificacao;

public interface Acessorio extends MateriaPrima {

    final TipoPrecificacao precificacao = UNIDADE;

}
