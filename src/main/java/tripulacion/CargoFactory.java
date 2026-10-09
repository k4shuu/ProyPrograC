package tripulacion;

public class CargoFactory {
    public static Tripulante crear(String cargo, String id, int ant){
        return switch (cargo.toUpperCase()) {
            case "ALFEREZ"   -> new Alferez(id, ant);
            case "CAPITAN"   -> new Capitan(id, ant);
            case "CONSEJERO" -> new Consejero(id, ant);
            case "TENIENTE"  -> new Teniente(id, ant);

            default -> throw new IllegalArgumentException("Tipo de cargo desconocido.");
        };
    }
}
