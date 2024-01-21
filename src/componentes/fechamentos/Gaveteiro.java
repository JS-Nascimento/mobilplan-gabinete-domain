package componentes.fechamentos;

import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import componentes.Dimensoes;
import componentes.estruturais.Gaveta;
import componentes.Estrutural;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.List;

public class Gaveteiro implements Estrutural {
    private final int quantidadeDeGavetas;
    private final FolgasGavetas folgaDasGavetas;
    private final List<Double> alturasDasGavetas;
    private PadraoDeFitagem padraoDeFitagem;
    private double espessura;
    private final List<Gaveta> gavetas;
    private final String descricao;


    public Gaveteiro(int quantidadeDeGavetas, FolgasGavetas folgaDasGavetas, List<Double> alturasDasGavetas) {
        this.quantidadeDeGavetas = quantidadeDeGavetas;
        this.folgaDasGavetas = folgaDasGavetas;
        this.alturasDasGavetas = alturasDasGavetas;

        this.gavetas = new ArrayList<>();
        this.descricao = "Gaveteiro com " + quantidadeDeGavetas + " gavetas";
    }

    private void calcularAlturaDaGaveta(Dimensoes dimensoes) {

        alturasDasGavetas.forEach(altura -> {
            Gaveta gaveta = new Gaveta(dimensoes, this.folgaDasGavetas,
                    (altura - this.folgaDasGavetas.corpoEmRelacaoFrente()));
            gavetas.add(gaveta);
        });
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {

        calcularAlturaDaGaveta(dimensoes);
        this.espessura = dimensoes.getEspessura();

        for (Gaveta gaveta : gavetas) {
            gaveta.aceitar(estrategia, dimensoes);
        }
    }

    @Override
    public void calcularAcabamentos(EstrategiaDePrecificacao precificacao) {

    }

    @Override
    public void adicionarAcabamentos(List<MateriaPrima> materiaPrimas) {

    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String getPrecificacao() {
        return null;
    }

    @Override
    public void setPrecificacao(String precificacao) {

    }

    @Override
    public double getArea() {
        return 0;
    }

    public List<Gaveta> getGavetas() {
        return gavetas;
    }

    @Override
    public double getMetragemLinear() {
        return 0;
    }

    @Override
    public List<MateriaPrima> getMateriasPrima() {
        return null;
    }

    @Override
    public PadraoDeFitagem getPadraoDeFitagem() {
        return padraoDeFitagem;
    }
}

