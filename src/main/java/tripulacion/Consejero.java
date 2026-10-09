package tripulacion;

public class Consejero extends Cargo {

    public Consejero(String identidad,int antiguedad) {
        super(identidad,antiguedad);
    }

    public double getSueldo(){
        return 600+600*0.05*this.antiguedad;
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Consejero: "+600+"\t Adicional antiguedad: "+600*0.05*this.antiguedad;
    }
}
