package tripulacion;

public class Marciano extends DecoratorOrigen{
    private static double subsidio = 18;
    private Tripulante tripulante;

    public Marciano(Tripulante tripulante){
        this.tripulante = tripulante;
    }

    public double getSueldo(){
        return this.tripulante.getSueldo() + subsidio;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Marciano: " + subsidio + " Sueldo Total " + this.getSueldo();
    }
}