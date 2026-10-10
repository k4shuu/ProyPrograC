package excepciones;

public class ExcesoCombException extends Exception {
    private int combAct, combCargar;
    private String mensaje;
    public ExcesoCombException(String mensaje, int combActual, int combCargar) {
        this.mensaje=mensaje;
        this.combAct= combAct;
        this.combCargar= combCargar;

    }
    public String getMensaje(){return this.mensaje;}
    public int getCombAct() {
        return combAct;
    }

    public int getCombCargar() {
        return combCargar;
    }
}
