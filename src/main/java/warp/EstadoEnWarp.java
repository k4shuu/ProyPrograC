package warp;

import excepciones.TransicionMotorInvalidaException;

public class EstadoEnWarp implements EstadoWarp {
    private MotorWarp motor;

    /**
     * pre-cond: motor != null
     * post-con: el atributo motor contendrá una referencia al motor pasado por parámetro
     * @param motor motor que cambiará de estado
     * @returns EstadoEnWarp
     */
    public EstadoEnWarp(MotorWarp motor){
        this.motor = motor;
    }

    @Override
    public void prepararSalto() throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [EN WARP]: No se puede preparar otro salto mientras se está en Warp.","EnWarp","PreparandoSalto");
    }

    @Override
    public void ejecutarSalto() {
        //ACCIÓN ACTUAL
    }

    @Override
    public void enfriar() { //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoDisponible(motor));
    }

    @Override
    public void finalizarEnfriamiento() throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [EN WARP]: Aún está en Warp, no puede finalizar enfriamiento","EnWarp","Disponible");
    }

    @Override
    public String getNombreEstado() {
        return "ENWARP";
    }
}
