package misiones;

import asistente.AsistenteComando;
import destinoytablero.Destino;
import destinoytablero.ElementoDelUniverso;
import destinoytablero.Posicion;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.ExcesoEnergiaException;
import excepciones.FaltaEnergiaException;

public class MisionIntercep extends Mision {
    private final int combustibleSolicitado;
    private final int energiaSolicitada;
    /**
     * pre-cond: asistente!=null
     * post-cond: se creo una instancia de MisionIntercep con el asistente dado
     * post-cond: se definen los recursos a gastar
     * @param asistente
     */
    public MisionIntercep(AsistenteComando asistente) {
        super("M01",asistente);
        assert asistente!=null: "El asistente dado para instanciar MisionIntercep no es valido";
        setCombConsumido(4);
        setEnergCargada(5);
        setDesgaste(4);
        combustibleSolicitado = azar.nextInt(51);
        energiaSolicitada = azar.nextInt(51);
    }

    @Override
    public void preparar() throws ExcesoDesgasteException, FaltaCombustibleException, ExcesoEnergiaException{
        this.obj = asistente.getTablero().obtenerObjetivo();
        if(this.asistente.getNave().tieneCombustible(combConsumido))
            if(this.asistente.getNave().sePuedeDesgastar(desgaste)){
                if(this.asistente.getNave().puedeCargarEnergia(energCargada)){
                    initRuta(obj);
                }else {
                    this.asistente.escribirBitacora("M-02","No fue posible completar la carga de energia porque excedia la capacidad de la nave");
                    throw new ExcesoEnergiaException("No será posible completar la carga de energia porque excede la capacidad de la nave", asistente.getNave().getEnergia(), energCargada);
                }
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

    @Override
    public void ejecutar() throws ExcesoEnergiaException,FaltaCombustibleException, ExcesoDesgasteException {
        this.asistente.getNave().usarCombustible(combConsumido);
        this.asistente.getNave().realizarDesgaste(desgaste);
        this.asistente.getNave().cargarEnerg(energCargada);
        this.estado = Estado.EJECUTADA;

        boolean naveViva=true;
        boolean alcanzoDestino=false;
        // 2. Ciclo de travesía
        while (naveViva && !alcanzoDestino) {
            // --- A. Turno de la Nave ---

            // A.1. Inspección de posición actual utilizando el Tablero
            ElementoDelUniverso elemento = this.asistente.getTablero().inspeccionarPosicion();
            if (elemento != null) {
                elemento.interactuar(this.getNave());
                naveViva = asistente.estaVivaNave();
            }

            //metodo de asistente que valide esta nave es asistente.estaVivaNave que es boolean
            // Validación inmediata de destrucción post-interacción
            if (getNave().getDesgaste() >= 100 || getNave().getEnergia() <= 0 || getNave().getCombustible() <= 0) {
                this.result=Resultado.FALLIDA;
                //seter boolean
                //deberiamos lanzar excepcion??
                return;
            }
            else {
                // A.2. Escaneo
                elemento = this.asistente.getRadarCA().analizarPasoSig(getRuta());


                //Cuando se solicitaria el saltoWarp?? y como hago que dure varios turnos
                // A.3. Planificación y movimiento
                if(elemento==null){
                    asistente.solicitarEjecucionSaltoComun(getRuta().getFirst());
                    getRuta().removeFirst();
                    //nave consume 2 de combustible
                }
                else {
                    this.asistente.solicitarEjecucionSaltoWarp(getRuta().get(1));//no lanza excepcion, salto simple
                    getRuta().removeFirst();
                    getRuta().removeFirst();
                }

                // --- B. Turno del Tablero ---
                Destino.getInstance().turnoDestino();

                // Nota técnica: Pausa en cada iteración
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                }
            }
        }

        // 3. Verificación en destino
        // Solo se llega aquí si el bucle terminó porque alcanzó el destino y sigue viva.
        if (!recursosAdicionales()) {
            fallarMision("Fallo: La nave no conservó los recursos exigidos para la entrega.");
        } else {
            completarMisionExitosamente();
        }
    }

    @Override
    public void evaluar() {
        if (result == null) {
            throw new IllegalStateException(
                    "No se puede evaluar una misión sin resultado"
            );
        }

        this.informe = new InformeMision(
                cod,
                combConsumido,
                energCargada,
                desgaste,
                result,
                estado
        );

        this.estado = Estado.EVALUADA;
    }

    // M01
    protected void aplicarResultadoExitoso() {
        try {
            getNave().usarCombustible(combustibleSolicitado);
            getNave().usarEnergia(energiaSolicitada);
        } catch (FaltaCombustibleException | FaltaEnergiaException ignored) {
        }
    }

    @Override
    public InformeMision cerrar() {
        this.asistente.escribirBitacora("Mision-01", "Misión finalizada, para mas informacion consultar el informe correspondiente");
        if (result == Resultado.EXITOSA) {
            aplicarResultadoExitoso();
        }

        this.estado = Estado.CERRADA;
        return informe;
    }

    private boolean recursosAdicionales(){
        return getNave().getCombustible() >= combustibleSolicitado && getNave().getEnergia() >= energiaSolicitada ;
    }
}
