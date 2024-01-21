package componentes;


import estrategias.EstrategiaDeConstrucao;
import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import java.util.List;

public interface Componente {
    void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes);
    void adicionarAcabamentos(List<MateriaPrima> novosAcabamentos);
    String getDescricao();
    double getArea();
    double getMetragemLinear();
    List<MateriaPrima> getMateriasPrima();
    PadraoDeFitagem getPadraoDeFitagem();
}