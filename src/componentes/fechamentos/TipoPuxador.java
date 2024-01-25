package componentes.fechamentos;

public enum TipoPuxador {

    SEM_PUXADOR("Sem Puxador"),
    PUXADOR_ALCA("Puxador Alça"),
    PUXADOR_CAVA_HORIZONTAL("Puxador Cava Horizontal"),
    PUXADOR_CAVA_VERTICAL("Puxador Cava Vertical"),
    PUXADOR_PERFIL_HORIZONTAL("Puxador Perfil Horizontal"),
    PUXADOR_PERFIL_VERTICAL("Puxador Perfil Vertical");

    private final String descricao;

    TipoPuxador(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return this.descricao;
    }
}
