package warp;

import destinoytablero.Posicion;
import destinoytablero.Tablero;
import excepciones.FaltaCombustibleException;
import excepciones.NaveDestruidaException;
import excepciones.TransicionMotorInvalidaException;
import naves.Nave;

import java.util.List;

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
    public void ejecutarSaltoComun() throws FaltaCombustibleException {

            motor.setEstado(new EstadoPreparandoSalto(this.motor));
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);

    }

    @Override
    public void ejecutarSaltoWarp() throws FaltaCombustibleException {
            this.motor.setEstado(new EstadoPreparandoSalto(this.motor));
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);

    }

    @Override
    public String getNombreEstado() {
        return "DISPONIBLE";
    }
}