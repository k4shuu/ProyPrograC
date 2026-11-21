package elementos;

import destinoytablero.ElementoDelUniverso;
import excepciones.FaltaEnergiaException;
import excepciones.NaveDestruidaException;
import naves.Nave;

public class Asteroide implements ElementoDelUniverso {



    public void interactuar(Nave nave) throws NaveDestruidaException {
        try {
            nave.usarEnergia(20);
        } catch (FaltaEnergiaException e) {
            throw new NaveDestruidaException("Nave destruida por chocar con asteroide");
        }
    }
}
