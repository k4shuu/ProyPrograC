package tripulacion;

public class Alferez extends Cargo{
    private static double sueldoBase = 200;
    private static double multiplicador = 0.005;

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: antiguedad>0
     * post-cond:se creo una instancia de Alferez con los parametros dados
     * @param identidad
     * @param antiguedad
     */
    public Alferez(String identidad, int antiguedad) {
        super(identidad, antiguedad);
        assert identidad!=null && !identidad.isEmpty() :"La identidad de Alferez ingresada debe ser distinta de null y de cadena vacia";
        assert antiguedad>0:"La antiguedad de Alferez debe ser mayor a cero";
    }

    public double getSueldo(){
        return sueldoBase*(1 + multiplicador*antiguedad);
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Alferez: " + sueldoBase + "\t Adicional antiguedad: " + sueldoBase*multiplicador*antiguedad;
    }
}