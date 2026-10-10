package tripulacion;

public abstract class DecoratorOrigen extends Tripulante{
    protected Tripulante tripulante;

    /**
     * pre-cond: tripulante!=null
     * post-cond: se creo una instancia de DecoratorOrigen con el tripulante dado
     * @param tripulante
     */
    public DecoratorOrigen(Tripulante tripulante){
        super(tripulante.getIdentidad(), tripulante.getAntiguedad());
        assert tripulante!=null:"El tripulante ingresado para DecoratorOrigen no debe ser null";
        this.tripulante=tripulante;
    }
}
