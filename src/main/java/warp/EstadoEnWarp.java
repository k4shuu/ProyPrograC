package warp;

import excepciones.*;

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

    public void ejecutarSaltoComun() throws FaltaCombustibleException {
            if (this.motor.getNave().getEnergia() < 5)
                this.motor.setEstado(new EstadoPreparandoSalto(motor));
            motor.getNave().usarCombustible(motor.COMBUSTIBLE_NECESARIO_COMUN);

    }

    @Override
    public void ejecutarSaltoWarp() throws FaltaEnergiaException,ExcesoDesgasteException{

            motor.getNave().usarEnergia(this.motor.ENERGIA_NECESARIA_WARP);
            motor.getNave().realizarDesgaste(this.motor.DESGASTE_MECANICO_WARP);
            motor.setEstado(new EstadoEnfriamiento(motor));


    }

    @Override
    public String getNombreEstado() {
        return "ENWARP";
    }
}
