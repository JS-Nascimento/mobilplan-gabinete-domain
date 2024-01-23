package componentes.fechamentos;

import componentes.AbstractComponente;
import componentes.Dimensoes;
import componentes.estruturais.CorpoGaveta;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.List;

public class Gaveteiro extends AbstractComponente {
    private final int quantidadeDeGavetas;
    private final FolgasGavetas folgaDasGavetas;
    private final List<Double> alturasDasGavetas;
    private PadraoDeFitagem padraoDeFitagem;
    private double espessura;
    private final List<CorpoGaveta> corpoGavetas;
    private final String descricao;


    public Gaveteiro(int quantidadeDeGavetas, FolgasGavetas folgaDasGavetas, List<Double> alturasDasGavetas) {
        super(null);
        this.quantidadeDeGavetas = quantidadeDeGavetas;
        this.folgaDasGavetas = folgaDasGavetas;
        this.alturasDasGavetas = alturasDasGavetas;

        this.corpoGavetas = new ArrayList<>();
        this.descricao = "Gaveteiro com " + quantidadeDeGavetas + " gavetas";
    }

    private void calcularAlturaDaGaveta(Dimensoes dimensoes) {

        alturasDasGavetas.forEach(altura -> {
            CorpoGaveta corpoGaveta = new CorpoGaveta(dimensoes, this.folgaDasGavetas,
                    (altura - this.folgaDasGavetas.corpoEmRelacaoFrente()));
            corpoGavetas.add(corpoGaveta);
        });
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {

        calcularAlturaDaGaveta(dimensoes);
        this.espessura = dimensoes.getEspessura();

        for (CorpoGaveta corpoGaveta : corpoGavetas) {
            corpoGaveta.aceitar(estrategia, dimensoes);
        }
    }

    public List<CorpoGaveta> getGavetas() {
        return corpoGavetas;
    }
}

