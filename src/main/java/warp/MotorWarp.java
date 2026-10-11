package warp;

import bitacora.Bitacora;
import excepciones.TransicionMotorInvalidaException;

public class MotorWarp {
    private EstadoWarp estadoActual;


    //SEGÚN REQUERIMIENTO E1-02 EL MOTOR COMIENZA EN ESTADO DISPONIBLE
    public MotorWarp() {
        this.estadoActual = new EstadoDisponible(this);
    }

    //DELEGAMOS CADA ACCIÓNAR SEGÚN EL ESTADO ACTUAL DEL MOTOR
    public void prepararSalto() throws TransicionMotorInvalidaException {
        estadoActual.prepararSalto();
    }

    public void ejecutarSalto() throws TransicionMotorInvalidaException {
        estadoActual.ejecutarSalto();
    }

    public void enfriar() throws TransicionMotorInvalidaException {
        estadoActual.enfriar();
    }

    public void finalizarEnfriamiento() throws TransicionMotorInvalidaException {
        estadoActual.finalizarEnfriamiento();
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
}