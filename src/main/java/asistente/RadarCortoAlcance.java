package asistente;

import destinoytablero.ElementoDelUniverso;
import destinoytablero.Posicion;
import destinoytablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class RadarCortoAlcance {
    private Tablero tablero;
    public RadarCortoAlcance(Tablero t){
        this.tablero = t;
    }

    public ElementoDelUniverso analizarPasoSig(List<Posicion> ruta){
        return tablero.inspeccionarPosicion(ruta.getFirst());
    }
}
