package warp;

import excepciones.TransicionMotorInvalidaException;

public class EstadoEnfriamiento implements EstadoWarp {
    private MotorWarp motor;

    /**
     * pre-cond: motor != null
     * post-con: el atributo motor contendrá una referencia al motor pasado por parámetro
     * @param motor motor que cambiará de estado
     * @returns EstadoEnfriamiento
     */
    public EstadoEnfriamiento(MotorWarp motor){
        this.motor = motor;
    }

    @Override
    public void prepararSalto() throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [ENFRIAMIENTO]: El motor está en enfriamiento, no puede preparar salto.","Enfriamiento","PreparandoSalto");
    }

    @Override
    public void ejecutarSalto() throws TransicionMotorInvalidaException {
        throw new TransicionMotorInvalidaException("ERROR [ENFRIAMIENTO]: El motor está en enfriamiento, no puede ejecutar un salto.","Enfriamiento","EnWarp");
    }

    @Override
    public void enfriar() {
        //ACCIÓN ACTUAL
    }

    @Override
    public void finalizarEnfriamiento() { //TRANSICIÓN VÁLIDA
        motor.setEstado(new EstadoDisponible(motor));
    }

    @Override
    public String getNombreEstado() {
        return "ENFRIAMIENTO";
    }
}