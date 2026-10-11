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

    protected Estado estado;
    protected Resultado result = null;

    /**
     * pre-cond: cod!=null y cod!=""
     * pre-cond: asistente!=null
     * post-cond: los atributos cod,estado y asistente se les asigna a cada uno el valor correspondiente pasados por parametro
     * @param cod
     * @param asistente
     */
    public Mision(String cod,AsistenteComando asistente){
        assert cod!=null && !cod.isEmpty():"El codigo de mision no debe ser null ni cadena vacia";
        assert asistente!=null:"El asistente de mision no debe ser null";

        this.cod = cod;
        this.estado = Estado.CREADA;
        this.asistente=asistente;
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

    public Estado getEstado(){
        return this.estado;
    }
    public Resultado getResult(){
        return this.result;
    }
    public AsistenteComando getAsistente(){
        return this.asistente;
    }
    public int getCombConsumido(){
        return this.combConsumido;
    }
    public int getDesgaste(){
        return this.desgaste;
    }
    public int getEnergCargada(){
        return this.energCargada;
    }
    public String getCod(){
        return this.cod;
    }
    public void setCombConsumido(int combConsumido) {
        this.combConsumido = combConsumido;
    }
    public void setEnergCargada(int energCargada) {
        this.energCargada = energCargada;
    }
    public void setDesgaste(int desgaste) {
        this.desgaste = desgaste;
    }
    public void setResult(Resultado result) {
        this.result = result;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
