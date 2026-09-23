public class Consejero extends Cargo {
    public Consejero(String identidad,int antiguedad) {
        super(identidad,antiguedad);
    }
    public double getSueldo(){
        return 600+600*0.05*this.antiguedad;
    }
    public String getConceptoSueldo(){
        return "Sueldo base por cargo: "+600+" Adicional antiguedad: "+600*0.05*this.antiguedad;
    }
}
