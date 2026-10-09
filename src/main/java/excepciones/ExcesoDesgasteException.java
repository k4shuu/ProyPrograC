package excepciones;

public class ExcesoDesgasteException extends Exception {
    private int desgasteExigido;
    private int desgasteActual;
    private String mensaje;

    public ExcesoDesgasteException(String mensaje,int desgasteActual,int desgasteExigido){
        this.desgasteActual=desgasteActual;
        this.desgasteExigido=desgasteExigido;
        this.mensaje=mensaje;
    }
    public String getMensaje(){
        return this.mensaje;
    }

}
