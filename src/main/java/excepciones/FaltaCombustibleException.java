package excepciones;

public class FaltaCombustibleException extends Exception{
    private String mensaje;
    private int combustibleRequerido;
    private int combustibleDisponible;

    public FaltaCombustibleException(String mensaje,int combustibleRequerido, int combustibleDisponible){
        this.mensaje=mensaje;
        this.combustibleDisponible=combustibleDisponible;
        this.combustibleRequerido=combustibleRequerido;
    }

    public String getMensaje(){
        return this.mensaje;
    }
}
