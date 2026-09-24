package warp;

import bitacora.Bitacora;

public class MotorWarp {
    private EstadoWarp estadoActual;
    private Bitacora bitacora;

    public MotorWarp(Bitacora bitacora) {
        this.estadoActual = new EstadoDisponible();
        this.bitacora = bitacora;
    }

    //SEGÚN REQUERIMIENTO E1-02 EL MOTOR COMIENZA EN ESTADO DISPONIBLE
    public MotorWarp() {
        this.estadoActual = new EstadoDisponible();
    }

    //DELEGAMOS CADA ACCIÓNAR SEGÚN EL ESTADO ACTUAL DEL MOTOR
    public void prepararSalto() {
        estadoActual.prepararSalto(this);
    }

    public void ejecutarSalto() {
        estadoActual.ejecutarSalto(this);
    }

    public void desactivarWarp() {
        estadoActual.desactivarWarp(this);
    }

    public void finalizarEnfriamiento() {
        estadoActual.finalizarEnfriamiento(this);
    }

    //GETTERS & SETTERS
    public EstadoWarp getEstadoActual() {
        return estadoActual;
    }

    public void setEstado(EstadoWarp nuevoEstado) {
        if (bitacora != null)
            bitacora.registrar("MotorWarp", "CAMBIO DE ESTADO: " + estadoActual.getNombreEstado() + " -> " + nuevoEstado.getNombreEstado());

        this.estadoActual = nuevoEstado;
    }

    public String getNombreEstadoActual() {
        return estadoActual.getNombreEstado();
    }
}