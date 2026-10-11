package asistente;

import bitacora.Bitacora;
import bitacora.Entrada;
import excepciones.TransicionMotorInvalidaException;
import misiones.InformeMision;
import misiones.Mision;
import naves.Nave;

public class AsistenteComando { //INTERMEDIARIO ENTRE CAPITÁN Y NAVE
    private final Bitacora bitacora;
    private final Nave nave;

    /**
     * pre-cond:bitacora!=null
     * pre-cond: nave!=null
     * post-cond: Asistente de comando tendrá registrada la nave y la bitacora
     * @param bitacora Bitacora que será registrada en el asistente
     * @param nave Nave que será registrada en el asistente
     */
    public AsistenteComando(Bitacora bitacora, Nave nave) {
        assert bitacora!=null:"La bitacora debe ser distinta de null";
        assert nave!=null:"La nave debe ser distinta de null";
        this.bitacora = bitacora;
        this.nave = nave;
    }

    public Nave getNave(){
        return nave;
    }

    //BITACORA
    /**
     * pre-cond: origen!=null y origen!=""
     * pre-cond: mensaje!=null y mensaje!=""
     * post-cond: El tamaño de entradas de la bitácora se incrementará en 1
     * @param origen Área de donde viene el evento
     * @param mensaje Contenido del evento a registrar
     */
    public void escribirBitacora(String origen, String mensaje){
        this.bitacora.registrar(origen,mensaje);
    }

    /**
     * pre-cond: entrada!=null y mensaje!=""
     * post-cond: escribir en la bitacora la entrada recibida
     * @param entrada Contenido del evento a registrar
     */
    public void escribirBitacora(Entrada entrada){
        assert entrada!=null :"La entrada debe ser distinta de null";
        this.bitacora.registrar(entrada);
    }

    public Bitacora getBitacora(){
        return bitacora;
    }

    //ÓRDENES PARA EL MOTOR WARP
    /**

     * post-cond: El motor warp de la nave cambiará de estado a Preparando Salto
     */
    public void solicitarPreparacionSalto() {
        try {
            nave.getMotorWarp().prepararSalto();
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: Ordenó preparar salto Warp.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    /**
     * pre-cond: nave != null
     * post-cond: El motor warp de la nave cambiará de estado a En Warp
     */
    public void solicitarEjecucionSalto() {
        try {
            nave.getMotorWarp().ejecutarSalto();
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: Ejecutó salto Warp con éxito.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    /**
     * pre-cond: nave != null
     * post-cond: El motor warp de la nave cambiará de estado a Enfriamiento
     */
    public void solicitarDesactivarWarp() {
        try {
            nave.getMotorWarp().enfriar();
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: Desactivó motor Warp (entrando en enfriamiento).");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    /**
     * pre-cond: nave != null
     * post-cond: El motor warp de la nave cambiará de estado a Disponible
     */
    public void solicitarFinalizarEnfriamiento() {
        try {
            nave.getMotorWarp().finalizarEnfriamiento();
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: Finalizó enfriamiento. Motor listo.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    /**
     * pre-cond: nave != null
     */
    public void mostrarLiquidacionHaberes(){
        nave.mostrarLiquidacionTripulacion();
    }
}