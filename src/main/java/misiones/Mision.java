package misiones;
import asistente.AsistenteComando;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.ExcesoEnergiaException;
import destinoytablero.*;

import java.util.List;


public abstract class Mision {
    protected final String cod;
    protected AsistenteComando asistente;
    protected int combConsumido;
    protected int energCargada;
    protected int desgaste;
    protected Posicion obj;
    protected List<Posicion> ruta;

    protected InformeMision informe;
    protected Estado estado;
    public Resultado result = null;

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
     */
    public final InformeMision comenzar() {
        try{
            preparar();
            ejecutar();
            evaluar();
        }catch (FaltaCombustibleException combExc){
            this.asistente.escribirBitacora("Mision","Mision cancelada por falta de combustible, cargue antes de reintentarlo");
        }catch (ExcesoDesgasteException desgExc){
            this.asistente.escribirBitacora("Mision","Mision cancelada debido a alto desgaste de la nave, se recomienda mantenimiento");
        }catch (ExcesoEnergiaException enerExc){
            this.asistente.escribirBitacora("Mision","Mision cancelada, peligro de sobrecarga de baterias, se solicita consumir energia");
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
