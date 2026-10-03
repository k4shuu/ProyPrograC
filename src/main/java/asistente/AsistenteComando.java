package asistente;

import bitacora.Bitacora;
import excepciones.TransicionMotorInvalidaException;
import misiones.Mision;
import naves.Nave;

public class AsistenteComando { //INTERMEDIARIO ENTRE CAPITÁN Y NAVE
    private final Bitacora bitacora;
    private final Nave nave;

    public AsistenteComando(Bitacora bitacora,Nave nave) {
        this.bitacora = bitacora;
        this.nave=nave;
    }
    public Nave getNave(){
        return nave;
    }
    //BITACORA
    public void escribirBitacora( String origen,String mensaje){
        this.bitacora.registrar(origen,mensaje);
    }

    //ÓRDENES PARA EL MOTOR WARP



    public void solicitarPreparacionSalto(Nave nave) {
        try {
            nave.getMotorWarp().prepararSalto();
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: Ordenó preparar salto Warp.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    public void solicitarEjecucionSalto(Nave nave) {
        try {
            nave.getMotorWarp().ejecutarSalto();
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: Ejecutó salto Warp con éxito.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    public void solicitarDesactivarWarp(Nave nave) {
        try {
            nave.getMotorWarp().enfriar();
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: Desactivó motor Warp (entrando en enfriamiento).");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

    public void solicitarFinalizarEnfriamiento(Nave nave) {
        try {
            nave.getMotorWarp().finalizarEnfriamiento();
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: Finalizó enfriamiento. Motor listo.");
        } catch (TransicionMotorInvalidaException e) {
            bitacora.registrar("AsistenteComando", "[Nave: " + nave.getIdentidad() + "]: " + e.getMensaje());
        }
    }

}