package misiones;

import bitacora.Bitacora;
import naves.Nave;

public class MisionRetorno extends Mision{

    public MisionRetorno(Nave nav) {
        super( nav, "M03");
    }

    @Override
    public void preparar() {

    }

    @Override
    public void ejecutar() {

    }

    @Override
    public void evaluar() {

    }

    @Override
    public InformeMision cerrar() {
        return null;
    }

}
