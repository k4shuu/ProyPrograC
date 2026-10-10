package misiones;

import asistente.AsistenteComando;
import bitacora.Bitacora;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import naves.Nave;

public class MisionRetorno extends Mision{

    /**
     * pre-cond: asistente!=null
     * post-cond: se creo una instancia de MisionRecotorno con el asistente dado
     * @param asistente
     */
    public MisionRetorno(AsistenteComando asistente) {
        super( "M03");
        assert asistente==null: "El asistente dado para instanciar MisionRecotorno no es valido";
        this.asistente=asistente;
    }

    @Override
    public void preparar() throws ExcesoDesgasteException, FaltaCombustibleException {
        if(this.asistente.getNave().tieneCombustible(4))
            if(this.asistente.getNave().sePuedeDesgastar(4)){
                this.asistente.solicitarPreparacionSalto(this.asistente.getNave());
            }
            else{
                this.asistente.escribirBitacora("M-02","ERROR: el desgaste de la mision excede lo permitido para la nave");
                throw new ExcesoDesgasteException("ERROR: el desgaste de la mision excede lo permitido para la nave ",this.asistente.getNave().getDesgaste(),4);
            }
        else {
            this.asistente.escribirBitacora("M-02","ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
            throw new FaltaCombustibleException("ERROR: La nave no tiene el combustible necesario para ejecutar la mision",4,this.asistente.getNave().getDesgaste());
        }
    }

    @Override
    public void ejecutar() throws FaltaCombustibleException, ExcesoDesgasteException {
        this.asistente.getNave().usarCombustible(4);
        this.asistente.getNave().realizarDesgaste(4);

    }

    @Override
    public void evaluar() {
        System.out.println("Evaluando resultados");

    }

    @Override
    public InformeMision cerrar() {
        this.asistente.escribirBitacora("Mision-03", "Misión completada con éxito, mas informacion en el respectivo informe");
        return new InformeMision("M03",4, 0,4);
    }

}
