package excepciones;

public class FaltaCombustibleException extends Exception{
    private String mensaje;
    public FaltaCombustibleException(String mensaje){
        this.mensaje=mensaje;
    }
    public String getMensaje(){
        return this.mensaje;
    }
}
