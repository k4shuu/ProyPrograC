package excepciones;

public class ExcesoCombException extends Exception {
    private int combAct, combMax;
    public ExcesoCombException(String message, int combActual, int combMax) {
        super(message);
        this.combAct= combAct;
        this.combMax= combMax;

    }

    public int getCombAct() {
        return combAct;
    }

    public int getCombMax() {
        return combMax;
    }
}
