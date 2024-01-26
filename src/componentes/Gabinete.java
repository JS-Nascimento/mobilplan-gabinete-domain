package componentes;

import componentes.config.Dimensoes;
import componentes.estruturais.Caixa;
import componentes.fechamentos.Gavetas;
import componentes.fechamentos.Portas;
import componentes.fechamentos.TipoPuxador;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import materiaPrima.acessorios.Acessorio;
import materiaPrima.acessorios.Ferragem;
import materiaPrima.acessorios.Puxador;

public class Gabinete {
    private String descricao;
    private final Caixa caixa;
    private Optional<Fechamento> fechamento;
    private final EstrategiaDeConstrucao estrategiaDeConstrucao;
    private Optional<Dimensoes> dimensoes;
    private Optional<Dimensoes> dimensoesInternas;
    private final List<MateriaPrima> acabamentoCaixaPadrao;
    private final List<MateriaPrima> acabamentoFechamento;
    private final Map<Acessorio, Double> ferragens;
    private Optional<Puxador> puxador;
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
        this.puxador = Optional.empty();
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

    public void adicionarFerragem(Acessorio ferragem, Double quantidade) {
        ferragens.put(ferragem, quantidade);
    }

    public void adicionarPuxador(Puxador puxador) {
        fechamento.ifPresentOrElse(fechamento -> {
                    if (fechamento instanceof Portas portas) {
                        portas.definirPuxador(puxador, this);
                        this.puxador = portas.getPuxador();
                    }
                    //TODO: Implementar para gavetas
                },
                () -> {
                    throw new IllegalArgumentException("Não é possível adicionar puxador a um gabinete sem fechamento");
                });
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

        fechamento.ifPresent(fechamento -> {

            if (fechamento instanceof Gavetas gavetas) {
                gavetas.frentes().forEach(frente -> {
                    frente.adicionarAcabamentos(acabamentoFechamento);
                });
                gavetas.corpoGavetas().forEach(corpo -> {
                    for (Estrutural componente : corpo.componentes()) {
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
                });
            } else if (fechamento instanceof Portas portas) {

                portas.portas().forEach(porta -> {
                    porta.adicionarAcabamentos(acabamentoFechamento);
                });
                //fechamento.adicionarAcabamentos(acabamentoFechamento);
            }
        });
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
                        quantidadePorMaterial.put(materiaPrima,
                                quantidadePorMaterial.get(materiaPrima) + quantidade);
                    } else {
                        quantidadePorMaterial.put(materiaPrima, quantidade);
                    }
                }));

        fechamento.ifPresent(fechamento -> {
            if (fechamento instanceof Gavetas gavetas) {
                gavetas.frentes().forEach(frente -> {
                    frente.getMateriasPrima().forEach(materiaPrima -> {
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

                gavetas.corpoGavetas().forEach(corpo -> {
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
            if (fechamento instanceof Portas portas) {
                portas.portas().forEach( porta -> {
                    portas.getMateriasPrima().forEach(materiaPrima -> {
                        double quantidade = 0.0;
                        switch (materiaPrima.getUnidade()) {
                            case METRO_QUADRADO -> quantidade = porta.getArea();
                            case METRO_LINEAR -> quantidade = porta.getMetragemLinear();
                        }
                        if (quantidadePorMaterial.containsKey(materiaPrima)) {
                            quantidadePorMaterial.put(materiaPrima,
                                    quantidadePorMaterial.get(materiaPrima) + quantidade);
                        } else {
                            quantidadePorMaterial.put(materiaPrima, quantidade);
                        }
                    });
                });

//                fechamento.getMateriasPrima().forEach(materiaPrima -> {
//                    double quantidade = 0.0;
//                    switch (materiaPrima.getUnidade()) {
//                        case METRO_QUADRADO -> quantidade = fechamento.getArea();
//                        case METRO_LINEAR -> quantidade = fechamento.getMetragemLinear();
//                    }
//                    if (quantidadePorMaterial.containsKey(materiaPrima)) {
//                        quantidadePorMaterial.put(materiaPrima,
//                                quantidadePorMaterial.get(materiaPrima) + quantidade);
//                    } else {
//                        quantidadePorMaterial.put(materiaPrima, quantidade);
//                    }
//
//                });
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

    public Map<Acessorio, Double> ferragens() {
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