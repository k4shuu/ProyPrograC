package naves;

import bitacora.Bitacora;
import warp.MotorWarp;

public class NaveCarguero extends Nave{

    public NaveCarguero(String identidad, int combustible, int energia, int desgaste, Bitacora bitacora){
        super(identidad, combustible, energia, desgaste, bitacora);
    }

    @Override
    public String toString(){
       return "Nave Carguero" + super.toString();
    }
}
