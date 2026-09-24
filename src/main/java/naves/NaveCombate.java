package naves;

import bitacora.Bitacora;
import warp.MotorWarp;

public class NaveCombate extends Nave{

    public NaveCombate(String identidad, int combustible, int energia, int desgaste, Bitacora bitacora){
        super(identidad, combustible, energia, desgaste, bitacora);
    }

    @Override
    public String toString(){
        return "Nave Combate" + super.toString();
    }
}
