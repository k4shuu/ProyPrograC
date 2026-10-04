package tripulacion;

public class Teniente extends Cargo {
    public Teniente(String identidad,int antiguedad) {
        super(identidad,antiguedad);
    }
    
    public double getSueldo(){
        return 400+400*0.03*this.antiguedad;
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Teniente: "+400+"\t Adicional antiguedad: "+400*0.03*this.antiguedad;
    }
}
