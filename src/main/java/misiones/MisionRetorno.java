package misiones;

import asistente.AsistenteComando;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;

public class MisionRetorno extends Mision{

    /**
     * pre-cond: asistente!=null
     * post-cond: se creo una instancia de MisionRecotorno con el asistente dado
     * post-cond: se definen los recursos a gastar
     * @param asistente
     */
    public MisionRetorno(AsistenteComando asistente) {
        super( "M03",asistente);
        assert asistente!=null: "El asistente dado para instanciar MisionRecotorno no es valido";
        setCombConsumido(4);
        setDesgaste(4);
    }

    @Override
    public void preparar() throws ExcesoDesgasteException, FaltaCombustibleException {
        this.obj = asistente.getTablero().obtenerObjetivo();
        if(this.asistente.getNave().tieneCombustible(combConsumido))
            if(this.asistente.getNave().sePuedeDesgastar(desgaste)){
                initRuta(obj);
            }else{
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
        this.asistente.escribirBitacora("Mision-03", "Misión finalizada, para mas informacion consultar el informe correspondiente");
        if (result == Resultado.EXITOSA) {
            aplicarResultadoExitoso();
        }

        this.estado = Estado.CERRADA;
        return informe;
    }

    // M03
    protected void aplicarResultadoExitoso() {
        getNave().setCombustible(100);
        getNave().setEnergia(100);
        getNave().mantenimiento();
    }
}