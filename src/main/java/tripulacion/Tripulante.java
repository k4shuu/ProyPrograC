package tripulacion;

public abstract class Tripulante {
    protected String identidad;
    protected int antiguedad;

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: antiguedad>0
     * post=??
     * @param identidad
     * @param antiguedad
     */
    public Tripulante(String identidad,int antiguedad){
        assert identidad!=null && !identidad.isEmpty() :"La identidad ingresada debe ser distinta de null y de cadena vacia";
        assert antiguedad>0:"La antiguedad debe ser mayor a cero";
        this.identidad=identidad;
        this.antiguedad=antiguedad;
    }

    public String getIdentidad(){
        return this.identidad;
    }
    public int getAntiguedad(){
        return this.antiguedad;
    }


    public abstract double getSueldo();

    public abstract String getConceptoSueldo();
    
}
