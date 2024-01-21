package componentes.estruturais;

import estrategias.EstrategiaDeConstrucao;
import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.PadraoDeFitagem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Caixa {
    private final List<Estrutural> componentes;
    private final List<MateriaPrima> materiasPrima;
    private final Map<Class<? extends Estrutural>, List<MateriaPrima>> acabamentosPorTipo;
    private double areaTotal;
    private double metragemFitaTotal;
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
    public List<String> listarComponentes() {
        List<String> descricaoComponentes = new ArrayList<>();
        for (Estrutural componente : componentes) {
            descricaoComponentes.add(componente.getDescricao()
                    + " - " + componente.getPrecificacao());
        }
        return descricaoComponentes;
    }

    public void aplicarEstrategia(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes, PadraoDeFitagem padraoDeFitagem) {
        for (Estrutural componente : componentes) {
            componente.aceitar(estrategia, dimensoes);
        }
        calcularTotal();
    }

    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {
        for (Estrutural componente : componentes) {
            componente.calcularAcabamentos(precificacao );
        }
    }

    private void calcularTotal() {
            this.areaTotal = 0.0;
            this.metragemFitaTotal = 0.0;
        if (componentes.isEmpty()) {
            return;
        }

        for (Estrutural componente : componentes) {
            this.areaTotal += componente.getArea();
            this.metragemFitaTotal += componente.getMetragemLinear();
        }
    }

    public double areaTotal() {
        return areaTotal;
    }

    public double metragemFitaTotal() {
        return metragemFitaTotal;
    }

    public List<Estrutural> componentes() {
        return componentes;
    }
}