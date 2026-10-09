package tripulacion;

public class OrigenFactory {
    public static Tripulante crear(Tripulante tripulante, String origen){
        return switch (origen.toUpperCase()) {
            case "MARCIANO"  -> new Marciano(tripulante);
            case "TERRICOLA" -> new Terricola(tripulante);
            case "VULCANO"   -> new Vulcano(tripulante);

            default -> throw new IllegalArgumentException("Tipo de origen desconocido.");
        };
    }
}
