package modelos;

import static materiaPrima.acabamento.Unidade.METRO_QUADRADO;

import componentes.Componente;
import componentes.Estrutural;
import componentes.Fechamento;
import componentes.PadraoDeFitagem;
import componentes.config.Dimensoes;
import componentes.estruturais.Caixa;
import componentes.fechamentos.Gaveteiro;
import componentes.fechamentos.Portas;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import materiaPrima.acabamento.Unidade;
import materiaPrima.acessorios.Acessorio;
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

    }

    public void adicionarComponenteEstrutural(Estrutural componente) {
        caixa.adicionarComponente(componente);
    }

    public void adicionarFechamento(Fechamento fechamento) {
        this.fechamento = Optional.of(fechamento);
    }

    public void adicionarFerragem(Acessorio ferragem, Double quantidade) {
        ferragens.merge(ferragem, quantidade, Double::sum);
    }

    public void removerFerragem(Acessorio ferragem, Double quantidade) {
        ferragens.computeIfPresent(ferragem, (key, value) -> value - quantidade);
    }

    public void alterarFerragem(Acessorio ferragem, Double quantidade) {
        ferragens.computeIfPresent(ferragem, (key, value) -> quantidade);
    }


    public void adicionarPuxador(Puxador puxador) {
        fechamento.ifPresentOrElse(fechamento -> {
                    if (fechamento instanceof Portas portas) {
                        portas.definirPuxador(puxador, this);
                        this.puxador = portas.getPuxador();
                    }
                    if (fechamento instanceof Gaveteiro gaveteiro) {
                        gaveteiro.definirPuxador(puxador, this);
                        this.puxador = gaveteiro.getPuxador();
                    }
                },
                () -> {
                    throw new IllegalArgumentException("Não é possível adicionar puxador a um gabinete sem fechamento");
                });
    }


    public void aplicarEstrategiaComDimensoes() {
        dimensoes.ifPresent(dimensoes -> {
            caixa.aplicarEstrategia(estrategiaDeConstrucao, dimensoes, padraoDeFitagem);
            fechamento.ifPresent(value -> value.aceitar(estrategiaDeConstrucao, dimensoes));
        });
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

            if (fechamento instanceof Gaveteiro gaveteiro) {
                gaveteiro.gavetas().forEach(gaveta -> {
                    gaveta.adicionarAcabamentos(acabamentoFechamento);
                    gaveta.corpoGaveta().ifPresent(corpoGaveta -> {
                        corpoGaveta.componentes().forEach(componente -> {
                            Class<? extends Estrutural> tipoComponente = componente.getClass();

                            List<MateriaPrima> acabamentosEspecificos = acabamentosCaixaEspecificos.get(tipoComponente);

                            if (acabamentosEspecificos != null && !acabamentosEspecificos.isEmpty()) {
                                // Se existirem acabamentos específicos, aplica-os
                                componente.adicionarAcabamentos(acabamentosEspecificos);
                            } else if (acabamentoCaixaPadrao != null && !acabamentoCaixaPadrao.isEmpty()) {
                                // Se não, aplica todos os acabamentos padrão (se houver)
                                componente.adicionarAcabamentos(acabamentoCaixaPadrao);
                            }
                        });
                    });
                });

            } else if (fechamento instanceof Portas portas) {
                portas.portas().forEach(porta -> {
                    porta.adicionarAcabamentos(acabamentoFechamento);
                });
            }
        });
        getQuantidadePorMaterial();

    }

    private void getQuantidadePorMaterial() {
        Map<MateriaPrima, Double> quantidadePorMaterial = new HashMap<>();

        caixa.componentes().forEach(componente ->
                componente.getMateriasPrima().forEach(materiaPrima ->
                        atualizarQuantidadePorMaterial(quantidadePorMaterial, materiaPrima,
                                calcularQuantidade(componente, materiaPrima))
                ));

        fechamento.ifPresent(fechamento -> {
            if (fechamento instanceof Gaveteiro gaveteiro) {
                processarGaveteiro(gaveteiro, quantidadePorMaterial);
            } else if (fechamento instanceof Portas portas) {
                processarPortas(portas, quantidadePorMaterial);
            }
        });

        this.acabamentos.putAll(quantidadePorMaterial);
    }

    private void processarGaveteiro(Gaveteiro gaveteiro, Map<MateriaPrima, Double> quantidadePorMaterial) {
        gaveteiro.gavetas().forEach(gaveta -> {
            List<MateriaPrima> materiasPrimasParaRemover = new ArrayList<>();

            gaveta.getMateriasPrima().forEach(materiaPrima -> {
                if (materiaPrima instanceof Puxador) {
                    materiasPrimasParaRemover.add(materiaPrima);
                }
            });

            materiasPrimasParaRemover.forEach(gaveta.getMateriasPrima()::remove);

            gaveta.getMateriasPrima().forEach(materiaPrima ->
                    atualizarQuantidadePorMaterial(quantidadePorMaterial, materiaPrima,
                            calcularQuantidade(gaveta, materiaPrima))
            );
            gaveta.corpoGaveta().ifPresent(corpoGaveta ->
                    corpoGaveta.componentes().forEach(componente ->
                            corpoGaveta.getMateriasPrima().forEach(materiaPrima ->
                                    atualizarQuantidadePorMaterial(quantidadePorMaterial, materiaPrima,
                                            calcularQuantidade(componente, materiaPrima))
                            )
                    )
            );
        });
    }

    private void processarPortas(Portas portas, Map<MateriaPrima, Double> quantidadePorMaterial) {
        portas.portas().forEach(porta -> {
            List<MateriaPrima> materiasPrimasParaRemover = new ArrayList<>();

            porta.getMateriasPrima().forEach(materiaPrima -> {
                if (materiaPrima instanceof Puxador) {
                    materiasPrimasParaRemover.add(materiaPrima);
                }
            });

            materiasPrimasParaRemover.forEach(porta.getMateriasPrima()::remove);

            porta.getMateriasPrima().forEach(materiaPrima ->
                    atualizarQuantidadePorMaterial(quantidadePorMaterial, materiaPrima,
                            calcularQuantidade(porta, materiaPrima))
            );
        });
    }

    private double calcularQuantidade(Componente componente, MateriaPrima materiaPrima) {

        if (!(materiaPrima instanceof Puxador) && (materiaPrima.getUnidade() == METRO_QUADRADO)) {
            return componente.getArea();
        } else if (!(materiaPrima instanceof Puxador) && (materiaPrima.getUnidade() == Unidade.METRO_LINEAR)) {
            return componente.getMetragemLinear();
        }
        return 0;
    }

    private void atualizarQuantidadePorMaterial(Map<MateriaPrima, Double> map, MateriaPrima chave,
                                                double quantidade) {
        map.merge(chave, quantidade, Double::sum);
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