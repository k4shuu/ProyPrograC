package misiones;

import asistente.AsistenteComando;
import destinoytablero.Destino;
import destinoytablero.ElementoDelUniverso;
import destinoytablero.Posicion;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.ExcesoEnergiaException;
import excepciones.FaltaEnergiaException;

public class MisionIntercep extends Mision {
    private final int combustibleSolicitado;
    private final int energiaSolicitada;
    /**
     * pre-cond: asistente!=null
     * post-cond: se creo una instancia de MisionIntercep con el asistente dado
     * post-cond: se definen los recursos a gastar
     * @param asistente
     */
    public MisionIntercep(AsistenteComando asistente) {
        super("M01",asistente);
        assert asistente!=null: "El asistente dado para instanciar MisionIntercep no es valido";
        setCombConsumido(4);
        setEnergCargada(5);
        setDesgaste(4);
        combustibleSolicitado = azar.nextInt(51);
        energiaSolicitada = azar.nextInt(51);
    }

    @Override
    public void preparar() throws ExcesoDesgasteException, FaltaCombustibleException, ExcesoEnergiaException{
        this.obj = asistente.getTablero().obtenerObjetivo();
        if(this.asistente.getNave().tieneCombustible(combConsumido))
            if(this.asistente.getNave().sePuedeDesgastar(desgaste)){
                if(this.asistente.getNave().puedeCargarEnergia(energCargada)){
                    initRuta(obj);
                }else {
                    this.asistente.escribirBitacora("M-02","No fue posible completar la carga de energia porque excedia la capacidad de la nave");
                    throw new ExcesoEnergiaException("No será posible completar la carga de energia porque excede la capacidad de la nave", asistente.getNave().getEnergia(), energCargada);
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

    // M01
    protected void aplicarResultadoExitoso() {
        try {
            getNave().usarCombustible(combustibleSolicitado);
            getNave().usarEnergia(energiaSolicitada);
        } catch (FaltaCombustibleException | FaltaEnergiaException ignored) {
        }
    }


    @Override
    protected boolean cumpleCondicionFinal() {
        return getNave().getCombustible() >= combustibleSolicitado && getNave().getEnergia() >= energiaSolicitada;
    }
}
