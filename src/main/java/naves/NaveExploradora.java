package naves;

import bitacora.Bitacora;
import warp.MotorWarp;

public class NaveExploradora extends Nave {

    public NaveExploradora(String identidad, int combustible, int energia, int desgaste, Bitacora bitacora){
        super(identidad, combustible, energia, desgaste, bitacora);
    }

    @Override
    public String toString(){
        return "Nave Exploradora" + super.toString();
    }
}
