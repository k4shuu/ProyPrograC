package bitacora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bitacora { //LISTA DE EVENTOS REGISTRABLES
    private final List<Entrada> entradas;

    public Bitacora() {
        this.entradas = new ArrayList<>();
    }

    public void registrar(String origen, String mensaje) {
        this.entradas.add(new Entrada(origen, mensaje));
    }
    public void registrar(Entrada entrada){this.entradas.add(entrada);}
    public List<Entrada> getEntradas() {
        return Collections.unmodifiableList(entradas); //NO MODIFICAREMOS, SOLO CARGAREMOS
    }
    public void mostrar(){
        for(entrada Entrada: entradas){
            entrada.toString();
        }
    }
    public void limpiar() { //VACÍA TODA LA COLECCIÓN
        this.entradas.clear();
    }
}