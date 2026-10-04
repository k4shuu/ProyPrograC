package warp;

import excepciones.TransicionMotorInvalidaException;

public class EstadoPreparandoSalto implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) {
        //ACCIÓN ACTUAL
    }

    @Override
    public void ejecutarSalto(MotorWarp motor) {   //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoEnWarp());
    }

    @Override
    public void enfriar(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("Error [PREPARANDO SALTO]: El motor está preparando el salto, no puede enfriar.","PreparandoSalto","Enfriamiento");
    }

    @Override
    public void finalizarEnfriamiento(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("Error [PREPARANDO SALTO]: El motor no puede finalizar enfriamiento.","PreparandoSalto","EnWarp");
    }

    @Override
    public String getNombreEstado() {
        return "Preparando salto";
    }
}