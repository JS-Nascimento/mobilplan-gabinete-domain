package modelos;

import componentes.Gabinete;
import estrategias.BaseEntreLaterais;
import estrategias.BaseSobreLaterais;
import estrategias.EstrategiaDeConstrucao;

public class GabineteModel {
    private GabineteBuilder builder;

    private GabineteModel(EstrategiaDeConstrucao estrategia) {
        this.builder = new GabineteBuilder(estrategia);
    }

    public static GabineteModel create(Construcao construcao) {
        switch (construcao) {
            case BaseEntreLaterais:
                return new GabineteModel(new BaseEntreLaterais());
            case BaseSobreLaterais:
                return new GabineteModel(new BaseSobreLaterais());
            default:
                throw new IllegalArgumentException("Construção inválida");
        }
    }

    public Gabinete build() {
        return builder.build();
    }

    private class GabineteBuilder {

        private final EstrategiaDeConstrucao estrategia;
        public GabineteBuilder(EstrategiaDeConstrucao estrategia) {
            this.estrategia = estrategia;
        }

        public Gabinete build() {
            // Construa e retorne um objeto Gabinete
            return new Gabinete(this.estrategia);
        }
    }
}
