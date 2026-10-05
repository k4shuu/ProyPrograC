package excepciones;

public class ExcesoEnergiaException extends Exception {
    private int energiaAct, energiaMax;
    private String mensaje;
    public ExcesoEnergiaException(String mensaje, int energiaAct, int energiaMax) {
        this.mensaje=mensaje;
        this.energiaAct= energiaAct;
        this.energiaMax= energiaMax;
    }

    public String getMensaje(){return this.mensaje;}
    public int getEnergiaAct() {
        return energiaAct;
    }

    public int getEnergiaMax() {
        return energiaMax;
    }
}