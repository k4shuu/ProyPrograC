package tripulacion;

public class Vulcano extends DecoratorOrigen {
    private static double subsidio = 30;

    /**
     * pre-cond: tripulante!=null
     * post-cond: se creo una instancia de Vulcano
     * @param tripulante
     */
    public Vulcano(Tripulante tripulante) {
        super(tripulante);
        assert tripulante!=null: "El tripulante para constructor de Vulcano ingresado debe ser distinto de null";
    }

    public double getSueldo(){
        return this.tripulante.getSueldo() + subsidio;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Vulcano: " + subsidio + " Sueldo total: " + this.getSueldo();
    }
}
