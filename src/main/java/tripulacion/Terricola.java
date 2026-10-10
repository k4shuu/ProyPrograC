package tripulacion;

public class Terricola extends DecoratorOrigen {
    private Tripulante tripulante;
    
    public Terricola(Tripulante tripulante){

        super(tripulante);
    }

    public double getSueldo(){
        return this.tripulante.getSueldo()+20;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Terrícola: "+20+" Sueldo total: "+this.getSueldo();
    }
}
