package warp;

import destinoytablero.Posicion;
import excepciones.*;

import java.util.List;

public interface EstadoWarp { //DEFINIRÁ LA PLANTILLA PARA LOS ESTADOS DEL MOTOR
    void ejecutarSaltoComun() throws FaltaCombustibleException;
    void ejecutarSaltoWarp() throws FaltaCombustibleException, ExcesoDesgasteException, FaltaEnergiaException;

    String getNombreEstado();
}