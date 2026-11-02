package excepciones;

public class ExcesoCombException extends Exception {
    private int combAct, combCargar;

    public ExcesoCombException(String mensaje, int combActual, int combCargar) {
        super(mensaje);
        this.combAct= combActual;
        this.combCargar= combCargar;

    }
    public int getCombAct() {
        return combAct;
    }

    public int getCombCargar() {
        return combCargar;
    }
}
