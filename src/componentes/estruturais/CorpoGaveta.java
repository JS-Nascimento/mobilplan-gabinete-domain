package componentes.estruturais;


import componentes.AbstractComponente;
import componentes.Dimensoes;
import componentes.Estrutural;
import componentes.FolgasGavetas;
import componentes.PadraoDeFitagem;
import estrategias.EstrategiaDeConstrucao;
import java.util.ArrayList;
import java.util.List;

public class CorpoGaveta extends AbstractComponente {
    private final List<Estrutural> componentesEstruturais;
    private final FolgasGavetas folgas;

    public CorpoGaveta(Dimensoes dimensoesGabinete, FolgasGavetas folgas, double altura) {

        super(folgas.padraoDeFitagem());
        this.folgas = folgas;
        this.altura = altura;
        this.espessura =  folgas.espessuraCorpo();
        this.componentesEstruturais = new ArrayList<>();
        this.largura = 350.0;

        this.componentesEstruturais.add(new FundoGaveta(folgas, PadraoDeFitagem.NENHUM));
        this.componentesEstruturais.add(new LateralGaveta(folgas,
                new Dimensoes(largura, altura, 0.0,this.espessura),
                padraoDeFitagem));
        this.componentesEstruturais.add(new LateralGaveta(folgas,
                new Dimensoes(largura, altura, folgas.profundidadeGaveta(), this.espessura),
                padraoDeFitagem));
        this.componentesEstruturais.add(new ContraFrenteGaveta(altura, folgas));

        this.componentesEstruturais.add(new TraseiroGaveta(altura, folgas));

        this.descricao = "Gaveta com folga de " + folgas + "mm";
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        for (Estrutural componente : componentesEstruturais) {
            componente.aceitar(estrategia, dimensoes);
        }
    }
    public void setDimensoes(double altura, double largura, double profundidade, double espessura,
                             PadraoDeFitagem padraoDeFitagem) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.espessura = espessura;
        this.altura = altura;
        //TODO: Refatorar para usar o padraoDeFitagem e metragemFita
    }

    public String listarComponentes() {

        StringBuilder descricaoComponentes = new StringBuilder();
        for (Estrutural componente : componentesEstruturais) {
            descricaoComponentes.append(" --> ")
                    .append(componente.getDescricao())
                    .append("\n");
        }
        return descricaoComponentes.toString();
    }
    public List<Estrutural> componentes() {
        return componentesEstruturais;
    }
}

