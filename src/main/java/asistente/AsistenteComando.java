package asistente;

import bitacora.Bitacora;
import excepciones.TransicionMotorInvalidaException;
import misiones.Mision;
import naves.Nave;

public class AsistenteComando { //INTERMEDIARIO ENTRE CAPITÁN Y NAVE
    private final Bitacora bitacora;
    private final Nave nave;

    /**
     * pre-cond:
     * post-cond: Asistente de comando tendrá registrada la nave y la bitacora
     * @param bitacora Bitacora que será registrada en el asistente
     * @param nave Nave que será registrada en el asistente
     */
    public AsistenteComando(Bitacora bitacora, Nave nave) {
        this.bitacora = bitacora;
        this.nave = nave;
    }

    public Nave getNave(){
        return nave;
    }

    //BITACORA
    /**
     * post-cond: El tamaño de entradas de la bitácora se incrementará en 1
     * @param origen Área de donde viene el evento
     * @param mensaje Contenido del evento a registrar
     */
    public void escribirBitacora(String origen, String mensaje){
        this.bitacora.registrar(origen,mensaje);
    }

    //ÓRDENES PARA EL MOTOR WARP
    /**
     * pre-cond: nave != null
     * post-cond: El motor warp de la nave cambiará de estado a Preparando Salto
     * @param nave Nave que solicita cambio de estado de motor warp
     */
    public void solicitarPreparacionSalto(Nave nave) {
        try {
            nave.getMotorWarp().prepararSalto();
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: Ordenó preparar salto Warp.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    /**
     * pre-cond: nave != null
     * post-cond: El motor warp de la nave cambiará de estado a En Warp
     * @param nave Nave que solicita cambio de estado de motor warp
     */
    public void solicitarEjecucionSalto(Nave nave) {
        try {
            nave.getMotorWarp().ejecutarSalto();
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: Ejecutó salto Warp con éxito.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    /**
     * pre-cond: nave != null
     * post-cond: El motor warp de la nave cambiará de estado a Enfriamiento
     * @param nave Nave que solicita cambio de estado de motor warp
     */
    public void solicitarDesactivarWarp(Nave nave) {
        try {
            nave.getMotorWarp().enfriar();
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: Desactivó motor Warp (entrando en enfriamiento).");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    /**
     * pre-cond: nave != null
     * post-cond: El motor warp de la nave cambiará de estado a Disponible
     * @param nave Nave que solicita cambio de estado de motor warp
     */
    public void solicitarFinalizarEnfriamiento(Nave nave) {
        try {
            nave.getMotorWarp().finalizarEnfriamiento();
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: Finalizó enfriamiento. Motor listo.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    /**
     * pre-cond: nave != null
     * @param nave Nave cuya liquidación de haberes de su tripulación ha sido solicitada
     */
    public void mostrarLiquidacionHaberes(Nave nave){
        nave.mostrarLiquidacionTripulacion();
    }
}