package tripulacion;

public class Terricola extends DecoratorOrigen {
    private Tripulante tripulante;
    
    public Terricola(Tripulante tripulante){
        this.tripulante=tripulante;
    }

    public double getSueldo(){
        return this.tripulante.getSueldo()+20;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "Subsidio Origen: "+20+" Sueldo total: "+this.getSueldo();
    }
}
