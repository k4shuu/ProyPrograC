package excepciones;

public class PersistenciaException extends Exception{
    public PersistenciaException(String mensaje, Exception causa) {
        super(mensaje, causa);
    }
}
