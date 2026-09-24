package warp;

public class EstadoEnWarp implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) {
        throw new IllegalStateException("ERROR [EN WARP]: No se puede preparar otro salto mientras se está en Warp.");
    }

    @Override
    public void ejecutarSalto(MotorWarp motor) {
        throw new IllegalStateException("ERROR [EN WARP]: El motor ya se encuentra en Warp.");
    }

    @Override
    public void desactivarWarp(MotorWarp motor) { //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoEnfriamiento());
    }

    @Override
    public void finalizarEnfriamiento(MotorWarp motor) {
        throw new IllegalStateException("ERROR [EN WARP]: Aún está en Warp, debe desactivarse primero.");
    }

    @Override
    public String getNombreEstado() {
        return "En warp";
    }
}