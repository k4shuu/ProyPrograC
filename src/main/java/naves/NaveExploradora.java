package naves;

public class NaveExploradora extends Nave {

    public NaveExploradora(String identidad,int combustible, int energia, int desgaste){
        super(identidad,combustible,energia,desgaste);
    }

    @Override
    public String toString(){
        return "Nave Exploradora" + super.toString();
    }
}
