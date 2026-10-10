package excepciones;

public class TipoMisionInvalidaException extends RuntimeException {
    private String tipo;
    public TipoMisionInvalidaException(String message, String tipo) {
        super(message);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
