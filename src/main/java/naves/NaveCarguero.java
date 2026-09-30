package naves;

import bitacora.Bitacora;
import warp.MotorWarp;

public class NaveCarguero extends Nave{

    public NaveCarguero(String identidad, int combustible, int energia, int desgaste){
        super(identidad, combustible, energia, desgaste);
    }

    @Override
    public String toString(){
       return "Nave Carguero" + super.toString();
    }
}
