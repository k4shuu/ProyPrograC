package excepciones;

public class ExcesoEnergiaException extends Exception {
    private int energiaAct, energiaCargar;
    private String mensaje;
    public ExcesoEnergiaException(String mensaje, int energiaAct, int energiaCargar) {
        this.mensaje=mensaje;
        this.energiaAct= energiaAct;
        this.energiaCargar= energiaCargar;
    }

    public String getMensaje(){return this.mensaje;}
    public int getEnergiaAct() {
        return energiaAct;
    }
    public int getEnergiaCargar() {
        return energiaCargar;
    }
}