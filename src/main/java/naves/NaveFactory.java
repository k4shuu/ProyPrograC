package naves;

import bitacora.Bitacora;
import warp.MotorWarp;

public class NaveFactory {

    public static Nave crearNave(String tipo, String identidad, int combustible, int energia, int desgaste, Bitacora bitacora){
        return switch (tipo.toUpperCase()) {
            case "EXPLORADORA" -> new NaveExploradora(identidad, combustible, energia, desgaste, bitacora);
            case "CARGUERO" -> new NaveCarguero(identidad, combustible, energia, desgaste, bitacora);
            case "COMBATE" -> new NaveCombate(identidad, combustible, energia, desgaste, bitacora);
            default -> throw new IllegalArgumentException("Nave desconocida: " + tipo);
        };
    }
}
