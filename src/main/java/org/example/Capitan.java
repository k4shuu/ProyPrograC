package org.example;

public class Capitan extends Cargo {
    public Capitan(String identidad,int antiguedad) {
        super(identidad,antiguedad);
    }
    public double getSueldo(){
        return 1000+1000*0.2*this.antiguedad;
    }
    public String getConceptoSueldo(){
        return "Sueldo base por cargo: "+1000+" Adicional antiguedad: "+1000*0.2*this.antiguedad;
    }
}
