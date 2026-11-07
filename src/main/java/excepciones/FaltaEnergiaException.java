package excepciones;

public class FaltaEnergiaException extends Exception{
    private int energiaRequerida;
    private int energiaDisponible;

    public FaltaEnergiaException(String mensaje, int energiaRequerida, int energiaDisponible){
        super(mensaje);
        this.energiaDisponible = energiaDisponible;
        this.energiaRequerida  = energiaRequerida;
    }

    public int getEnergiaRequerida() {
        return energiaRequerida;
    }
    public int getEnergiaDisponible(){
        return energiaDisponible;
    }
}
