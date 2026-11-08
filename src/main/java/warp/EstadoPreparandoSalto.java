package warp;

import excepciones.FaltaCombustibleException;
import excepciones.FaltaEnergiaException;
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

    public void ejecutarSaltoComun() {
        try {
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);
        }catch(FaltaCombustibleException e){
            //System.out.println(e.getMessage());
        }
    }

    @Override
    public void ejecutarSaltoWarp() throws TransicionMotorInvalidaException {   //TRANSICIÓN VÁLIDA
        try{
            motor.getNave().usarEnergia(motor.ENERGIA_NECESARIA_WARP);
            motor.setEstado(new EstadoEnWarp(motor));
        }catch(FaltaEnergiaException e){
            //System.out.println(e.getMessage());
            ejecutarSaltoComun();
            throw new TransicionMotorInvalidaException(e.getMessage(), this.getNombreEstado(), "ENWARP");
        }
    }

    @Override
    public String getNombreEstado() {
        return "PREPARANDOSALTO";
    }
}