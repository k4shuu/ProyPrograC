package misiones;
import asistente.AsistenteComando;
import naves.Nave;
import bitacora.Bitacora;

public abstract class Mision {
    protected final String cod;
    protected AsistenteComando asistente;
    protected int combConsumido;
    protected int energConsumida;
    protected int desgaste;

    protected InformeMision informe;
    protected Estado estado;
    protected Resultado result = null;

    public Mision(String cod){
        this.cod = cod;
        this.estado = Estado.CREADA;
    }

    public static Mision crearMision(String cod,AsistenteComando asistente){
        return switch (cod.toUpperCase()) {
            case "M01" -> new MisionIntercep(asistente);
            case "M02" -> new MisionRecolec(asistente);
            case "M03" -> new MisionRetorno(asistente);
            default -> throw new IllegalArgumentException("Tipo de mision desconocido");
        };
    }

    //Template Method
    public final InformeMision comenzar(){
        preparar();
        ejecutar();
        evaluar();
        return cerrar();
    }

    public abstract void preparar();

    public abstract void ejecutar();

    public abstract void evaluar();

    public abstract InformeMision cerrar();

}
