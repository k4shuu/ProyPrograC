package warp;

import bitacora.Bitacora;
import excepciones.TransicionMotorInvalidaException;

public class MotorWarp {
    private EstadoWarp estadoActual;
    private Bitacora bitacora;

    /**
     * pre-cond: bitacora != null
     * post-con: atributo bitácora contendrá una referencia a la bitácora pasada por parametro
     * @param bitacora canal bitácora para registrar eventos
     * @returns MotorWarp
     */
    public MotorWarp(Bitacora bitacora) {
        assert bitacora != null : "La bitácora pasada por parámetro es nula.";

        this.estadoActual = new EstadoDisponible(this);
        this.bitacora = bitacora;

        assert this.bitacora.equals(bitacora) : "El atributo bitácora no almacenó la referencia a la bítacora parámetro.";
    }

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

        if (bitacora != null)
            bitacora.registrar("MotorWarp", "CAMBIO DE ESTADO: " + estadoActual.getNombreEstado() + " -> " + nuevoEstado.getNombreEstado());

        this.estadoActual = nuevoEstado;
    }

    public String getNombreEstadoActual() {
        return estadoActual.getNombreEstado();
    }
}