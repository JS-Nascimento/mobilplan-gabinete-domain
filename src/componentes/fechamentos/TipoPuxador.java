package componentes.fechamentos;

public enum TipoPuxador {

    SEM_PUXADOR("Sem Puxador"),
    PUXADOR_ALCA("Puxador Alça"),
    PUXADOR_CAVA("Puxador Cava"),
    PUXADOR_PERFIL("Puxador Perfil");

    private final String descricao;

    TipoPuxador(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return this.descricao;
    }
}
