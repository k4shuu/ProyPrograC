package tripulacion;

import java.util.Objects;

public abstract class DecoratorOrigen extends Tripulante{
    protected Tripulante tripulante;

    /**
     * pre-cond: tripulante!=null
     * post-cond: se creo una instancia de DecoratorOrigen con el tripulante dado
     * @param tripulante
     */
    public DecoratorOrigen(Tripulante tripulante){
        super(tripulante.getIdentidad(), tripulante.getAntiguedad());
        assert tripulante!=null:"El tripulante ingresado para DecoratorOrigen no debe ser null";
        this.tripulante=tripulante;
    }
    @Override
    public int hashCode() {
        return Objects.hash(tripulante);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DecoratorOrigen)) return false;
        DecoratorOrigen otro = (DecoratorOrigen) o;
        return Objects.equals(this.tripulante, otro.tripulante);
    }

}
