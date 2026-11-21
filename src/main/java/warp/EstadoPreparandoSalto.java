package warp;

import excepciones.FaltaCombustibleException;
import excepciones.FaltaEnergiaException;
import excepciones.NaveDestruidaException;
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

    public void ejecutarSaltoComun() throws FaltaCombustibleException {
        if (this.motor.getNave().getEnergia() >= 5)
                this.motor.setEstado(new EstadoEnWarp(motor));
        motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);

    }

    @Override
    public void ejecutarSaltoWarp() throws FaltaCombustibleException {   //TRANSICIÓN VÁLIDA
            if (this.motor.getNave().getEnergia() >= 5)
                this.motor.setEstado(new EstadoEnWarp(motor));
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);

    }

    @Override
    public String getNombreEstado() {
        return "PREPARANDOSALTO";
    }
}