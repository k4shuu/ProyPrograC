package warp;

import excepciones.TransicionMotorInvalidaException;

public interface EstadoWarp { //DEFINIRÁ LA PLANTILLA PARA LOS ESTADOS DEL MOTOR
    void prepararSalto() throws TransicionMotorInvalidaException;
    void ejecutarSalto() throws TransicionMotorInvalidaException;
    void enfriar() throws TransicionMotorInvalidaException;
    void finalizarEnfriamiento() throws TransicionMotorInvalidaException;

    String getNombreEstado();
}