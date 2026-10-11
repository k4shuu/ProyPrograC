package excepciones;

public class ExcesoEnergiaException extends Exception {
    private int energiaAct, energiaCargar;
    public ExcesoEnergiaException(String mensaje, int energiaAct, int energiaCargar) {
        super(mensaje);
        this.energiaAct= energiaAct;
        this.energiaCargar= energiaCargar;
    }

    public int getEnergiaAct() {
        return energiaAct;
    }
    public int getEnergiaCargar() {
        return energiaCargar;
    }
}