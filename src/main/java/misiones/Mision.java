package misiones;
import asistente.AsistenteComando;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import destinoytablero.*;
import excepciones.NaveDestruidaException;
import naves.Nave;

import java.util.List;
import java.util.Random;

/**
 * Abstracción base para una misión espacial.
 * Implementa el algoritmo general mediante el patrón Template Method: crea
 * el informe, prepara la ruta, ejecuta el viaje, evalúa el resultado y cierra
 * la misión. Las subclases personalizan la condición final y la recompensa.
 */
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
     * Inicializa una misión en estado {@link Estado#CREADA}.
     * precond: el código no es nulo ni vacío y el asistente es válido
     * postcond: la misión conserva el código y el asistente, y queda en estado CREADA
     * @param cod código identificador de la misión
     * @param asistente asistente que permite operar la nave y el tablero
     */
    public Mision(String cod, AsistenteComando asistente){
        assert cod != null && !cod.isBlank() : "El código de misión no debe ser nulo ni estar vacío";
        assert asistente != null : "El asistente de misión no debe ser nulo";

        this.cod = cod;
        this.estado = Estado.CREADA;
        this.asistente = asistente;

    }

    /**
     * Ejecuta el ciclo completo de la misión (Template Method).
     * precond: la misión está en estado CREADA y el asistente tiene una nave válida
     * postcond: la misión queda cerrada y devuelve un informe con un resultado
     * @return informe correspondiente a la misión ejecutada
     */
    public final InformeMision comenzar() {
        assert estado == Estado.CREADA : "Solo puede comenzarse una misión en estado CREADA";
        assert getNave() != null : "La misión necesita una nave válida";

        this.informe = new InformeMision(cod, getNave());
        try{
            preparar();
            ejecutar();
        } catch (FaltaCombustibleException exception) {
            cancelarMision("La nave no tiene combustible suficiente");
        } catch (ExcesoDesgasteException exception) {
            cancelarMision("El desgaste inicial supera el máximo permitido");
        }
        evaluar();
        InformeMision informeFinal = cerrar();

        return informeFinal;
    }

    /**
     * Marca la misión como fallida y registra el motivo en la bitácora.
     *
     * @param motivo descripción no vacía de la causa del fallo
     */
    protected final void fallarMision(String motivo) {
        assert motivo != null && !motivo.isBlank() : "El motivo del fallo no puede estar vacío";

        this.result = Resultado.FALLIDA;
        this.asistente.escribirBitacora(
                this.cod,
                "Misión fallida: " + motivo
        );

    }

    /**
     * Marca la misión como exitosa y registra el resultado en la bitácora.
     */
    protected final void completarMisionExitosamente() {
        this.result = Resultado.EXITOSA;

        this.asistente.escribirBitacora(
                this.cod,
                "Misión completada exitosamente"
        );

    }

    /**
     * Cancela la misión y registra el motivo en la bitácora.
     *
     * @param motivo descripción no vacía de la causa de cancelación
     */
    protected final void cancelarMision(String motivo) {
        assert motivo != null && !motivo.isBlank() : "El motivo de cancelación no puede estar vacío";

        this.result = Resultado.CANCELADA;
        this.asistente.escribirBitacora(this.cod, "Misión cancelada: " + motivo);

    }

    /**
     * Indica si la nave se encuentra en la posición objetivo.
     *
     * @return {@code true} si la nave alcanzó el objetivo
     */
    protected final boolean alcanzoObjetivo() {
        assert obj != null : "La misión debe tener un objetivo";
        return asistente.getTablero().getPosicionNave().equals(obj);
    }

    /**
     * Obtiene el objetivo, valida los recursos de la nave y calcula la ruta.
     * precond: la misión está creada y sus consumos son válidos
     * postcond: la misión queda preparada con un objetivo y una ruta calculada
     * @throws FaltaCombustibleException si la nave no dispone del combustible requerido
     * @throws ExcesoDesgasteException si el desgaste requerido supera el máximo permitido
     */
    public void preparar() throws ExcesoDesgasteException, FaltaCombustibleException{
        assert estado == Estado.CREADA : "Solo puede prepararse una misión creada";
        assert combConsumido > 0 : "El consumo de combustible debe ser positivo";
        assert energCargada >= 0 : "La carga de energía no puede ser negativa";
        assert desgaste > 0 : "El desgaste debe ser positivo";

        this.obj = asistente.getTablero().obtenerObjetivo();
        if(this.asistente.getNave().tieneCombustible(combConsumido))
            if(this.asistente.getNave().sePuedeDesgastar(desgaste)){
                initRuta(obj);
            }
            else{
                this.asistente.escribirBitacora("M-02","ERROR: el desgaste de la mision excede lo permitido para la nave ");
                throw new ExcesoDesgasteException("ERROR: el desgaste de la mision excede lo permitido para la nave",this.asistente.getNave().getDesgaste(),desgaste);
            }
        else {
            this.asistente.escribirBitacora("M-02","ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
            throw new FaltaCombustibleException("ERROR: La nave no tiene el combustible necesario para ejecutar la mision",combConsumido,this.asistente.getNave().getCombustible());
        }
        this.estado = Estado.PREPARADA;

    }

    /**
     * Consume los recursos iniciales y realiza el viaje hasta el objetivo.
     * precond: la misión está preparada
     * postcond: la misión queda ejecutada y tiene un resultado
     * @throws FaltaCombustibleException si no se puede consumir el combustible requerido
     * @throws ExcesoDesgasteException si no se puede aplicar el desgaste requerido
     */
    public final void ejecutar() throws FaltaCombustibleException, ExcesoDesgasteException {
        assert estado == Estado.PREPARADA : "Solo puede ejecutarse una misión preparada";

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

    /**
     * Realiza el siguiente movimiento, mediante salto warp o salto común.
     *
     * @throws NaveDestruidaException si una interacción destruye la nave
     */
    private void realizarMovimiento() throws NaveDestruidaException {
        assert getRuta() != null && !getRuta().isEmpty() : "Debe existir un paso pendiente en la ruta";

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

    /**
     * Verifica las condiciones adicionales exigidas al llegar al objetivo.
     * Las subclases pueden redefinir este punto de extensión.
     *
     * @return {@code true} si la misión puede completarse exitosamente
     */
    protected boolean cumpleCondicionFinal() {
        return true;
    }

    /**
     * Aplica a la nave el consumo, la carga y el desgaste iniciales.
     *
     * @throws FaltaCombustibleException si el combustible disponible no alcanza
     * @throws ExcesoDesgasteException si el desgaste supera el máximo permitido
     */
    private void consumirRecursosIniciales() throws FaltaCombustibleException, ExcesoDesgasteException {
        assert combConsumido > 0 : "El consumo de combustible debe ser positivo";
        assert energCargada >= 0 : "La carga de energía no puede ser negativa";
        assert desgaste > 0 : "El desgaste debe ser positivo";

        getNave().usarCombustible(combConsumido);
        getNave().realizarDesgaste(desgaste);

        if (energCargada > 0) {
            getNave().cargarEnerg(energCargada);
        }
    }

    /**
     * Recorre la ruta e interactúa con los elementos encontrados.
     *
     * @return {@code true} si la nave alcanzó el objetivo; {@code false} si la
     * nave fue destruida, la ruta se agotó o el hilo fue interrumpido
     */
    private boolean realizarViaje() {
        assert obj != null : "La misión debe tener un objetivo antes de viajar";
        assert getRuta() != null : "La misión debe tener una ruta antes de viajar";

        boolean alcanzoDestino = alcanzoObjetivo();

        try {
            while (!alcanzoDestino && !getRuta().isEmpty()) {

                ElementoDelUniverso elemento =
                        asistente.getTablero().inspeccionarPosicion();

                if (elemento != null) {
                    elemento.interactuar(getNave());
                }

                realizarMovimiento();


                Destino.getInstance().turnoDestino();

                alcanzoDestino = alcanzoObjetivo();

                try {
                    Thread.sleep(3000);
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

        } catch (NaveDestruidaException e) {
            fallarMision(e.getMessage());
            return false;
        }
    }

    /**
     * Incorpora el resultado de la misión al informe.
     * precond: la misión tiene un resultado y un informe
     * precond: la misión fue ejecutada o cancelada durante la preparación
     * postcond: la misión y el informe quedan evaluados
     */
    public void evaluar() {
        assert informe != null : "Debe existir un informe para evaluar la misión";
        assert result != null : "La misión debe tener un resultado antes de evaluarse";
        assert estado == Estado.EJECUTADA || result == Resultado.CANCELADA
                : "Solo puede evaluarse una misión ejecutada o cancelada";

        this.estado = Estado.EVALUADA;
        this.informe.finalizar(getNave(), result, estado);

    }

    /**
     * Aplica la recompensa de una misión exitosa y finaliza su ciclo de vida.
     * precond: la misión fue evaluada
     * postcond: la misión queda cerrada
     * @return informe final de la misión
     */
    public final InformeMision cerrar() {
        assert estado == Estado.EVALUADA : "Solo puede cerrarse una misión evaluada";
        assert result != null : "La misión debe tener un resultado antes de cerrarse";
        assert informe != null : "La misión debe tener un informe antes de cerrarse";

        if (result == Resultado.EXITOSA) {
            aplicarResultadoExitoso();
        }

        asistente.escribirBitacora(cod, "Misión finalizada");

        this.estado = Estado.CERRADA;
        return informe;
    }

    /**
     * Aplica la recompensa específica de una misión exitosa y actualiza los
     * balances del informe.
     * precond: la misión finalizó con resultado exitoso
     * postcond: se aplica la recompensa y se actualizan los balances del informe
     */
    protected abstract void aplicarResultadoExitoso();

    /**
     * Obtiene la ruta pendiente de la misión.
     * @return ruta pendiente de la misión
     */
    public List<Posicion> getRuta(){
        return this.asistente.getRuta();
    }

    /**
     * Calcula e inicializa una ruta hacia la posición indicada.
     *
     * @param p posición de destino
     */
    public void initRuta(Posicion p){
        assert p != null : "La posición de destino no puede ser nula";
        this.asistente.calcRuta(p);
    }

    /**
     * Obtiene el estado actual de la misión.
     * @return estado actual de la misión
     */
    public Estado getEstado(){
        return this.estado;
    }

    /**
     * Obtiene el resultado actual de la misión.
     * @return resultado de la misión, o null si todavía no terminó
     */
    public Resultado getResult(){
        return this.result;
    }

    /**
     * Obtiene el asistente asociado a la misión.
     * @return asistente asociado a la misión
     */
    public AsistenteComando getAsistente(){
        return this.asistente;
    }

    /**
     * Obtiene el consumo inicial de combustible.
     * @return combustible que la misión consume al comenzar
     */
    public int getCombConsumido(){
        return this.combConsumido;
    }

    /**
     * Obtiene el desgaste inicial.
     * @return desgaste que la misión aplica al comenzar
     */
    public int getDesgaste(){
        return this.desgaste;
    }

    /**
     * Obtiene la carga inicial de energía.
     * @return energía que la misión carga al comenzar
     */
    public int getEnergCargada(){
        return this.energCargada;
    }

    /**
     * Obtiene el código identificador.
     * @return código identificador de la misión
     */
    public String getCod(){
        return this.cod;
    }

    /**
     * Configura el consumo inicial de combustible.
     *
     * @param combConsumido cantidad positiva de combustible
     */
    public void setCombConsumido(int combConsumido) {
        assert combConsumido > 0 : "El consumo de combustible debe ser positivo";
        this.combConsumido = combConsumido;
    }

    /**
     * Configura la energía que se cargará al iniciar la misión.
     *
     * @param energCargada cantidad no negativa de energía
     */
    public void setEnergCargada(int energCargada) {
        assert energCargada >= 0 : "La carga de energía no puede ser negativa";
        this.energCargada = energCargada;
    }

    /**
     * Configura el desgaste inicial de la misión.
     *
     * @param desgaste cantidad positiva de desgaste
     */
    public void setDesgaste(int desgaste) {
        assert desgaste > 0 : "El desgaste debe ser positivo";
        this.desgaste = desgaste;
    }

    /**
     * Establece el resultado de la misión.
     *
     * @param result resultado no nulo
     */
    public void setResult(Resultado result) {
        assert result != null : "El resultado no puede ser nulo";
        this.result = result;
    }

    /**
     * Establece el estado de la misión.
     *
     * @param estado estado no nulo
     */
    public void setEstado(Estado estado) {
        assert estado != null : "El estado no puede ser nulo";
        this.estado = estado;
    }

    /**
     * Obtiene la nave operada por el asistente.
     * @return nave operada por el asistente
     */
    public Nave getNave(){return this.getAsistente().getNave();}
}
