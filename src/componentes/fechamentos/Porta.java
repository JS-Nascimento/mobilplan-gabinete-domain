package componentes.fechamentos;

import static helpers.FitaHelper.calcularMetragemFita;

import componentes.AbstractComponenteFechamento;
import modelos.Gabinete;
import componentes.PadraoDeFitagem;
import componentes.config.Dimensoes;
import componentes.config.Folgas;
import estrategias.EstrategiaDeConstrucao;
import java.util.Optional;
import materiaPrima.acessorios.Puxador;

public class Porta extends AbstractComponenteFechamento implements ComPuxador{
    private final TipoPorta tipoPorta;
    private final Folgas folgas;


    public Porta(double altura, double largura, double espessura, PadraoDeFitagem padraoDeFitagem, TipoPorta tipoPorta,
                 Folgas folgas, Puxador puxador) {
        super(padraoDeFitagem);
        this.tipoPorta = tipoPorta;
        this.puxador = Optional.ofNullable(puxador);
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
        this.folgas = folgas;
    }

    @Override
    public void aceitar(EstrategiaDeConstrucao estrategia, Dimensoes dimensoes) {
        //Processar desconto do puxador
        estrategia.aplicarParaPuxador(this, dimensoes, gabinete);
        aplicarPuxador();
    }

    public TipoPorta tipoPorta() {
        return tipoPorta;
    }

    private void aplicarPuxador() {

        puxador.ifPresent(puxador -> {
            if (!this.materiaPrimas().contains(puxador)){
                this.materiaPrimas().add(puxador);
            }
        });

    }

    public Folgas folgas() {
        return folgas;
    }

    @Override
    public Optional<Puxador> getPuxador() {
        return puxador;
    }

    public void definirPuxador(Puxador novoPuxador) {
        this.puxador = Optional.ofNullable(novoPuxador);
    }
    @Override
    public void setDimensoes(double altura, double largura, double espessura,
                             PadraoDeFitagem padraoDeFitagem) {
        this.altura = altura;
        this.largura = largura;
        this.espessura = espessura;
        this.area = (altura * largura);
        this.metragemFita = calcularMetragemFita(altura, largura, padraoDeFitagem);

        StringBuilder sb = new StringBuilder();
        sb.append("Porta ")
                .append(tipoPorta.toString());

        puxador.ifPresent(puxador -> {
            sb.append(" - ");
            sb.append(puxador.getTipoPuxador().toString());
        });

        this.descricao = sb.toString();
    }
    public void setGabinete(Gabinete gabinete) {
        this.gabinete = gabinete;
    }
}
