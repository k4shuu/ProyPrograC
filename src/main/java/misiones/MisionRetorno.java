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
        if(this.asistente.getNave().tieneCombustible(combConsumido))
            if(this.asistente.getNave().sePuedeDesgastar(desgaste)){
                this.asistente.solicitarPreparacionSalto(this.asistente.getNave());
            }
            else{
                this.asistente.escribirBitacora("M-02","ERROR: el desgaste de la mision excede lo permitido para la nave");
                throw new ExcesoDesgasteException("ERROR: el desgaste de la mision excede lo permitido para la nave ",this.asistente.getNave().getDesgaste(),desgaste);
            }
        else {
            this.asistente.escribirBitacora("M-02","ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
            throw new FaltaCombustibleException("ERROR: La nave no tiene el combustible necesario para ejecutar la mision",combConsumido,this.asistente.getNave().getDesgaste());
        }
        this.estado = Estado.PREPARADA;
    }

    @Override
    public void ejecutar() throws FaltaCombustibleException, ExcesoDesgasteException {
        this.asistente.getNave().usarCombustible(combConsumido);
        this.asistente.getNave().realizarDesgaste(desgaste);
        this.estado = Estado.EJECUTADA;
    }

    @Override
    public void evaluar() {
        System.out.println("Evaluando resultados");
        this.estado = Estado.EVALUADA;
        this.result = Resultado.EXITOSA;
    }

    @Override
    public InformeMision cerrar() {
        this.asistente.escribirBitacora("Mision-03", "Misión finalizada, mas informacion en el respectivo informe");
        Estado lastState = this.estado;
        this.estado = Estado.CERRADA;
        return new InformeMision("M03",combConsumido, energCargada,desgaste,result,lastState);
    }

}
