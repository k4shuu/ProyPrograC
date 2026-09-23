package misiones;

import bitacora.Bitacora;
import naves.Nave;

public class MisionIntercep extends Mision {

    public MisionIntercep(Nave nav) {
        super(nav,"M01");
    }

    @Override
    public void preparar() {

    }

    @Override
    public void ejecutar() {

    }
    ;
    @Override
    public void evaluar() {

    }

    @Override
    public InformeMision cerrar() {
        return null;
    }
}
