package tripulacion;

public class Teniente extends Cargo {
    private static double sueldoBase = 400;
    private static double multiplicador = 0.03;

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: antiguedad>0
     * post-cond: se creo una instancia de teniente
     * @param identidad
     * @param antiguedad
     */
    public Teniente(String identidad, int antiguedad) {
        super(identidad, antiguedad);
        assert identidad!=null && !identidad.isEmpty() :"La identidad de Teniente ingresada debe ser distinta de null y de cadena vacia";
        assert antiguedad>0:"La antiguedad de Teniente debe ser mayor a cero";
    }

    public double getSueldo(){
        return sueldoBase*(1 + multiplicador*antiguedad);
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Teniente: " + sueldoBase + "\t Adicional antiguedad: " + sueldoBase*multiplicador*antiguedad;
    }
}