package excepciones;

public class TipoOrigenInvalidoException extends RuntimeException {
    private String tipo;

    public TipoOrigenInvalidoException(String message, String tipo) {
        super(message);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}