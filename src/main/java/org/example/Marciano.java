package org.example;

public class Marciano extends DecoratorOrigen{
    private Tripulante tripulante;
    public Marciano(Tripulante tripulante) {
        this.tripulante=tripulante;
    }
    public double getSueldo(){
        return this.tripulante.getSueldo()+18;
    }
    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "Subsidio Origen: "+18+" Sueldo Total "+this.getSueldo();
    }
}
