package warp;

import excepciones.TransicionMotorInvalidaException;

public class EstadoDisponible implements EstadoWarp {
    @Override
    public void prepararSalto(MotorWarp motor) { //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoPreparandoSalto());
    }

    @Override
    public void ejecutarSalto(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [DISPONIBLE]: Debe preparar el salto antes de ejecutarlo.","Disponible","EnWarp");
    }

    @Override
    public void enfriar(MotorWarp motor) throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [DISPONIBLE]: El motor no se encuentra en Warp.","Disponible","Enfriamiento");
    }

    @Override
    public void finalizarEnfriamiento(MotorWarp motor) {
        //ACCIÓN ACTUAL
    }


    @Override
    public String getNombreEstado() {
        return "Disponible";
    }
}