package bitacora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bitacora { //LISTA DE EVENTOS REGISTRABLES
    private final List<Entrada> entradas;

    public Bitacora() {
        this.entradas = new ArrayList<>();
    }

    /**
     * post-cond: El tamaño de entradas se incrementará en 1
     *
     * @param origen  Área de donde viene el evento
     * @param mensaje Contenido del evento a registrar
     */
    public void registrar(String origen, String mensaje) {
        this.entradas.add(new Entrada(origen, mensaje));
    }

    /**
     * pre-cond: entrada
     * post-cond: El tamaño de entradas se incrementará en 1
     *
     * @param entrada Evento a registrar
     */
    public void registrar(Entrada entrada) {
        this.entradas.add(entrada);
    }

    public List<Entrada> getEntradas() {
        return Collections.unmodifiableList(entradas); //NO MODIFICAREMOS, SOLO CARGAREMOS
    }

    public void mostrarBitacora() {
        for (Entrada entrada : entradas)
            System.out.println(entrada.toString());
    }

    public void limpiar() { //VACÍA TODA LA COLECCIÓN
        this.entradas.clear();
    }
}