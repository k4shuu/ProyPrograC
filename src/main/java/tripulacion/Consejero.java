package tripulacion;

public class Consejero extends Cargo {
    private static double sueldoBase = 600;
    private static float multiplicador = 0.05f;

    public Consejero(String identidad, int antiguedad) {
        super(identidad, antiguedad);
    }

    public double getSueldo(){
        return sueldoBase*(1 + multiplicador*antiguedad);
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Consejero: " + sueldoBase + "\t Adicional antiguedad: " + sueldoBase*multiplicador*antiguedad;
    }
}
