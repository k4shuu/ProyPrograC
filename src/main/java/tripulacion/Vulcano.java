package tripulacion;

import java.util.Objects;

public class Vulcano extends DecoratorOrigen {
    private static double subsidio = 30;

    /**
     * pre-cond: tripulante!=null
     * post-cond: se creo una instancia de Vulcano
     * @param tripulante
     */
    public Vulcano(Tripulante tripulante) {
        super(tripulante);
        assert tripulante!=null: "El tripulante para constructor de Vulcano ingresado debe ser distinto de null";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vulcano)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.tripulante);
    }

    public double getSueldo(){
        return this.tripulante.getSueldo() + subsidio;
    }
    public String getInfo(){
        return this.tripulante.getInfo()+" Origen: Vulcano ";
    }

    @Override
    public String getCargo() {
        return this.tripulante.getCargo();
    }

    public String getConceptoSueldo(){
        return this.tripulante.getConceptoSueldo() + "\t Subsidio Vulcano: " + subsidio + " Sueldo total: " + this.getSueldo();
    }
}
