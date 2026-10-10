package naves;

import bitacora.Bitacora;
import warp.MotorWarp;

public class NaveCarguero extends Nave{

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: combustible>0 y combustible<=100
     * pre-cond: energia>0 y energia<=100
     * pre-cond: desgaste>0 y desgaste<=100
     * post-cond: se creo una instancia nave carguero con los parametros dados
     * @param identidad
     * @param combustible
     * @param energia
     * @param desgaste
     */
    public NaveCarguero(String identidad, int combustible, int energia, int desgaste){
        super(identidad, combustible, energia, desgaste);
        assert combustible>0 && combustible<=100: "El combustible ingresado debe ser mayor a cero y menor o igual a 100" ;
        assert energia>0 && energia<=100:"La energia ingresada debe ser mayor a cero y menor o igual a 100";
        assert identidad!=null && !identidad.isEmpty() :"La identidad ingresada debe ser distinta de null y de cadena vacia";
        assert desgaste>0 && desgaste<=100:"El desgaste ingresado debe ser mayor a cero y menor o igual a 100";
    }

    @Override
    public String toString(){
       return "Nave Carguero" + super.toString();
    }
}
