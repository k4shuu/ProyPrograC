package tripulacion;

public class Vulcano extends DecoratorOrigen {
    private Tripulante tripulante;

    public Vulcano(Tripulante tripulante) {
        super(tripulante);
    }

    public double getSueldo(){
        return this.tripulante.getSueldo()+30;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Vulcano: "+30+" Sueldo total: "+this.getSueldo();
    }
}
