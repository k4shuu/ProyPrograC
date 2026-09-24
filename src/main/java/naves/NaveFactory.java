package naves;

public class NaveFactory {

    public static Nave crearNave(String tipo,String identidad,int combustible,int energia, int desgaste){
        return switch (tipo.toUpperCase()) {
            case "EXPLORADORA" -> new NaveExploradora(identidad, combustible, energia, desgaste);
            case "CARGUERO" -> new NaveCarguero(identidad, combustible, energia, desgaste);
            case "COMBATE" -> new NaveCombate(identidad, combustible, energia, desgaste);
            default -> throw new IllegalArgumentException("Nave desconocida: " + tipo);
        };
    }
}
