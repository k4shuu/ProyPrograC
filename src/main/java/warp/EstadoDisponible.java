package warp;

import excepciones.TransicionMotorInvalidaException;

public class EstadoDisponible implements EstadoWarp {
    private MotorWarp motor;

    /**
     * pre-cond: motor != null
     * post-con: el atributo motor contendrá una referencia al motor pasado por parámetro
     * @param motor motor que cambiará de estado
     * @returns EstadoDisponible
     */
    public EstadoDisponible(MotorWarp motor){
        this.motor = motor;
    }

    @Override
    public void prepararSalto() { //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoPreparandoSalto(motor));
    }

    @Override
    public void ejecutarSalto() throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [DISPONIBLE]: Debe preparar el salto antes de ejecutarlo.","Disponible","EnWarp");
    }

    @Override
    public void enfriar() throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [DISPONIBLE]: El motor no se encuentra en Warp.","Disponible","Enfriamiento");
    }

    @Override
    public void finalizarEnfriamiento() {
        //ACCIÓN ACTUAL
    }


    @Override
    public String getNombreEstado() {
        return "DISPONIBLE";
    }
}