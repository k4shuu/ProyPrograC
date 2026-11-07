package warp;

import destinoytablero.Posicion;
import destinoytablero.Tablero;
import excepciones.FaltaCombustibleException;
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

    public void ejecutarSaltoComun(Posicion p) {
        try {
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);
        }catch(FaltaCombustibleException e){
            //System.out.println(e.getMessage());
        }
    }

    @Override
    public void ejecutarSaltoComun() {
        try {
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);
        }catch(FaltaCombustibleException e){
            //System.out.println(e.getMessage());
        }
    }

    @Override
    public void ejecutarSaltoWarp() throws TransicionMotorInvalidaException {
        ejecutarSaltoComun();
        motor.setEstado(new EstadoPreparandoSalto(motor));
        throw new TransicionMotorInvalidaException("Error: Transición Inválida", getNombreEstado(), "En Warp");
    }

    @Override
    public String getNombreEstado() {
        return "DISPONIBLE";
    }
}