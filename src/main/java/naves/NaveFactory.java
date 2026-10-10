package naves;

import bitacora.Bitacora;
import warp.MotorWarp;

public class NaveFactory {

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: combustible>0 y combustible<=100
     * pre-cond: energia>0 y energia<=100
     * pre-cond: desgaste>0 y desgaste<=100
     * post-cond:??
     * @param tipo
     * @param identidad
     * @param combustible
     * @param energia
     * @param desgaste
     * @return
     */
    public static Nave crearNave(String tipo, String identidad, int combustible, int energia, int desgaste){
        assert combustible>0 && combustible<=100: "El combustible ingresado debe ser mayor a cero y menor o igual a 100" ;
        assert energia>0 && energia<=100:"La energia ingresada debe ser mayor a cero y menor o igual a 100";
        assert identidad!=null && !identidad.isEmpty() :"La identidad ingresada debe ser distinta de null y de cadena vacia";
        assert desgaste>0 && desgaste<=100:"El desgaste ingresado debe ser mayor a cero y menor o igual a 100";
        return switch (tipo.toUpperCase()) {
            case "EXPLORADORA" -> new NaveExploradora(identidad, combustible, energia, desgaste);
            case "CARGUERO" -> new NaveCarguero(identidad, combustible, energia, desgaste);
            case "COMBATE" -> new NaveCombate(identidad, combustible, energia, desgaste);
            default -> throw new IllegalArgumentException("Nave desconocida: " + tipo);
            //Crear exception de tipoNaveIncorrecto
        };
    }
}
