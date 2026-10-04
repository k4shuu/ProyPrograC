package naves;

public class ExcesoEnergiaException extends Exception {
    private int energiaAct, energiaMax;
    public ExcesoEnergiaException(String message, int energiaAct, int energiaMax) {
        super(message);
        this.energiaAct= energiaAct;
        this.energiaMax= energiaMax;
    }

    public int getEnergiaAct() {
        return energiaAct;
    }

    public int getEnergiaMax() {
        return energiaMax;
    }
}