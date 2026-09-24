package bitacora;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaBitacora { //CADA EVENTO REGISTRABLE
    private final LocalDateTime fechaHora;
    private final String mensaje;
    private final String origen; //DE DONDE VIENE EL NUEVO EVENTO REGISTRADO

    public EntradaBitacora(String origen, String mensaje) {
        this.fechaHora = LocalDateTime.now(); //EL INSTANTE EN EL QUE SE REGISTRA EL EVENTO
        this.origen = origen;
        this.mensaje = mensaje;
    }

    //GETTERS & SETTERS
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getOrigen() {
        return origen;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return "[" + fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "] [" + origen + "]: " + mensaje;
    }
}