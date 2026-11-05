package asistente;

import destinoytablero.Posicion;
import destinoytablero.Tablero;

import java.util.ArrayList;
import java.util.List;

public class RadarLargoAlcance{
    private final Tablero tablero;

    public RadarLargoAlcance(Tablero t){
        this.tablero = t;
    }


    public List<Posicion> calcRuta(Posicion destino){
        List<Posicion> ruta = new ArrayList<>();
        int x = this.tablero.getPosicionNave().getX();
        int y = this.tablero.getPosicionNave().getY();

        while(x != destino.getX() || y != destino.getY()){
            x+= Integer.compare(destino.getX(),x);
            y+= Integer.compare(destino.getY(),y);
            ruta.add(new Posicion(x,y));
        }
        return ruta;
    }
}
