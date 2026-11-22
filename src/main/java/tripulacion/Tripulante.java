package tripulacion;

import java.util.Objects;

public abstract class Tripulante {
    protected String identidad;
    protected int antiguedad;

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: antiguedad>=0
     * post-cond: se creo una instancia de tripulante con los parametros dados
     * @param identidad
     * @param antiguedad
     */
    public Tripulante(String identidad,int antiguedad){
        assert identidad!=null && !identidad.isEmpty() :"La identidad de Tripulante ingresada debe ser distinta de null y de cadena vacia";
        assert antiguedad>=0:"La antiguedad de Tripulante debe ser mayor o igual a cero";
        this.identidad=identidad;
        this.antiguedad=antiguedad;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Tripulante otro = (Tripulante) o;
        return this.antiguedad == otro.antiguedad &&
                Objects.equals(this.identidad, otro.identidad);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getClass(), identidad, antiguedad);
    }
    public  abstract String getInfo();

    public String getIdentidad(){
        return this.identidad;
    }
    public int getAntiguedad(){
        return this.antiguedad;
    }

    public abstract String getCargo();
    public abstract double getSueldo();

    public abstract String getConceptoSueldo();
    
}
