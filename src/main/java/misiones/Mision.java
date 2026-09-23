package misiones;
import naves.Nave;
import bitacora.Bitacora;

public abstract class Mision {
    private final String cod;
    private final Nave nave;
    private final Bitacora bitacora;

    private int combConsumido;
    private int energConsumida;
    private int desgaste;

    private InformeMision informe;
    private Estado estado;
    private Resultado result = null;

    public Mision(String cod, Nave nav,Bitacora bit){
        this.cod = cod;
        this.nave = nav;
        this.bitacora = bit;
        this.estado = Estado.CREADA;
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
