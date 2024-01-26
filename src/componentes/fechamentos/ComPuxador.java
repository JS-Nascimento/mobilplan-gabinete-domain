package componentes.fechamentos;

import componentes.Fechamento;
import componentes.PadraoDeFitagem;

public interface ComPuxador extends Fechamento {

    void setDimensoes(double altura, double largura, double espessura, PadraoDeFitagem padraoDeFitagem);
}
