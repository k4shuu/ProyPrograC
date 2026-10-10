package warp;

import bitacora.Bitacora;
import excepciones.TransicionMotorInvalidaException;

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
    public void prepararSalto() throws TransicionMotorInvalidaException {
        estadoActual.prepararSalto(this);
    }

    public void ejecutarSalto() throws TransicionMotorInvalidaException {
        estadoActual.ejecutarSalto(this);
    }

    public void enfriar() throws TransicionMotorInvalidaException {
        estadoActual.enfriar(this);
    }

    public void finalizarEnfriamiento() throws TransicionMotorInvalidaException {
        estadoActual.finalizarEnfriamiento(this);
    }

    //GETTERS & SETTERS
    public EstadoWarp getEstadoActual() {
        return estadoActual;
    }

    public void cambiarEstado(EstadoWarp nuevoEstado){
        try {
            switch (nuevoEstado.getNombreEstado()) {
                case "DISPONIBLE"      -> estadoActual.finalizarEnfriamiento(this);
                case "ENFRIAMIENTO"    -> estadoActual.enfriar(this);
                case "ENWARP"          -> estadoActual.ejecutarSalto(this);
                case "PREPARANDOSALTO" -> estadoActual.prepararSalto(this);
                default -> throw new TransicionMotorInvalidaException("Transición desconocida", estadoActual.getNombreEstado(), nuevoEstado.getNombreEstado());
            }
        }catch(TransicionMotorInvalidaException e){
            System.out.println(e.getMensaje());
        }
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