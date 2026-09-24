package warp;

public class EstadoEnfriamiento implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) {
        throw new IllegalStateException("ERROR [ENFRIAMIENTO]: El motor está en enfriamiento.");
    }

    @Override
    public void ejecutarSalto(MotorWarp motor) {
        throw new IllegalStateException("ERROR [ENFRIAMIENTO]: El motor está en enfriamiento.");
    }

    @Override
    public void desactivarWarp(MotorWarp motor) {
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