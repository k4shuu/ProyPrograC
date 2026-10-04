package warp;

public class EstadoEnWarp implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) {
        throw new IllegalStateException("ERROR [EN WARP]: No se puede preparar otro salto mientras se está en Warp.");
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
    public void finalizarEnfriamiento(MotorWarp motor) {
        throw new IllegalStateException("ERROR [EN WARP]: Aún está en Warp, debe desactivarse primero.");
    }

    @Override
    public String getNombreEstado() {
        return "En warp";
    }
}