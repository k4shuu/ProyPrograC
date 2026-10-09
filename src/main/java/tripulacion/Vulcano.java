package tripulacion;

public class Vulcano extends DecoratorOrigen {
    private static double subsidio = 30;
    private Tripulante tripulante;

    public Vulcano(Tripulante tripulante){
        this.tripulante = tripulante;
    }

    public double getSueldo(){
        return this.tripulante.getSueldo() + subsidio;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Vulcano: " + subsidio + " Sueldo total: " + this.getSueldo();
    }
}