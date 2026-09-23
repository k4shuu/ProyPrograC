public class Vulcano extends DecoratorOrigen {
    private Tripulante tripulante;

    public Vulcano(Tripulante tripulante) {
        this.tripulante=tripulante;
    }

    public double getSueldo(){
        return this.tripulante.getSueldo()+30;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "Subsidio Origen: "+30+" Sueldo total: "+this.getSueldo();
    }
}
