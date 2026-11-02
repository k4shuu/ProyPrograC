package asistente;

import destinoytablero.Posicion;

import java.util.ArrayList;
import java.util.List;

public class RadarLargoAlcance{
    private Posicion pos;

    public RadarLargoAlcance(Posicion posicion){
        this.pos = posicion;
    }

    public void setPos(Posicion pos) {
        this.pos = pos;
    }

    public List<Posicion> calcRuta(Posicion destino){
        List<Posicion> ruta = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();

        while(x != destino.getX() && y != destino.getY()){
            x+= Integer.compare(destino.getX(),x);
            y+= Integer.compare(destino.getY(),y);
            ruta.add(new Posicion(x,y));
        }
        return ruta;
    }
}
