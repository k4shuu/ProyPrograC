package naves;

public class NaveCarguero extends Nave{

    public NaveCarguero(String identidad,int combustible, int energia, int desgaste){
        super(identidad,combustible,energia,desgaste);
    }

    @Override
    public String toString(){
       return "Nave.Nave Carguero" + super.toString();
    }
}
