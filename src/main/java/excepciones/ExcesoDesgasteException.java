package excepciones;

public class ExcesoDesgasteException extends Exception {
    private int desgasteExigido;
    private int desgasteActual;
    public ExcesoDesgasteException(String mensaje,int desgasteActual,int desgasteExigido){
        super(mensaje);
        this.desgasteActual=desgasteActual;
        this.desgasteExigido=desgasteExigido;
    }

    public int getDesgasteExigido() {return desgasteExigido;}
    public int getDesgasteActual() {return desgasteActual;}
}
