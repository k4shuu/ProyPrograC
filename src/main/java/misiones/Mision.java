package misiones;
import naves.Nave;
import bitacora.Bitacora;

public abstract class Mision {
    protected final String cod;

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

    public static Mision crearMision(String cod){
        return switch (cod.toUpperCase()) {
            case "M01" -> new MisionIntercep();
            case "M02" -> new MisionRecolec();
            case "M03" -> new MisionRetorno();
            default -> throw new IllegalArgumentException("Tipo de mision desconocido");
        };
    }

    //Template Method
    public final InformeMision comenzar(Nave nave){
        preparar(nave);
        ejecutar(nave);
        evaluar();
        return cerrar();
    }

    public abstract void preparar(Nave nave);

    public abstract void ejecutar(Nave nave);

    public abstract void evaluar();

    public abstract InformeMision cerrar();

}
