package destinoytablero;

import excepciones.NaveDestruidaException;
import naves.Nave;

public interface ElementoDelUniverso {
    void interactuar(Nave nave) throws NaveDestruidaException;
}
