package tripulacion;

public class Consejero extends Cargo {
    private static double sueldoBase = 600;
    private static double multiplicador = 0.05;

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: antiguedad>0
     * post-cond: se creo una instancia de Consejero con los parametros dados
     * @param identidad
     * @param antiguedad
     */
    public Consejero(String identidad, int antiguedad) {

        super(identidad, antiguedad);
        assert identidad!=null && !identidad.isEmpty() :"La identidad de consejero ingresada debe ser distinta de null y de cadena vacia";
        assert antiguedad>0:"La antiguedad de consejero debe ser mayor a cero";
    }

    public double getSueldo(){
        return sueldoBase*(1 + multiplicador*antiguedad);
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Consejero: " + sueldoBase + "\t Adicional antiguedad: " + sueldoBase*multiplicador*antiguedad;
    }
}
