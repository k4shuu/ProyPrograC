package tripulacion;

import excepciones.TipoOrigenInvalidoException;

public class CargoFactory {
    /**
     * pre-cond: cargo!=null y cargo!=""
     * pre-cond: id!=null e id!=""
     * pre-cond: ant>0
     * pre-cond: cantConsejos>=0
     * post-cond: se creo una instancia de Cargo con los parametros dados
     * @param cargo
     * @param id
     * @param ant
     * @return
     */
    public static Cargo crear(String cargo, String id, int ant,int cantConsejos){
        assert id!=null && !id.isEmpty() :"La identidad de Cargo ingresada debe ser distinta de null y de cadena vacia";
        assert ant>0:"La antiguedad de Cargo debe ser mayor a cero";
        assert cargo!=null && !cargo.isEmpty() :"El cargo ingresado debe ser distinto de null y de cadena vacia";
        assert cantConsejos>=0: "La cantidad de consejos debe ser mayor o igual a cero";

        return switch (cargo.toUpperCase()) {
            case "ALFEREZ"   -> new Alferez(id, ant);
            case "CAPITAN"   -> new Capitan(id, ant);
            case "CONSEJERO" -> new Consejero(id, ant,cantConsejos);
            case "TENIENTE"  -> new Teniente(id, ant);

            default -> throw new TipoOrigenInvalidoException("Tipo de origen desconocido.", cargo);
        };
    }
}
