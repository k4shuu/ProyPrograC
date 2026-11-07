package warp;

import excepciones.TransicionMotorInvalidaException;

public interface EstadoWarp { //DEFINIRÁ LA PLANTILLA PARA LOS ESTADOS DEL MOTOR
    void ejecutarSaltoComun();
    void ejecutarSaltoWarp() throws TransicionMotorInvalidaException;

    String getNombreEstado();
}