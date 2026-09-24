package warp;

public interface EstadoWarp { //DEFINIRÁ LA PLANTILLA PARA LOS ESTADOS DEL MOTOR
    void prepararSalto(MotorWarp motor);
    void ejecutarSalto(MotorWarp motor);
    void desactivarWarp(MotorWarp motor);
    void finalizarEnfriamiento(MotorWarp motor);

    String getNombreEstado();
}