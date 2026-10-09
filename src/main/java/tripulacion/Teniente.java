package tripulacion;

public class Teniente extends Cargo {
    private static double sueldoBase = 400;
    private static float multiplicador = 0.03f;

    public Teniente(String identidad, int antiguedad) {
        super(identidad, antiguedad);
    }

    public double getSueldo(){
        return sueldoBase*(1 + multiplicador*antiguedad);
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Teniente: " + sueldoBase + "\t Adicional antiguedad: " + sueldoBase*multiplicador*antiguedad;
    }
}