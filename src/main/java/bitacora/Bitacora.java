package bitacora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bitacora { //LISTA DE EVENTOS REGISTRABLES
    private final List<EntradaBitacora> entradas;

    public Bitacora() {
        this.entradas = new ArrayList<>();
    }

    public void registrar(String origen, String mensaje) {
        this.entradas.add(new EntradaBitacora(origen, mensaje));
    }

    public List<EntradaBitacora> getEntradas() {
        return Collections.unmodifiableList(entradas); //NO MODIFICAREMOS, SOLO CARGAREMOS
    }

    public void limpiar() { //VACÍA TODA LA COLECCIÓN
        this.entradas.clear();
    }
}