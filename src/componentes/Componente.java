package componentes;


import estrategias.EstrategiaDeConstrucao;
import estrategias.EstrategiaDePrecificacao;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import java.util.List;

public interface Componente {
    void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes);
    void calcularAcabamentos(EstrategiaDePrecificacao precificacao);
    void adicionarAcabamentos(List<MateriaPrima> novosAcabamentos);
    String getDescricao();
    String getPrecificacao();
    void setPrecificacao(String precificacao);
    double getArea();
    double getMetragemLinear();
    List<MateriaPrima> getMateriasPrima();
    PadraoDeFitagem getPadraoDeFitagem();
}