package componentes.estruturais;

import componentes.config.Dimensoes;
import componentes.Estrutural;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import materiaPrima.MateriaPrima;


public class Caixa {
    private final List<Estrutural> componentes;
    private final List<MateriaPrima> materiasPrima;
    private final Map<Class<? extends Estrutural>, List<MateriaPrima>> acabamentosPorTipo;

    public Caixa() {
        this.acabamentosPorTipo = new HashMap<>();
        this.materiasPrima = new ArrayList<>();
        this.componentes = new ArrayList<>();
    }

    public void adicionarComponente(Estrutural componente) {
        componentes.add(componente);
    }

    public void adicionarAcabamento(MateriaPrima materiaPrima) {
        this.materiasPrima.add(materiaPrima);
    }

    public void definirAcabamentoPorTipo(Class<? extends Estrutural> tipoComponente, List<MateriaPrima> materiasPrima) {
        acabamentosPorTipo.put(tipoComponente, materiasPrima);
    }

    public void aplicarAcabamentos() {
        for (Estrutural componente : componentes) {
            List<MateriaPrima> acabamentosEspecificos = acabamentosPorTipo.get(componente.getClass());
            if (acabamentosEspecificos != null) {
                componente.adicionarAcabamentos(acabamentosEspecificos);
            }
        }
    }

    public void aplicarEstrategia(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes,
                                  PadraoDeFitagem padraoDeFitagem) {
        for (Estrutural componente : componentes) {
            componente.aceitar(estrategia, dimensoes);
        }
    }

    public List<Estrutural> componentes() {
        return componentes;
    }
}