package misiones;
import naves.Nave;
import bitacora.Bitacora;

public abstract class Mision {
    protected final String cod;
    protected final Nave nave;
    protected final Bitacora bitacora;

    protected int combConsumido;
    protected int energConsumida;
    protected int desgaste;

    protected InformeMision informe;
    protected Estado estado;
    protected Resultado result = null;

    public Mision(Nave nav,String cod){
        this.cod = cod;
        this.nave = nav;
        this.bitacora = nav.getBit();
        this.estado = Estado.CREADA;
    }

    public static Mision crearMision(String cod,Nave nave){
        return switch (cod.toUpperCase()) {
            case "M01" -> new MisionIntercep(nave);
            case "M02" -> new MisionRecolec(nave);
            case "M03" -> new MisionRetorno(nave);
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
