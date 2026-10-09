package tripulacion;

public class Alferez extends Cargo{
    private static double sueldoBase = 200;
    private static float multiplicador = 0.005f;

    public Alferez(String identidad, int antiguedad) {
        super(identidad, antiguedad);
    }

    public double getSueldo(){
        return sueldoBase*(1 + multiplicador*antiguedad);
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Alferez: " + sueldoBase + "\t Adicional antiguedad: " + sueldoBase*multiplicador*antiguedad;
    }
}