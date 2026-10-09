package tripulacion;

public class Capitan extends Cargo {
    private static double sueldoBase = 1000;
    private static float multiplicador = 0.2f;

    public Capitan(String identidad, int antiguedad) {
        super(identidad, antiguedad);
    }

    public double getSueldo(){
        return sueldoBase*(1 + multiplicador*antiguedad);
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Capitán: " + sueldoBase + "\t Adicional antiguedad: " + sueldoBase*multiplicador*antiguedad;
    }
}