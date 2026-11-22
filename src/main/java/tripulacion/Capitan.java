package tripulacion;

public class Capitan extends Cargo {
    private static double sueldoBase = 1000;
    private static double multiplicador = 0.2;

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: antiguedad>=0
     * post-cond:se creo una instancia de Capitan con los parametros dados
     * @param identidad
     * @param antiguedad
     */
    public Capitan(String identidad,int antiguedad) {

        super(identidad,antiguedad);
        assert identidad!=null && !identidad.isEmpty() :"La identidad de Capitan ingresada debe ser distinta de null y de cadena vacia";
        assert antiguedad>=0:"La antiguedad de Capitan debe ser mayor o igual a cero";
    }
    public String getInfo(){
        return "Nombre: "+this.getIdentidad()+" Antiguedad: "+this.getAntiguedad()+" Cargo: Capitan ";
    }
    // En Capitan:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Capitan)) return false;
        return super.equals(o);
    }

    @Override
    public String getCargo() {
        return "Capitan";
    }

    public double getSueldo(){
        return sueldoBase*(1 + multiplicador*antiguedad);
    }

    public String getConceptoSueldo(){
        return "Sueldo base por Capitán: " + sueldoBase + "\t Adicional antiguedad: " + sueldoBase*multiplicador*antiguedad;
    }

}
