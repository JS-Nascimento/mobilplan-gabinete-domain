package materiaPrima.acabamento;

public enum Unidade {

    METRO_QUADRADO("m²"),
    METRO_LINEAR("m/l"),
    UNIDADE("un"),
    PAR("par"),
    JOGO("jogo"),
    PECA("peça");

    private final String descricao;

    Unidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
