package warp;

import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.NaveDestruidaException;
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

    public void ejecutarSaltoComun() throws FaltaCombustibleException {

            motor.setEstado(new EstadoDisponible(motor));
            this.motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);

    }

    @Override
    public void ejecutarSaltoWarp() throws FaltaCombustibleException {
            motor.setEstado(new EstadoDisponible(motor));
            this.motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);
    }

    @Override
    public String getNombreEstado() {
        return "ENFRIAMIENTO";
    }
}