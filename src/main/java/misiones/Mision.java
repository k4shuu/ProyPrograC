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
    public final InformeMision comenzar(){
        try {
            preparar();
            ejecutar();
            evaluar();
        }catch(FaltaCombustibleException eC){
            System.out.println("La mision fue cancelada por falta de combustible, combustible necesario: " + eC.getCombustibleRequerido() );
            this.result = Resultado.CANCELADA;
        }catch(ExcesoDesgasteException eD){
            System.out.println("La mision fue cancelada, la nave se encuentra muy desgastada, desgaste: " + eD.getDesgasteExigido() );
            this.result = Resultado.CANCELADA;
        }catch(ExcesoEnergiaException eE){
            System.out.println("La mision fue cancelada por falta de almacenamiento para energia, requerido: " + eE.getEnergiaCargar());
            this.result = Resultado.CANCELADA;
        }
        return cerrar();
    }

    public abstract void preparar() throws FaltaCombustibleException, ExcesoDesgasteException, ExcesoEnergiaException;

    /**
     * precond: mision debe estar preparada
     * postcond: la nave del asistente gasto recursos
     * @throws FaltaCombustibleException
     * @throws ExcesoDesgasteException
     * @throws ExcesoEnergiaException
     */
    public abstract void ejecutar() throws FaltaCombustibleException, ExcesoDesgasteException,ExcesoEnergiaException;

    /**
     *precond: mision debe haberse ejecutado
     *postcond: cambio de estado de la mision
     */
    public abstract void evaluar();

    /**
     * precond: debe haberse evaluado la mision o haber fallado antes
     * postcond: cambio de estado de la mision
     * @return Informe de la mision
     */
    public abstract InformeMision cerrar();

}
