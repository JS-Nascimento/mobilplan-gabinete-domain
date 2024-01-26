package componentes;


import componentes.config.Dimensoes;
import estrategias.EstrategiaDeConstrucao;
import materiaPrima.MateriaPrima;
import java.util.List;

public interface Componente {
    void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes);
    void adicionarAcabamentos(List<MateriaPrima> novosAcabamentos);
    String getDescricao();
    double getArea();
    double getMetragemLinear();
    List<MateriaPrima> getMateriasPrima();
    PadraoDeFitagem getPadraoDeFitagem();
    double altura();
    double largura();
    double profundidade();
    double espessura();

}