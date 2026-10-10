package tripulacion;

public class Terricola extends DecoratorOrigen {
    private static double subsidio = 20;


    /**
     * pre-cond: tripulante!=null
     * post-cond: se creo una instancia de Terricola con el tripulante dado
     * @param tripulante
     */
    public Terricola(Tripulante tripulante){
        super(tripulante);
        assert tripulante!=null  :"Tripulante para constructor Terricola ingresado debe ser distinto de null";
    }

    public double getSueldo(){
        return this.tripulante.getSueldo()+subsidio;
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Terrícola: "+subsidio+" Sueldo total: "+this.getSueldo();
    }
}
