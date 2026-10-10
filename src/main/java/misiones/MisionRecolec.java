package misiones;

import asistente.AsistenteComando;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.ExcesoEnergiaException;

public class MisionRecolec extends Mision{

    /**
     * pre-cond: asistente!=null
     * post-cond: se creo una instancia de MisionRecolec con el asistente dado
     * @param asistente
     */
    public MisionRecolec(AsistenteComando asistente) {

        super("M02");
        assert asistente==null: "El asistente dado para instanciar MisionRecolec no es valido";
        this.asistente=asistente;
        this.combConsumido= 4;
        this.energCargada = 5;
        this.desgaste = 4;
    }

    @Override
    public void preparar() throws ExcesoDesgasteException, FaltaCombustibleException , ExcesoEnergiaException{
        if(this.asistente.getNave().tieneCombustible(combConsumido))
            if(this.asistente.getNave().sePuedeDesgastar(desgaste)){
                if(this.asistente.getNave().puedeCargarEnergia(energCargada)){
                    this.asistente.solicitarPreparacionSalto(this.asistente.getNave());
                }else {
                    this.asistente.escribirBitacora("M-02","No fue posible completar la carga de energia porque excedia la capacidad de la nave");
                    throw new ExcesoEnergiaException("No fue posible completar la carga de energia porque excedia la capacidad de la nave", asistente.getNave().getEnergia(), energCargada);
                }
            }
            else{
                this.asistente.escribirBitacora("M-02","ERROR: el desgaste de la mision excede lo permitido para la nave ");
                throw new ExcesoDesgasteException("ERROR: el desgaste de la mision excede lo permitido para la nave",this.asistente.getNave().getDesgaste(),desgaste);
            }
        else {
            this.asistente.escribirBitacora("M-02","ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
            throw new FaltaCombustibleException("ERROR: La nave no tiene el combustible necesario para ejecutar la mision",combConsumido,this.asistente.getNave().getCombustible());
        }
        this.estado = Estado.PREPARADA;
    }

    @Override
    public void ejecutar() throws FaltaCombustibleException, ExcesoDesgasteException, ExcesoEnergiaException {
        this.asistente.getNave().usarCombustible(combConsumido);
        this.asistente.getNave().realizarDesgaste(desgaste);
        this.asistente.getNave().cargarEnerg(energCargada);
        this.estado = Estado.EJECUTADA;
    }

    @Override
    public void evaluar() {;
        System.out.println("Evaluando resultados");
        this.estado = Estado.EVALUADA;
        this.result = Resultado.EXITOSA;
    }

    @Override
    public InformeMision cerrar() {
        this.asistente.escribirBitacora("Mision-02", "Misión finalizada, mas informacion en el respectivo informe");
        Estado lastState = this.estado;
        this.estado = Estado.CERRADA;
        return new InformeMision("M02",4, 5,4,result,lastState);
    }

}
