package excepciones;

public class FaltaCombustibleException extends Exception{
    private int combustibleRequerido;
    private int combustibleDisponible;

    public FaltaCombustibleException(String mensaje,int combustibleRequerido, int combustibleDisponible){
        super(mensaje);
        this.combustibleDisponible=combustibleDisponible;
        this.combustibleRequerido=combustibleRequerido;
    }
    public int getCombustibleDisponible(){return combustibleDisponible;}
    public int getCombustibleRequerido() {
        return combustibleRequerido;
    }
}
