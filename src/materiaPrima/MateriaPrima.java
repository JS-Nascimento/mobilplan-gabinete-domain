package materiaPrima;

import materiaPrima.acabamento.Unidade;

public interface MateriaPrima {

    String getDescricao();
    String getCor();
    Unidade getUnidade();
    double getPreco();
}
