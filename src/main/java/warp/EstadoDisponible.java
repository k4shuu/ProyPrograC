package warp;

public class EstadoDisponible implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) { //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoPreparandoSalto());
    }

    @Override
    public void ejecutarSalto(MotorWarp motor) {
        throw new IllegalStateException("ERROR [DISPONIBLE]: Debe preparar el salto primero.");
    }

    @Override
    public void desactivarWarp(MotorWarp motor) {
        throw new IllegalStateException("ERROR [DISPONIBLE]: El motor no se encuentra en Warp.");
    }

    @Override
    public void finalizarEnfriamiento(MotorWarp motor) {
        throw new IllegalStateException("ERROR [DISPONIBLE]: El motor no está en enfriamiento.");
    }

    @Override
    public String getNombreEstado() {
        return "Disponible";
    }
}