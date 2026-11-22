package elementos;

import destinoytablero.ElementoDelUniverso;
import naves.Nave;

public class EstacionesDeCarga implements ElementoDelUniverso {
    @Override
    public void interactuar(Nave nave) {
        nave.cargarEnerg(20);
    }
}