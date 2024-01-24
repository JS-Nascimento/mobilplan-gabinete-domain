package componentes;

import componentes.estruturais.Caixa;
import componentes.fechamentos.Gaveta;
import componentes.fechamentos.Porta;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import materiaPrima.acessorios.Ferragem;

public class Gabinete {
    private String descricao;
    private final Caixa caixa;
    private Optional<Fechamento> fechamento;
    private final EstrategiaDeConstrucao estrategiaDeConstrucao;
    private Optional<Dimensoes> dimensoes;
    private Optional<Dimensoes> dimensoesInternas;
    private final List<MateriaPrima> acabamentoCaixaPadrao;
    private final List<MateriaPrima> acabamentoFechamento;
    private final Map<Ferragem, Double> ferragens;
    private final Map<MateriaPrima, Double> acabamentos;
    private final Map<Class<? extends Estrutural>, List<MateriaPrima>> acabamentosCaixaEspecificos;
    private PadraoDeFitagem padraoDeFitagem;

    public Gabinete(EstrategiaDeConstrucao estrategia) {
        this.caixa = new Caixa();
        this.fechamento = Optional.empty();
        this.dimensoes = Optional.empty();
        this.dimensoesInternas = Optional.empty();
        this.estrategiaDeConstrucao = estrategia;
        this.ferragens = new HashMap<>();
        this.acabamentos = new HashMap<>();
        this.acabamentoCaixaPadrao = new ArrayList<>();
        this.acabamentosCaixaEspecificos = new HashMap<>();
        this.acabamentoFechamento = new ArrayList<>();
    }

    public void definirDimensoes(Dimensoes novasDimensoes) {
        this.dimensoes = Optional.of(novasDimensoes);
        this.dimensoesInternas = Optional.of(novasDimensoes.calcularDimensoesInternas());
        this.aplicarEstrategiaComDimensoes();
    }

    public void adicionarComponenteEstrutural(Estrutural componente) {
        caixa.adicionarComponente(componente);
    }

    public void adicionarFechamento(Fechamento fechamento) {
        this.fechamento = Optional.of(fechamento);
    }

    public void adicionarFerragem(Ferragem ferragem, Double quantidade) {
        ferragens.put(ferragem, quantidade);
    }


    private void aplicarEstrategiaComDimensoes() {
        if (dimensoes.isPresent()) {
            fechamento.ifPresent(value -> value.aceitar(estrategiaDeConstrucao, dimensoes.get()));
            caixa.aplicarEstrategia(estrategiaDeConstrucao, dimensoes.get(), padraoDeFitagem);
        }
    }

    public void definirAcabamentosCaixa(List<? extends Acabamento> novosAcabamentos) {

        for (MateriaPrima materiaPrima : novosAcabamentos) {
            if (!acabamentoCaixaPadrao.contains(materiaPrima)) {
                acabamentoCaixaPadrao.add(materiaPrima);
            }
        }

    }

    public void definirAcabamentosFrente(List<? extends Acabamento> novosAcabamentos) {

        for (MateriaPrima materiaPrima : novosAcabamentos) {
            if (!acabamentoFechamento.contains(materiaPrima)) {
                acabamentoFechamento.add(materiaPrima);
            }
        }

    }

    public void definirAcabamentoCaixaEspecifico(Class<? extends Estrutural> componente,
                                                 List<? extends Acabamento> acabamentos) {

        var acabamentosExistentes = acabamentosCaixaEspecificos.getOrDefault(componente, new ArrayList<>());

        for (Acabamento acabamento : acabamentos) {
            if (!acabamentosExistentes.contains(acabamento)) {
                acabamentosExistentes.add(acabamento);
            }
        }

        acabamentosCaixaEspecificos.put(componente, acabamentosExistentes);
    }

    public void aplicarAcabamentos() {
        for (Estrutural componente : caixa.componentes()) {
            Class<? extends Estrutural> tipoComponente = componente.getClass();

            List<MateriaPrima> acabamentosEspecificos = acabamentosCaixaEspecificos.get(tipoComponente);

            if (acabamentosEspecificos != null && !acabamentosEspecificos.isEmpty()) {
                // Se existirem acabamentos específicos, aplica-os
                componente.adicionarAcabamentos(acabamentosEspecificos);
            } else if (acabamentoCaixaPadrao != null && !acabamentoCaixaPadrao.isEmpty()) {
                // Se não, aplica todos os acabamentos padrão (se houver)
                componente.adicionarAcabamentos(acabamentoCaixaPadrao);
            }
        }

        fechamento.ifPresent(fechamento -> fechamento.adicionarAcabamentos(acabamentoFechamento));

        getQuantidadePorMaterial();
    }

    private void getQuantidadePorMaterial() {

        Map<MateriaPrima, Double> quantidadePorMaterial = new HashMap<>();
        caixa.componentes().forEach(componente ->
                componente.getMateriasPrima().forEach(materiaPrima -> {
                    double quantidade = 0.0;
                    switch (materiaPrima.getUnidade()) {
                        case METRO_QUADRADO -> quantidade = componente.getArea();
                        case METRO_LINEAR -> quantidade = componente.getMetragemLinear();
                    }
                    if (quantidadePorMaterial.containsKey(materiaPrima)) {
                        quantidadePorMaterial.put(materiaPrima, quantidadePorMaterial.get(materiaPrima) + quantidade);
                    } else {
                        quantidadePorMaterial.put(materiaPrima, quantidade);
                    }
                }));

        fechamento.ifPresent(fechamento -> {
            if (fechamento instanceof Gaveta gaveta) {
                gaveta.frentes().forEach(frente -> {
                    fechamento.getMateriasPrima().forEach(materiaPrima -> {
                        double quantidade = 0.0;
                        switch (materiaPrima.getUnidade()) {
                            case METRO_QUADRADO -> quantidade = frente.getArea();
                            case METRO_LINEAR -> quantidade = frente.getMetragemLinear();
                        }
                        if (quantidadePorMaterial.containsKey(materiaPrima)) {
                            quantidadePorMaterial.put(materiaPrima,
                                    quantidadePorMaterial.get(materiaPrima) + quantidade);
                        } else {
                            quantidadePorMaterial.put(materiaPrima, quantidade);
                        }

                    });
                });

                gaveta.corpoGavetas().forEach(corpo -> {
                    corpo.componentes().forEach(componente -> {
                        componente.getMateriasPrima().forEach(materiaPrima -> {
                            double quantidade = 0.0;
                            switch (materiaPrima.getUnidade()) {
                                case METRO_QUADRADO -> quantidade = componente.getArea();
                                case METRO_LINEAR -> quantidade = componente.getMetragemLinear();
                            }
                            if (quantidadePorMaterial.containsKey(materiaPrima)) {
                                quantidadePorMaterial.put(materiaPrima,
                                        quantidadePorMaterial.get(materiaPrima) + quantidade);
                            } else {
                                quantidadePorMaterial.put(materiaPrima, quantidade);
                            }
                        });
                    });
                });
            }
            if (fechamento instanceof Porta porta) {

                fechamento.getMateriasPrima().forEach(materiaPrima -> {
                    double quantidade = 0.0;
                    switch (materiaPrima.getUnidade()) {
                        case METRO_QUADRADO -> quantidade = fechamento.getArea();
                        case METRO_LINEAR -> quantidade = fechamento.getMetragemLinear();
                    }
                    if (quantidadePorMaterial.containsKey(materiaPrima)) {
                        quantidadePorMaterial.put(materiaPrima,
                                quantidadePorMaterial.get(materiaPrima) + quantidade);
                    } else {
                        quantidadePorMaterial.put(materiaPrima, quantidade);
                    }

                });
            }

        });

        this.acabamentos.putAll(quantidadePorMaterial);
    }

    public Optional<Dimensoes> getDimensoesInternas() {
        return dimensoesInternas;
    }

    public Optional<Dimensoes> getDimensoes() {
        return dimensoes;
    }

    public Caixa caixa() {
        return caixa;
    }

    public Optional<Fechamento> fechamento() {
        return fechamento;
    }

    public Map<Ferragem, Double> ferragens() {
        return ferragens;
    }

    public Map<MateriaPrima, Double> acabamentos() {
        return acabamentos;
    }

    public String descricao() {
        String descricao = "Gabinete - ";

        if (fechamento.isPresent()) {
            descricao = descricao + fechamento.get().getDescricaoCurta() + " - ";
        }
        if (dimensoes.isPresent()) {
            descricao =
                    descricao + dimensoes.get().getLargura() + "mm x " + dimensoes.get().getProfundidade() +
                            "mm x " +
                            dimensoes.get().getAltura() + "mm";
        }

        return descricao;
    }
}