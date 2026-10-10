package tripulacion;

public abstract class DecoratorOrigen extends Tripulante{
    protected Tripulante tripulante;
    public DecoratorOrigen(Tripulante tripulante){
        super(tripulante.getIdentidad(), tripulante.getAntiguedad());
        this.tripulante=tripulante;
    }
}
