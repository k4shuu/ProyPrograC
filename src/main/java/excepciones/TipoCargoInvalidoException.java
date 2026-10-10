package excepciones;

public class TipoCargoInvalidoException extends RuntimeException {
    private String tipo;

    public TipoCargoInvalidoException(String message, String tipo) {
        super(message);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}