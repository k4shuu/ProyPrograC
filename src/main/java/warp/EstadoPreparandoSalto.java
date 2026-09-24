package warp;

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
    public void desactivarWarp(MotorWarp motor) {  //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoDisponible());
    }

    @Override
    public void finalizarEnfriamiento(MotorWarp motor) {
        throw new IllegalStateException("Error [PREPARANDO SALTO]: El motor no está en enfriamiento.");
    }

    @Override
    public String getNombreEstado() {
        return "Preparando salto";
    }
}