package tripulacion;

public class Capitan extends Cargo {

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: antiguedad>0
     * post-cond:??
     * @param identidad
     * @param antiguedad
     */
    public Capitan(String identidad,int antiguedad) {

        super(identidad,antiguedad);
        assert identidad!=null && !identidad.isEmpty() :"La identidad ingresada debe ser distinta de null y de cadena vacia";
        assert antiguedad>0:"La antiguedad debe ser mayor a cero";
    }

    public double getSueldo(){
        return 1000+1000*0.2*this.antiguedad;
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Capitan: "+1000+"\t Adicional antiguedad: "+1000*0.2*this.antiguedad;
    }
}
