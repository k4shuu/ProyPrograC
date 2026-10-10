package tripulacion;

public class CargoFactory {
    /**
     * pre-cond: cargo!=null y cargo!=""
     * pre-cond: id!=null e id!=""
     * pre-cond: ant>0
     * post-cond: se creo una instancia de Cargo con los parametros dados
     * @param cargo
     * @param id
     * @param ant
     * @return
     */
    public static Cargo crear(String cargo, String id, int ant){
        assert id!=null && !id.isEmpty() :"La identidad de Cargo ingresada debe ser distinta de null y de cadena vacia";
        assert ant>0:"La antiguedad de Cargo debe ser mayor a cero";
        assert cargo!=null && !cargo.isEmpty() :"El cargo ingresado debe ser distinto de null y de cadena vacia";
        return switch (cargo.toUpperCase()) {
            case "ALFEREZ"   -> new Alferez(id, ant);
            case "CAPITAN"   -> new Capitan(id, ant);
            case "CONSEJERO" -> new Consejero(id, ant);
            case "TENIENTE"  -> new Teniente(id, ant);

            default -> throw new IllegalArgumentException("Tipo de cargo desconocido.");
        };
    }
}
