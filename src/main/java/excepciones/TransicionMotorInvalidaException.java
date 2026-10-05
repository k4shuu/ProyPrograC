package excepciones;

public class TransicionMotorInvalidaException extends Exception {
    private String estadoOrigen;
    private String estadoDestino;
    private String mensaje;

    public TransicionMotorInvalidaException(String mensaje,String estadoOrigen,String estadoDestino){
        this.estadoDestino=estadoDestino;
        this.estadoOrigen=estadoOrigen;
        this.mensaje=mensaje;
    }
    public String getMensaje(){
        return this.mensaje;
    }
}
