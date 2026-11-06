package elementos;

import destinoytablero.ElementoDelUniverso;
import naves.Nave;

public class Asteroide implements ElementoDelUniverso {



    public void interactuar(Nave nave) {
        nave.colisionConAsteroide(20);
    }
}
