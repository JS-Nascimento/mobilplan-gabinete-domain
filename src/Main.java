import static materiaPrima.acabamento.Unidade.PAR;
import static materiaPrima.acabamento.Unidade.UNIDADE;

import componentes.PadraoDeFitagem;
import componentes.config.ConfigFabricacao;
import componentes.config.Dimensoes;
import componentes.config.DimensoesAcessorio;
import componentes.estruturais.Base;
import componentes.estruturais.Fundo;
import componentes.estruturais.FundoGaveta;
import componentes.estruturais.Lateral;
import componentes.estruturais.PrateleiraInterna;
import componentes.estruturais.TipoFundo;
import componentes.estruturais.TipoPrateleira;
import componentes.estruturais.TravessaHorizontal;
import componentes.fechamentos.Gaveteiro;
import componentes.fechamentos.Portas;
import componentes.fechamentos.SemComponente;
import componentes.fechamentos.TipoFrente;
import componentes.fechamentos.TipoPorta;
import componentes.fechamentos.TipoPuxador;
import estrategias.BaseEntreLaterais;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import materiaPrima.acabamento.CalculaPorLado;
import materiaPrima.acabamento.DimensoesChapa;
import materiaPrima.acabamento.FitaDeBorda;
import materiaPrima.acabamento.Mdf;
import materiaPrima.acessorios.Direcao;
import materiaPrima.acessorios.Ferragem;
import materiaPrima.acessorios.Puxador;
import modelos.Gabinete;
import proposta.Proposta;
import proposta.ambiente.Ambiente;
import proposta.item.Item;

public class Main {
    public static void main(String[] args) {

        Gabinete gabinete = new Gabinete(new BaseEntreLaterais());

        gabinete.adicionarComponenteEstrutural(new Base(ConfigFabricacao.PADRAO_ACABAMENTO_BASE));
        gabinete.adicionarComponenteEstrutural(new Lateral(ConfigFabricacao.PADRAO_ACABAMENTO_LATERAL));
        gabinete.adicionarComponenteEstrutural(new Lateral(ConfigFabricacao.PADRAO_ACABAMENTO_LATERAL));
        gabinete.adicionarComponenteEstrutural(
                new TravessaHorizontal(ConfigFabricacao.PADRAO_ACABAMENTO_TRAVESSA_HORIZONTAL));
        gabinete.adicionarComponenteEstrutural(
                new TravessaHorizontal(ConfigFabricacao.PADRAO_ACABAMENTO_TRAVESSA_HORIZONTAL));
        gabinete.adicionarComponenteEstrutural(new Fundo(TipoFundo.ENCAIXADO, 6, 10, PadraoDeFitagem.NENHUM));
        gabinete.adicionarComponenteEstrutural(new PrateleiraInterna(TipoPrateleira.FIXA,
                18, ConfigFabricacao.PADRAO_ACABAMENTO_PRATELEIRA_INTERNA));

        gabinete.adicionarFechamento(
                new Portas(
                        TipoPorta.DUPLA,
                        18,
                        PadraoDeFitagem.QUATRO_LADOS,
                        null
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
        var mdfCaixa18Esp = new Mdf(
                "MDF TX ",
                "Carvalho Hannover 18mm",
                CalculaPorLado.UMA_FACE,
                new DimensoesChapa(2750, 1850, 18),
                90.00

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
        var parafusoGaveta = new Ferragem(
                "Parafuso Gaveta 16mmx3,5mm",
                "Bicromatizado",
                UNIDADE,
                0.12

        );
        var dobradica = new Ferragem(
                "Dobradica 35mm",
                "Inox",
                UNIDADE,
                32.50

        );
        var trilhos = new Ferragem(
                "Trilho Telescopico 350mm",
                "Inox",
                PAR,
                60.82

        );

        var puxador = new Puxador(
                false,
                Direcao.HORIZONTAL,
                TipoPuxador.PUXADOR_ALCA,
                "Puxador Haste",
                "Inox Escovado 224mm",
                new DimensoesAcessorio(250, 20, 30),
                9.90
        );
        var puxador2 = new Puxador(
                true,
                Direcao.VERTICAL,
                TipoPuxador.PUXADOR_PERFIL,
                "Perfil Tipo C",
                "Anodizado Fosco 35mm",
                new DimensoesAcessorio(35, 0, 20),
                25.00
        );
        var puxador3 = new Puxador(
                true,
                Direcao.HORIZONTAL,
                TipoPuxador.PUXADOR_PERFIL,
                "Perfil Tipo C",
                "Anodizado Fosco 35mm",
                new DimensoesAcessorio(35, 0, 20),
                25.00
        );

        gabinete.definirDimensoes(new Dimensoes(500, 800, 400, 15));
        gabinete.adicionarFerragem(ferragem, 4.0);
        gabinete.adicionarFerragem(dobradica, 6.0);
        gabinete.adicionarPuxador(puxador2);
        gabinete.definirAcabamentosCaixa(List.of(mdfCaixa18Esp, fitaBordas));
        gabinete.definirAcabamentosFrente(List.of(mdfCaixa18, fitaBordas22));
        gabinete.definirAcabamentoCaixaEspecifico(Fundo.class, List.of(mdfFundo));


        var item = new Item(gabinete);
        System.out.println(item);

        /**
         * Gaveteiro
         */

        Gabinete gaveteiro = new Gabinete(new BaseEntreLaterais());

        gaveteiro.adicionarComponenteEstrutural(new Base(PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new Lateral(PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new Lateral(PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new TravessaHorizontal(PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new TravessaHorizontal(PadraoDeFitagem.UMA_ALTURA));
        gaveteiro.adicionarComponenteEstrutural(new Fundo(TipoFundo.ENCAIXADO, 6, 10, PadraoDeFitagem.NENHUM));


        var frentes = new ArrayList<Double>();
        frentes.add(120.0);
        frentes.add(155.0);
        frentes.add(155.0);
//////        gavetas.add(155.0);'
////        //TODO: corrigir a calculo de gavetas de gavetas
        gaveteiro.adicionarFechamento(
                new Gaveteiro(
                        TipoFrente.NORMAL,
                        4,
                        frentes,
                        PadraoDeFitagem.QUATRO_LADOS,
                        18));

        gaveteiro.definirDimensoes(new Dimensoes(400, 800, 560, 15));
        gaveteiro.definirAcabamentosCaixa(List.of(mdfCaixa, fitaBordas));
        gaveteiro.definirAcabamentosFrente(List.of(mdfCaixa18, fitaBordas22));
        gaveteiro.definirAcabamentoCaixaEspecifico(Fundo.class, List.of(mdfFundo));
        gaveteiro.definirAcabamentoCaixaEspecifico(FundoGaveta.class, List.of(mdfFundo));
        gaveteiro.adicionarFerragem(trilhos, 4.0);
        gaveteiro.adicionarFerragem(parafusoGaveta, 48.0);
        gaveteiro.adicionarPuxador(puxador3);
        var item2 = new Item(gaveteiro);
        System.out.println(item2);


        Gabinete gabineteSemFrente = new Gabinete(new BaseEntreLaterais());

        gabineteSemFrente.adicionarComponenteEstrutural(new Base(ConfigFabricacao.PADRAO_ACABAMENTO_BASE));
        gabineteSemFrente.adicionarComponenteEstrutural(new Base(ConfigFabricacao.PADRAO_ACABAMENTO_BASE));
        gabineteSemFrente.adicionarComponenteEstrutural(new Lateral(ConfigFabricacao.PADRAO_ACABAMENTO_LATERAL));
        gabineteSemFrente.adicionarComponenteEstrutural(new Lateral(ConfigFabricacao.PADRAO_ACABAMENTO_LATERAL));
        gabineteSemFrente.adicionarComponenteEstrutural(new Fundo(TipoFundo.ENCAIXADO, 6, 10, PadraoDeFitagem.NENHUM));
        gabineteSemFrente.adicionarComponenteEstrutural(new PrateleiraInterna(TipoPrateleira.FIXA,
                18, ConfigFabricacao.PADRAO_ACABAMENTO_PRATELEIRA_INTERNA));

        gabineteSemFrente.adicionarFechamento(
                new SemComponente());


        gabineteSemFrente.definirDimensoes(new Dimensoes(350, 800, 400, 15));
        gabineteSemFrente.adicionarFerragem(ferragem, 4.0);
        gabineteSemFrente.definirAcabamentosCaixa(List.of(mdfCaixa, fitaBordas));
        gabineteSemFrente.definirAcabamentosFrente(List.of(mdfCaixa18, fitaBordas22));
        gabineteSemFrente.definirAcabamentoCaixaEspecifico(Fundo.class, List.of(mdfFundo));

        var item3 = new Item(gabineteSemFrente);
        System.out.println(item3);

        var itens = List.of(item, item2, item3);
        //var itens = new ArrayList<Item>();

        var ambiente = new Ambiente(
                "Cozinha",
                "Cozinha com armarios",
                "Cozinha com armarios",
                itens);

        System.out.println(ambiente);

        var ambiente2 = new Ambiente(
                "Sala",
                "Home theater",
                "Branco",
                itens);

        var ambiente3 = new Ambiente(
                "Banheiro",
                "Banheiro com armarios",
                "Banheiro com armarios",
                itens);

        var ambiente4 = new Ambiente(
                "Quarto",
                "Quarto com armarios",
                "Quarto com armarios",
                itens);

        var proposta = new Proposta(
                LocalDate.now(),
                UUID.randomUUID(),
                "Proposta de Móveis",
                "Casa de Jorge e Danusa",
                30,
                List.of(ambiente, ambiente2, ambiente3, ambiente4),
                "Observação");

        System.out.println(proposta);
    }

}