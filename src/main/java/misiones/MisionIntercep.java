package misiones;

import asistente.AsistenteComando;
import bitacora.Bitacora;
import excepciones.FaltaCombustibleException;
import naves.ExcesoEnergiaException;
import naves.Nave;

public class MisionIntercep extends Mision {

    public MisionIntercep(AsistenteComando asistente) {

        super("M01");
        this.asistente=asistente;
    }

    @Override
    public void preparar() {
        if(this.asistente.getNave().tieneCombustible(4))
            if(this.asistente.getNave().sePuedeDesgastar(4)){

                this.asistente.solicitarPreparacionSalto(this.asistente.getNave());
            }
            else{
                this.asistente.escribirBitacora("M-01","ERROR: el desgaste de la mision excede lo permitido para la nave ");
                throw new IllegalStateException("ERROR: el desgaste de la mision excede lo permitido para la nave ");

            }
        else {
                this.asistente.escribirBitacora("M-01","ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
                throw new IllegalStateException("ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
            }
    }

    @Override
    public void ejecutar() throws ExcesoEnergiaException{
        this.asistente.getNave().usarCombustible(4);
        this.asistente.getNave().realizarDesgaste(4);
        this.asistente.getNave().cargarEnerg(5);
        this.asistente.solicitarEjecucionSalto(this.asistente.getNave());

    }
    ;
    @Override
    public void evaluar() {
        System.out.println("Evaluando resultados");
    }

    @Override
    public InformeMision cerrar() {
        this.asistente.solicitarDesactivarWarp(this.asistente.getNave());
        InformeMision informe=new InformeMision("Se llego al objetivo simulado y se realizo la asistencia","M-01");
        return informe;
    }
}
