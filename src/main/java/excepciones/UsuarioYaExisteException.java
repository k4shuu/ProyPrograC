package excepciones;

public class UsuarioYaExisteException extends UsuarioInvalidoException {
    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
    }
}
