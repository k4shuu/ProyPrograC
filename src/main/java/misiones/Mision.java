package misiones;
import asistente.AsistenteComando;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.ExcesoEnergiaException;



public abstract class Mision {
    protected final String cod;
    protected AsistenteComando asistente;
    protected int combConsumido;
    protected int energCargada;
    protected int desgaste;

    protected InformeMision informe;
    protected Estado estado;
    protected Resultado result = null;

    public Mision(String cod){
        this.cod = cod;
        this.estado = Estado.CREADA;
    }

    //Template Method

    /**
     * postcond: la nave gasto recursos o se cancelo la mision
     * @return Informe de mision correspondiente
     * @throws FaltaCombustibleException si combAct < combRequerido
     * @throws ExcesoDesgasteException si desgasteAct + desgasteRequerido > 100
     * @throws ExcesoEnergiaException si energiaCargadd + energAct > 100
     */
    public final InformeMision comenzar() throws FaltaCombustibleException, ExcesoDesgasteException, ExcesoEnergiaException {
        preparar();
        ejecutar();
        evaluar();
        return cerrar();
    }

    public abstract void preparar() throws FaltaCombustibleException, ExcesoDesgasteException, ExcesoEnergiaException;

    public abstract void ejecutar() throws FaltaCombustibleException, ExcesoDesgasteException,ExcesoEnergiaException;

    public abstract void evaluar();

    public abstract InformeMision cerrar();

}
