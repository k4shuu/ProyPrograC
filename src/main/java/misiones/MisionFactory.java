package misiones;

import asistente.AsistenteComando;
import excepciones.TipoMisionInvalidaException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MisionFactory {

    /**
     precond = cod!= null y asistente != null
     postcond = queda creada una mision con referencia al asistente
     @param asistente AsistenteComando
     @param cod String
     */
    public static Mision crearMision(String cod, AsistenteComando asistente){
        assert !Objects.equals(cod, "") && cod != null: "Codigo no puede estar vacio";
        assert asistente != null: "Asistente debe ser valido";
        return switch (cod.toUpperCase()) {
            case "M01" -> new MisionIntercep(asistente);
            case "M02" -> new MisionRecolec(asistente);
            case "M03" -> new MisionRetorno(asistente);
            default -> throw new TipoMisionInvalidaException("Tipo de mision desconocido",cod);
        };
    }
}
