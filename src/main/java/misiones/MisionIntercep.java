package misiones;

import bitacora.Bitacora;
import naves.Nave;

public class MisionIntercep extends Mision {

    public MisionIntercep() {
        super("M01");
    }

    @Override
    public void preparar(Nave nave) {
        if(nave.tieneCombustible(4))
            if(nave.sePuedeDesgastar(4)){

                nave.getAsistente().solicitarPreparacionSalto(nave);
            }
            else{
                nave.getAsistente().escribirBitacora("M-01","ERROR: el desgaste de la mision excede lo permitido para la nave ");
                throw new IllegalStateException("ERROR: el desgaste de la mision excede lo permitido para la nave ");

            }
        else {
            nave.getAsistente().escribirBitacora("M-01","ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
                throw new IllegalStateException("ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
            }
    }

    @Override
    public void ejecutar(Nave nave) {
        nave.usarCombustible(4);
        nave.realizarDesgaste(4);
        nave.cargarEnerg(5);
        nave.getMotorWarp().ejecutarSalto();
    }
    ;
    @Override
    public void evaluar() {

    }

    @Override
    public InformeMision cerrar() {
        InformeMision informe=new InformeMision("Se llego al objetivo simulado y se realizo la asistencia","M-01");
        return informe;
    }
}
