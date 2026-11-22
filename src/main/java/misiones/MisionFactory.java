package misiones;

import asistente.AsistenteComando;
import excepciones.TipoMisionInvalidaException;

/**
 * Fábrica de las misiones soportadas por la aplicación.
 */
public class MisionFactory {

    /**
     * Crea una instancia de la fábrica de misiones.
     */
    public MisionFactory() {
    }

    /**
     * Crea la misión identificada por el código indicado.
     * precond: el código no es nulo ni vacío y el asistente es válido
     * postcond: la misión creada conserva el asistente y queda en estado CREADA
     * @param cod código de misión ({@code M01}, {@code M02} o {@code M03})
     * @param asistente asistente que coordina la nave y el tablero
     * @return misión correspondiente al código solicitado
     * @throws TipoMisionInvalidaException si el código no identifica una misión conocida
     */
    public static Mision crearMision(String cod, AsistenteComando asistente){
        assert cod != null && !cod.isBlank() : "El código no puede ser nulo ni estar vacío";
        assert asistente != null : "El asistente debe ser válido";

        Mision mision = switch (cod.toUpperCase()) {
            case "M01" -> new MisionIntercep(asistente);
            case "M02" -> new MisionRecolec(asistente);
            case "M03" -> new MisionRetorno(asistente);
            default -> throw new TipoMisionInvalidaException("Tipo de misión desconocido", cod);
        };

        return mision;
    }
}
