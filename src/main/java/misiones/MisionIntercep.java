package misiones;

import asistente.AsistenteComando;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.ExcesoEnergiaException;

public class MisionIntercep extends Mision {

    public MisionIntercep(AsistenteComando asistente) {

        super("M01");
        this.asistente=asistente;
    }

    @Override
    public void preparar() throws ExcesoDesgasteException, FaltaCombustibleException{
        if(this.asistente.getNave().tieneCombustible(4))
            if(this.asistente.getNave().sePuedeDesgastar(4)){

                this.asistente.solicitarPreparacionSalto(this.asistente.getNave());

            }
            else{
                this.asistente.escribirBitacora("M-01","ERROR: el desgaste de la mision excede lo permitido para la nave ");
                throw new ExcesoDesgasteException("ERROR: el desgaste de la mision excede lo permitido para la nave ",this.asistente.getNave().getDesgaste(),4);

            }
        else {
                this.asistente.escribirBitacora("M-01","ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
                throw new FaltaCombustibleException("ERROR: La nave no tiene el combustible necesario para ejecutar la mision",4,this.asistente.getNave().getCombustible());
            }
    }

    @Override
    public void ejecutar() throws ExcesoEnergiaException,FaltaCombustibleException, ExcesoDesgasteException {
        this.asistente.getNave().usarCombustible(4);
        this.asistente.getNave().realizarDesgaste(4);
        this.asistente.getNave().cargarEnerg(5);
        this.asistente.solicitarEjecucionSalto(this.asistente.getNave());
        //escribir en bitacora los recursos que uso la mision y que se ejecuto aca
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
        //this.asistente.escribirBitacora(informe);
        //informe no seria hijo entrada
        return informe;
    }
}
