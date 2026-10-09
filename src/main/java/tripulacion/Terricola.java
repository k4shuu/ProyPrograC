package tripulacion;

public class Terricola extends DecoratorOrigen {
    private static double subsidio = 20;
    private Tripulante tripulante;
    
    public Terricola(Tripulante tripulante){
        this.tripulante = tripulante;
    }

    public double getSueldo(){
        return this.tripulante.getSueldo() + subsidio;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Terrícola: " + subsidio + " Sueldo total: " + this.getSueldo();
    }
}