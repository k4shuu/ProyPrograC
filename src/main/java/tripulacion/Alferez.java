package tripulacion;

public class Alferez extends Cargo{

    public Alferez(String identidad,int antiguedad) {
        super(identidad,antiguedad);
    }

    public double getSueldo(){
        return 200+200*0.005*this.antiguedad;
    }

    public String getConceptoSueldo(){
        return "Sueldo base por cargo: "+200+" Adicional antiguedad: "+200*0.005*this.antiguedad;
    }
}
