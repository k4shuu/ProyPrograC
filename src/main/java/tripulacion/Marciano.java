package tripulacion;

public class Marciano extends DecoratorOrigen{
    private Tripulante tripulante;

    public Marciano(Tripulante tripulante) {
        super(tripulante);
    }

    public double getSueldo(){
        return this.tripulante.getSueldo()+18;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Marciano: "+18+" Sueldo Total "+this.getSueldo();
    }
}
