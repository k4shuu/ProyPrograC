public abstract class Tripulante {
    protected String identidad;
    protected int antiguedad;
    
    public Tripulante(String identidad,int antiguedad){
        this.identidad=identidad;
        this.antiguedad=antiguedad;
    }
    public Tripulante(){//constructor para el decorator
        
    }

    public abstract double getSueldo();

    public abstract String getConceptoSueldo();
    
}
