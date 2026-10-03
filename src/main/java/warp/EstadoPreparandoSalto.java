package warp;

import excepciones.TransicionMotorInvalidaException;

public class EstadoPreparandoSalto implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) {
        throw new IllegalStateException("Error [PREPARANDO SALTO]: El motor ya se encuentra preparando el salto.");
    }

    @Override
    public void ejecutarSalto(MotorWarp motor) {   //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoEnWarp());
    }

    @Override
    public void enfriar(MotorWarp motor) throws TransicionMotorInvalidaException {  //TRANSICIÓN VÁLIDA

        throw new TransicionMotorInvalidaException("Error [PREPARANDO SALTO]: No puede enfriar","PreparandoSalto","Enfriamiento");
    }

    @Override
    public void finalizarEnfriamiento(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("Error [PREPARANDO SALTO]: No puede finalizar enfriamiento","PreparandoSalto","Disponible");
    }

    @Override
    public String getNombreEstado() {
        return "Preparando salto";
    }
}