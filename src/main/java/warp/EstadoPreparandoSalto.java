package warp;

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
    public void enfriar(MotorWarp motor) {
        throw new IllegalStateException("Error [PREPARANDO SALTO]: El motor está preparando el salto.");
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