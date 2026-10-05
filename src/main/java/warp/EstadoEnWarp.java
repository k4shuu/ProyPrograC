package warp;

import excepciones.TransicionMotorInvalidaException;

public class EstadoEnWarp implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [EN WARP]: No se puede preparar otro salto mientras se está en Warp.","EnWarp","PreparandoSalto");
    }

    @Override
    public void ejecutarSalto(MotorWarp motor) {
        //ACCIÓN ACTUAL
    }

    @Override
    public void enfriar(MotorWarp motor) { //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoDisponible());
    }

    @Override
    public void finalizarEnfriamiento(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [EN WARP]: Aún está en Warp, no puede finalizar enfriamiento","EnWarp","Disponible");
    }

    @Override
    public String getNombreEstado() {
        return "En warp";
    }
}