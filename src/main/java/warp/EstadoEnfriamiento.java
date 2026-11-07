package warp;

import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
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

    public void ejecutarSaltoComun() {
        try {
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);
        }catch(FaltaCombustibleException e){
            //System.out.println(e.getMessage());
        }
    }

    @Override
    public void ejecutarSaltoWarp() throws TransicionMotorInvalidaException{
        try {
            motor.getNave().realizarDesgaste(motor.DESGASTE_MECANICO_WARP);
            motor.setEstado(new EstadoDisponible(motor));
            ejecutarSaltoComun();
            throw new TransicionMotorInvalidaException("Error: Transición Inválida", getNombreEstado(), "En Warp");
        } catch (ExcesoDesgasteException e){
            System.out.println(e.getMessage());
            throw new TransicionMotorInvalidaException(e.getMessage(), this.getNombreEstado(), "En Warp");
        }
    }

    @Override
    public String getNombreEstado() {
        return "ENFRIAMIENTO";
    }
}