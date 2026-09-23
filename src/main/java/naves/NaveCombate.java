package naves;

public class NaveCombate extends Nave{

    public NaveCombate(String identidad,int combustible, int energia, int desgaste){
        super(identidad,combustible,energia,desgaste);
    }

    @Override
    public String toString(){
        return "Nave.Nave Combate" + super.toString();
    }
}
