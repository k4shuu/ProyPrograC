package asistente;

import bitacora.Bitacora;
import bitacora.Entrada;
import excepciones.*;
import naves.Nave;
import destinoytablero.*;

import java.util.List;

public class AsistenteComando { //INTERMEDIARIO ENTRE CAPITÁN Y NAVE
    private final Bitacora bitacora;
    private final Nave nave;
    private final Tablero tablero;
    private final RadarLargoAlcance radarLA;
    private final RadarCortoAlcance radarCA;
    private List<Posicion> ruta = null;

    /**
     * pre-cond:bitacora!=null
     * pre-cond: nave!=null
     * post-cond: Asistente de comando tendrá registrada la nave y la bitacora
     * @param bitacora Bitacora que será registrada en el asistente
     * @param nave Nave que será registrada en el asistente
     */
    public AsistenteComando(Bitacora bitacora, Nave nave, Tablero t) {
        assert bitacora!=null:"La bitacora debe ser distinta de null";
        assert nave!=null:"La nave debe ser distinta de null";
        this.bitacora = bitacora;
        this.nave = nave;
        this.tablero = t;
        this.radarLA = new RadarLargoAlcance(t);
        this.radarCA = new RadarCortoAlcance(t);
    }

    public boolean estaVivaNave(){
        return this.nave.estaViva();
    }

    public Nave getNave(){
        return nave;
    }

    //BITACORA
    /**
     * pre-cond: origen!=null y origen!=""
     * pre-cond: mensaje!=null y mensaje!=""
     * post-cond: El tamaño de entradas de la bitácora se incrementará en 1
     * @param origen Área de donde viene el evento
     * @param mensaje Contenido del evento a registrar
     */
    public void escribirBitacora(String origen, String mensaje){
        this.bitacora.registrar(origen,mensaje);
    }

    /**
     * pre-cond: entrada!=null y mensaje!=""
     * post-cond: escribir en la bitacora la entrada recibida
     * @param entrada Contenido del evento a registrar
     */
    public void escribirBitacora(Entrada entrada){
        assert entrada!=null :"La entrada debe ser distinta de null";
        this.bitacora.registrar(entrada);
    }

    public Bitacora getBitacora(){
        return bitacora;
    }

    //ÓRDENES PARA EL MOTOR WARP
    public void solicitarEjecucionSaltoComun() throws NaveDestruidaException {
        try {
            Posicion p = ruta.getFirst();
            nave.getMotorWarp().ejecutarSaltoComun();
            if(nave.getCombustible()==0)
                throw new NaveDestruidaException("Nave destruida por no tener combustible al haber ejecutado salto comun con exito");
            tablero.moverNave(p);
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: Ejecutó salto Común con éxito.");
        }
        catch(FaltaCombustibleException e){
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: " + e.getMessage() + ".");
            throw new NaveDestruidaException("Nave destruida por falta de combustible para ejecutar salto comun");
        }
    }

    /**
     * pre-cond: nave != null
     * post-cond: El motor warp de la nave cambiará de estado a En Warp
     */
    public void solicitarEjecucionSaltoWarp() throws NaveDestruidaException{
        try {
            int combAnt=nave.getCombustible();
            Posicion p;
            int paso=nave.getMotorWarp().ejecutarSaltoWarp();

            if(nave.getEnergia()==0)
                throw new NaveDestruidaException("Nave destruida por no tener energia al haber ejecutado salto warp con exito");
            if(nave.getCombustible()==0)
                throw new NaveDestruidaException("Nave destruida por no tener combustible al haber ejecutado salto warp con exito");
            if(nave.getDesgaste()==100)
                throw new NaveDestruidaException("Nave destruida por tener desgaste 100 al haber ejecutado con exito salto warp");

            if (paso==1){
                 p = ruta.getFirst();
            }
            else{
                 p = ruta.get(1);
            }
            tablero.moverNave(p);
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: Ejecutó salto Warp con éxito.");
        }catch (FaltaCombustibleException e){
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: " + e.getMessage() + ".");
            throw new NaveDestruidaException("Nave destruida por falta de combustible para ejecutar saltoWarp");
        }
        catch (FaltaEnergiaException e){
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: " + e.getMessage() + ".");
            throw new NaveDestruidaException("Nave destruida por falta de energia para ejecutar saltoWarp");
        }
        catch (ExcesoDesgasteException e){
            bitacora.registrar("MOTOR", "[Nave: " + nave.getIdentidad() + "]: " + e.getMessage() + ".");
            throw new NaveDestruidaException("Nave destruida por exceso de desgaste para ejecutar saltoWarp");
        }
    }
    /**
     * pre-cond: nave != null
     */
    public void mostrarLiquidacionHaberes(){
        nave.mostrarLiquidacionTripulacion();
    }
    public List<Posicion> getRuta(){return ruta;}
    public void calcRuta(Posicion destino){
        this.ruta = this.radarLA.calcRuta(destino);
    }
    public Tablero getTablero() {
        return tablero;
    }
    public RadarLargoAlcance getRadarLA() { return radarLA; }
    public RadarCortoAlcance getRadarCA() {
        return radarCA;
    }
}