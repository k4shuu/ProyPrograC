package warp;

import excepciones.TransicionMotorInvalidaException;


public class EstadoPreparandoSalto implements EstadoWarp {
    private MotorWarp motor;

    /**
     * pre-cond: motor != null
     * post-con: el atributo motor contendrá una referencia al motor pasado por parámetro
     * @param motor motor que cambiará de estado
     * @returns EstadoPreparandoSalto
     */
    public EstadoPreparandoSalto(MotorWarp motor){
        this.motor = motor;
    }

    @Override
    public void prepararSalto() {
        //ACCIÓN ACTUAL
    }

    @Override
    public void ejecutarSalto() {   //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoEnWarp(motor));
    }

    @Override
    public void enfriar() throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("Error [PREPARANDO SALTO]: El motor está preparando el salto, no puede enfriar.","PreparandoSalto","Enfriamiento");
    }

    @Override
    public void finalizarEnfriamiento() throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("Error [PREPARANDO SALTO]: El motor no puede finalizar enfriamiento.","PreparandoSalto","EnWarp");
    }

    @Override
    public String getNombreEstado() {
        return "PREPARANDOSALTO";
    }
}