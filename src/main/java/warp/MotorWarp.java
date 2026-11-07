package warp;

import bitacora.Bitacora;
import excepciones.FaltaCombustibleException;
import excepciones.TransicionMotorInvalidaException;
import naves.Nave;

public class MotorWarp {
    final int COMBUSTIBLE_NECESARIO_COMUN = 2;
    final int ENERGIA_NECESARIA_WARP = 5;
    final int DESGASTE_MECANICO_WARP = 3;
    private EstadoWarp estadoActual;
    private Nave nave;

    //SEGÚN REQUERIMIENTO E1-02 EL MOTOR COMIENZA EN ESTADO DISPONIBLE
    public MotorWarp(Nave nave) {
        this.estadoActual = new EstadoDisponible(this);
        this.nave = nave;
    }

    public void ejecutarSaltoComun() {
        estadoActual.ejecutarSaltoComun();
    }

    //DELEGAMOS CADA ACCIONAR SEGÚN EL ESTADO ACTUAL DEL MOTOR
    public void ejecutarSaltoWarp() throws TransicionMotorInvalidaException{
        estadoActual.ejecutarSaltoWarp();
    }

    //GETTERS & SETTERS
    public EstadoWarp getEstadoActual() {
        return estadoActual;
    }

    /**
     * pre-cond: nuevoEstado != null
     * post-con: se actualizará el estado interno del motor al pasado por parámetro
     * @param nuevoEstado estado para actualizar
     */
    public void setEstado(EstadoWarp nuevoEstado) {
        assert nuevoEstado != null : "Estado pasado por parámetro es nulo.";

        this.estadoActual = nuevoEstado;
    }

    public String getNombreEstadoActual() {
        return estadoActual.getNombreEstado();
    }

    public Nave getNave(){
        return nave;
    }
}