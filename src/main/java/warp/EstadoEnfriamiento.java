package warp;

import excepciones.TransicionMotorInvalidaException;

public class EstadoEnfriamiento implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [ENFRIAMIENTO]: El motor está en enfriamiento, no puede preparar salto.","Enfriamiento","PreparandoSalto");
    }

    @Override
    public void ejecutarSalto(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [ENFRIAMIENTO]: El motor está en enfriamiento, no puede ejecutar un salto.","Enfriamiento","EnWarp");
    }

    @Override
    public void enfriar(MotorWarp motor) {
        throw new IllegalStateException("ERROR [ENFRIAMIENTO]: El motor ya no está en Warp.");
    }

    @Override
    public void finalizarEnfriamiento(MotorWarp motor) { //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoDisponible());
    }

    @Override
    public String getNombreEstado() {
        return "Enfriamiento";
    }
}