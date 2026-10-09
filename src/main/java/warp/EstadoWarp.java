package warp;

import excepciones.TransicionMotorInvalidaException;

public interface EstadoWarp { //DEFINIRÁ LA PLANTILLA PARA LOS ESTADOS DEL MOTOR
    void prepararSalto(MotorWarp motor) throws TransicionMotorInvalidaException;
    void ejecutarSalto(MotorWarp motor) throws TransicionMotorInvalidaException;
    void enfriar(MotorWarp motor) throws TransicionMotorInvalidaException;
    void finalizarEnfriamiento(MotorWarp motor) throws TransicionMotorInvalidaException;

    String getNombreEstado();
}