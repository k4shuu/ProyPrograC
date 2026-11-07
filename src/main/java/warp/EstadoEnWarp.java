package warp;

import excepciones.FaltaCombustibleException;
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

    public void ejecutarSaltoComun() {
        try {
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);
        }catch(FaltaCombustibleException e){
            //System.out.println(e.getMessage());
        }
    }

    @Override
    public void ejecutarSaltoWarp() throws TransicionMotorInvalidaException{
        motor.setEstado(new EstadoEnfriamiento(motor));
        throw new TransicionMotorInvalidaException("Error: Transición Inválida", getNombreEstado(), "En Warp");
    }

    @Override
    public String getNombreEstado() {
        return "ENWARP";
    }
}
