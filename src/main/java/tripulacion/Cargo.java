package tripulacion;

public abstract class Cargo extends Tripulante {

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: antiguedad>0
     * post-cond: se creo una instancia de Cargo con los parametros dados
     * @param identidad
     * @param antiguedad
     */
    public Cargo(String identidad, int antiguedad) {
        super(identidad, antiguedad);
        assert identidad!=null && !identidad.isEmpty() :"La identidad de Cargo ingresada debe ser distinta de null y de cadena vacia";
        assert antiguedad>0:"La antiguedad de Cargo debe ser mayor a cero";
    }
}