package misiones;
import asistente.AsistenteComando;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.ExcesoEnergiaException;
import destinoytablero.*;
import naves.Nave;

import java.util.List;
import java.util.Random;


public abstract class Mision {
    protected final String cod;
    protected AsistenteComando asistente;
    protected int combConsumido;
    protected int energCargada;
    protected int desgaste;
    protected Posicion obj;
    protected Random azar = new Random();
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
        this.informe = new InformeMision(cod, getNave());
        try{
            preparar();
            ejecutar();
        } catch (FaltaCombustibleException exception) {
            cancelarMision("La nave no tiene combustible suficiente");
        } catch (ExcesoDesgasteException exception) {
            cancelarMision("El desgaste inicial supera el máximo permitido");
        } catch (ExcesoEnergiaException exception) {
            cancelarMision("La energía de la misión excede la capacidad");
        }
        evaluar();
        return cerrar();
    }



    protected final void fallarMision(String motivo) {
        this.result = Resultado.FALLIDA;
        this.asistente.escribirBitacora(
                this.cod,
                "Misión fallida: " + motivo
        );
    }

    protected final void completarMisionExitosamente() {
        this.result = Resultado.EXITOSA;

        this.asistente.escribirBitacora(
                this.cod,
                "Misión completada exitosamente"
        );
    }

    protected final void cancelarMision(String motivo) {
        this.result = Resultado.CANCELADA;
        this.asistente.escribirBitacora(this.cod, "Misión cancelada: " + motivo);
    }

    protected final boolean alcanzoObjetivo() {
        return asistente.getTablero().getPosicionNave().equals(obj);
    }

    public abstract void preparar() throws FaltaCombustibleException, ExcesoDesgasteException, ExcesoEnergiaException;

    /**
     * precond: mision debe estar preparada
     * postcond: la nave del asistente gasto recursos
     * @throws FaltaCombustibleException
     * @throws ExcesoDesgasteException
     * @throws ExcesoEnergiaException
     */
    public final void ejecutar() throws FaltaCombustibleException, ExcesoDesgasteException, ExcesoEnergiaException {
        consumirRecursosIniciales();

        boolean recorridoCompletado = realizarViaje();

        this.estado = Estado.EJECUTADA;

        if (!recorridoCompletado) {
            return;
        }

        if (!cumpleCondicionFinal()) {
            fallarMision("La nave no conservo los recursos necesarios para la entrega");
            return;
        }

        completarMisionExitosamente();
    }

    private void realizarMovimiento() {
        ElementoDelUniverso siguienteElemento = asistente.getRadarCA().analizarPasoSig(getRuta());

        if (siguienteElemento != null && getRuta().size() >= 2) {
            asistente.solicitarEjecucionSaltoWarp();

            if(asistente.getTablero().getPosicionNave().equals(getRuta().get(1))){
                getRuta().removeFirst();
                getRuta().removeFirst();
            }
        } else {
            asistente.solicitarEjecucionSaltoComun();

            if(asistente.getTablero().getPosicionNave().equals(getRuta().getFirst())){
                getRuta().removeFirst();
            }
        }
    }

    protected boolean cumpleCondicionFinal() {
        return true;
    }

    private void consumirRecursosIniciales() throws FaltaCombustibleException, ExcesoDesgasteException {
        getNave().usarCombustible(combConsumido);
        getNave().realizarDesgaste(desgaste);

        if (energCargada > 0) {
            getNave().cargarEnerg(energCargada);
        }
    }

    private boolean realizarViaje() {
        boolean naveViva = asistente.estaVivaNave();
        boolean alcanzoDestino = alcanzoObjetivo();

        while (naveViva && !alcanzoDestino && !getRuta().isEmpty()) {

            ElementoDelUniverso elemento =
                    asistente.getTablero().inspeccionarPosicion();

            if (elemento != null) {
                elemento.interactuar(getNave());
            }

            naveViva = asistente.estaVivaNave();

            if (!naveViva) {
                fallarMision("La nave fue destruida durante el recorrido");
                return false;
            }

            realizarMovimiento();

            if (!asistente.estaVivaNave()) {
                fallarMision("La nave agotó sus recursos durante el movimiento");
                return false;
            }

            Destino.getInstance().turnoDestino();

            alcanzoDestino = alcanzoObjetivo();

            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                cancelarMision("La misión fue interrumpida");
                return false;
            }
        }

        if (!alcanzoDestino) {
            fallarMision("No fue posible alcanzar el objetivo");
            return false;
        }

        return true;
    }

    /**
     *precond: mision debe haberse ejecutado
     *postcond: cambio de estado de la mision
     */
    public void evaluar() {
        this.estado = Estado.EVALUADA;
        this.informe.finalizar(getNave(), result, estado);
    }

    /**
     * precond: debe haberse evaluado la mision o haber fallado antes
     * postcond: cambio de estado de la mision
     * @return Informe de la mision
     */
    public final InformeMision cerrar() {
        if (result == Resultado.EXITOSA) {
            aplicarResultadoExitoso();
        }

        asistente.escribirBitacora(cod, "Misión finalizada");

        this.estado = Estado.CERRADA;
        return informe;
    }

    /**
     * precond: la misión debe haber finalizado con resultado exitoso
     * postcond: se aplica la recompensa correspondiente y se actualizan los balances del informe
     */
    protected abstract void aplicarResultadoExitoso();

    public List<Posicion> getRuta(){
        return this.asistente.getRuta();
    }
    public void initRuta(Posicion p){
        this.asistente.calcRuta(p);
    }

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
    public Nave getNave(){return this.getAsistente().getNave();}
}
