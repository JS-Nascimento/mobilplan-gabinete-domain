import static materiaPrima.acabamento.Unidade.UNIDADE;

import componentes.FolgasGavetas;
import componentes.fechamentos.FrenteDeGaveta;
import componentes.fechamentos.TipoFrente;
import estrategias.BaseEntreLaterais;
import java.util.Comparator;
import materiaPrima.MateriaPrima;
import materiaPrima.acabamento.Acabamento;
import materiaPrima.acabamento.CalculaPorLado;
import materiaPrima.acabamento.DimensoesChapa;
import materiaPrima.acabamento.FitaDeBorda;
import materiaPrima.acabamento.Mdf;
import materiaPrima.acabamento.TipoAcabamento;
import java.util.ArrayList;
import materiaPrima.TipoPrecificacao;
import componentes.Dimensoes;
import componentes.Folgas;
import componentes.Gabinete;
import componentes.PadraoDeFitagem;
import componentes.estruturais.Base;
import componentes.estruturais.Fundo;
import componentes.estruturais.Lateral;
import componentes.estruturais.PrateleiraInterna;
import componentes.estruturais.TipoFundo;
import componentes.estruturais.TipoPrateleira;
import componentes.estruturais.Travessa;
import componentes.fechamentos.Porta;
import componentes.fechamentos.TipoPorta;
import java.util.List;
import materiaPrima.acabamento.Unidade;
import materiaPrima.acessorios.Ferragem;
import proposta.item.Item;

public class Main {
    public static void main(String[] args) {

        Gabinete gabinete = new Gabinete(new BaseEntreLaterais());

        gabinete.adicionarComponenteEstrutural(new Base(PadraoDeFitagem.UMA_ALTURA));
        gabinete.adicionarComponenteEstrutural(new Lateral(PadraoDeFitagem.UMA_ALTURA));
        gabinete.adicionarComponenteEstrutural(new Lateral(PadraoDeFitagem.UMA_ALTURA));
        gabinete.adicionarComponenteEstrutural(new Travessa(75, PadraoDeFitagem.UMA_ALTURA));
        gabinete.adicionarComponenteEstrutural(new Travessa(75, PadraoDeFitagem.UMA_ALTURA));
        gabinete.adicionarComponenteEstrutural(new Fundo(TipoFundo.ENCAIXADO, 6, 10, PadraoDeFitagem.NENHUM));
        gabinete.adicionarComponenteEstrutural(new PrateleiraInterna(TipoPrateleira.MOVEL,
                18, new Folgas(30), PadraoDeFitagem.UMA_ALTURA));

        gabinete.adicionarFechamento(new Porta(TipoPorta.PORTA_DUPLA,
                18,
                new Folgas(3, 3, 3, 3, 25),
                PadraoDeFitagem.QUATRO_LADOS
        ));

        var mdfCaixa = new Mdf(
                "MDF TX",
                "Branco 15mm",
                CalculaPorLado.UMA_FACE,
                new DimensoesChapa(2750, 1850, 15),
                60.0

        );
        var mdfCaixa18 = new Mdf(
                "MDF TX",
                "Branco 18mm",
                CalculaPorLado.UMA_FACE,
                new DimensoesChapa(2750, 1850, 18),
                75.55

        );

        var mdfFundo = new Mdf(
                "MDF TX",
                "Branco 6mm",
                CalculaPorLado.UMA_FACE,
                new DimensoesChapa(2750, 1850, 6),
                35.0

        );

        var fitaBordas = new FitaDeBorda(
                "Fita de Borda 16mm",
                "Branco Tx",
                16.0,
                0.85);

        var fitaBordas22 = new FitaDeBorda(
                "Fita de Borda 22mm",
                "Branco Tx",
                22.0,
                1.10);

        var ferragem = new Ferragem(
                "Kit Parafuso Montagem",
                "Bicromatizado",
                UNIDADE,
                5.0

        );
        var dobradica = new Ferragem(
                "Dobradica 35mm",
                "Inox",
                UNIDADE,
                32.50

        );

        gabinete.definirDimensoes(new Dimensoes(500, 800, 400, 15));
        gabinete.adicionarFerragem(ferragem, 4.0);
        gabinete.adicionarFerragem(dobradica, 6.0);
        gabinete.definirAcabamentosCaixa(List.of(mdfCaixa, fitaBordas));
        gabinete.definirAcabamentosFrente(List.of(mdfCaixa18, fitaBordas22));
        gabinete.definirAcabamentoCaixaEspecifico(Fundo.class, List.of(mdfFundo));

        var item = new Item(gabinete);
        System.out.println(item.toString());

        System.out.println("\n--------------------------------------------------\n");

        Gabinete gaveteiro = new Gabinete(new BaseEntreLaterais());

        gaveteiro.adicionarComponenteEstrutural(new Base(PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new Lateral(PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new Lateral(PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new Travessa(75, PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new Travessa(75, PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new Fundo(TipoFundo.ENCAIXADO, 6, 10, PadraoDeFitagem.NENHUM));

        var frentes = new ArrayList<Double>();
        frentes.add(120.0);
        frentes.add(155.0);
        frentes.add(155.0);
////        frentes.add(155.0);
//        //TODO: corrigir a calculo de frentes de gaveta
        gaveteiro.adicionarFechamento(
                new FrenteDeGaveta(
                        new Folgas(3, 3, 3, 3, 3),
                        TipoFrente.NORMAL,
                        4,
                        frentes,
                        new FolgasGavetas(TipoFundo.ENCAIXADO, 26, 350, 30, 15, 6, 10, PadraoDeFitagem.UMA_ALTURA),
                        PadraoDeFitagem.QUATRO_LADOS, 18));

        gaveteiro.definirDimensoes(new Dimensoes(400, 800, 560, 15));
        gaveteiro.definirAcabamentosCaixa(List.of(mdfCaixa, fitaBordas));
        gabinete.definirAcabamentosFrente(List.of(mdfCaixa18, fitaBordas22));
        gaveteiro.definirAcabamentoCaixaEspecifico(Fundo.class, List.of(mdfFundo));
        var item2 = new Item(gaveteiro);
        System.out.println(item2.toString());


    }
}