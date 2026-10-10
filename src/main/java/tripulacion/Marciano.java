package tripulacion;

public class Marciano extends DecoratorOrigen{
    private static double subsidio = 18;

    /**
     * pre-cond: tripulante!=null
     * post-cond: se creo una instancia de Marciano con el tripulante dado
     * @param tripulante
     */
    public Marciano(Tripulante tripulante) {

        super(tripulante);
        assert tripulante!=null:"Tripulante ingresado para el constructor de Marciano debe ser distinto de null";
    }

    public double getSueldo(){
        return this.tripulante.getSueldo()+18;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Marciano: "+18+" Sueldo Total "+this.getSueldo();
    }
}
