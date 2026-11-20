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
     * pre-cond: origen!=null y origen!=""
     * pre-cond: mensaje!=null y mensaje!=""
     * post-cond: El tamaño de entradas se incrementará en 1
     *
     * @param origen  Área de donde viene el evento
     * @param mensaje Contenido del evento a registrar
     */
    public void registrar(String origen, String mensaje) {
        assert origen!=null && !origen.isEmpty():"El origen de una entrada debe ser distinto de null y distinto de cadena vacia";
        assert mensaje!=null && !mensaje.isEmpty():"El mensaje de una entrada debe ser distinto de null y distinto de cadena vacia";
        this.entradas.add(new Entrada(origen, mensaje));
    }

    /**
     * pre-cond: entrada!=null
     * post-cond: El tamaño de entradas se incrementará en 1
     *
     * @param entrada Evento a registrar
     */
    public void registrar(Entrada entrada) {
        int tamAnterior=this.entradas.size();
        assert entrada!=null: "La entrada debe ser distinta de null";
        this.entradas.add(entrada);
        assert (tamAnterior + 1) == this.entradas.size() :"El tamaño de entradas se debe incrementar en 1";
    }

    public List<Entrada> getEntradas() {
        return Collections.unmodifiableList(entradas); //NO MODIFICAREMOS, SOLO CARGAREMOS
    }

    public void mostrarBitacora() {
        for (Entrada entrada : entradas)
            System.out.println(entrada.toString());
    }

    public Entrada getUltimaEntrada(){
        return this.entradas.getLast();
    }
    public void limpiar() { //VACÍA TODA LA COLECCIÓN
        this.entradas.clear();
    }
}