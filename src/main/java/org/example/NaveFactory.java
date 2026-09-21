package org.example;

public class NaveFactory {
    public static Nave crearNave(String tipo,String identidad,int combustible,int energia, int desgaste){
        switch (tipo.toUpperCase()){
            case "EXPLORADORA": return new NaveExploradora(identidad,combustible,energia,desgaste);
            case "CARGUERO": return new NaveCarguero(identidad,combustible,energia,desgaste);
            case "COMBATE": return new NaveCombate(identidad,combustible,energia,desgaste);
            default: throw  new IllegalArgumentException("Nave desconocida: "+tipo);
        }
    }
}
