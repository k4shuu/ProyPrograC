package misiones;

import asistente.AsistenteComando;

public class MisionFactory {

    public static Mision crearMision(String cod, AsistenteComando asistente){
        return switch (cod.toUpperCase()) {
            case "M01" -> new MisionIntercep(asistente);
            case "M02" -> new MisionRecolec(asistente);
            case "M03" -> new MisionRetorno(asistente);
            default -> throw new IllegalArgumentException("Tipo de mision desconocido");
        };
    }
}
