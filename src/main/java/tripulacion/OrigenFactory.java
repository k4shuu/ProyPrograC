package tripulacion;

public class OrigenFactory {
    /**
     * pre-cond: tripulante!=null
     * pre-cond: origen!=null y origen!=""
     * post-cond: se creo una instancia de DecoratorOrigen
     * @param tripulante
     * @param origen
     * @return
     */
    public static DecoratorOrigen crear(Tripulante tripulante, String origen){
        assert origen!=null && !origen.isEmpty() :"El origen para constructor de DecoratorOrigen ingresado debe ser distinto de null y de cadena vacia";
        assert tripulante!=null:"El tripulante ingresado para constructor de DecoratorOrigen debe ser distinto de null";
        return switch (origen.toUpperCase()) {
            case "MARCIANO"  -> new Marciano(tripulante);
            case "TERRICOLA" -> new Terricola(tripulante);
            case "VULCANO"   -> new Vulcano(tripulante);

            default -> throw new IllegalArgumentException("Tipo de origen desconocido.");
        };
    }
}
