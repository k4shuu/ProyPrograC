package excepciones;

public class TransicionMotorInvalidaException extends Exception {
    private String estadoOrigen;
    private String estadoDestino;

    public TransicionMotorInvalidaException(String mensaje,String estadoOrigen,String estadoDestino){
        super(mensaje);
        this.estadoDestino=estadoDestino;
        this.estadoOrigen=estadoOrigen;
    }

    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }
}
