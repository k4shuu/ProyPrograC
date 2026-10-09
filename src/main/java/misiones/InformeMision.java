package misiones;

import bitacora.Entrada;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InformeMision {
    private final LocalDateTime fechaHora;
    private final String mensaje;
    private final String origen;

    public InformeMision(String mensaje,String origen) {
        this.fechaHora = LocalDateTime.now();
        this.mensaje = mensaje;
        this.origen = origen;
    }

    @Override
    public String toString() {
        return "[" + fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "] [" + origen + "]: " + mensaje;
    }

    public LocalDateTime getFechaHora() {return fechaHora;}
    public String getMensaje() {return mensaje;}
    public String getOrigen() {return origen;}
}
