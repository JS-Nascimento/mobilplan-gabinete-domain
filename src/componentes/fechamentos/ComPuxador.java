package componentes.fechamentos;

import componentes.Fechamento;
import componentes.Gabinete;
import componentes.PadraoDeFitagem;
import materiaPrima.acessorios.Puxador;

public interface ComPuxador extends Fechamento {

    void setDimensoes(double altura, double largura, double espessura, PadraoDeFitagem padraoDeFitagem);
}
