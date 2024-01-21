package componentes;

public class Folgas {
    private final int superior;
    private final int inferior;
    private final int esquerda;
    private final int direita;
    private final int entreComponentes;

    public Folgas(int superior, int inferior, int esquerda, int direita, int entreComponentes) {
        this.superior = superior;
        this.inferior = inferior;
        this.esquerda = esquerda;
        this.direita = direita;
        this.entreComponentes = entreComponentes;
    }

    public Folgas(int profundidade) {
        this.superior = profundidade;
        this.inferior = 0;
        this.esquerda = 0;
        this.direita = 0;
        this.entreComponentes = 0;
    }

    public int superior() {
        return superior;
    }

    public int inferior() {
        return inferior;
    }

    public int esquerda() {
        return esquerda;
    }

    public int direita() {
        return direita;
    }

    public int entreComponentes() {
        return entreComponentes;
    }

}
