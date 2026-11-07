package misiones;

import asistente.AsistenteComando;
import destinoytablero.Destino;
import destinoytablero.ElementoDelUniverso;
import destinoytablero.Posicion;
import excepciones.ExcesoDesgasteException;
import excepciones.FaltaCombustibleException;
import excepciones.ExcesoEnergiaException;

public class MisionIntercep extends Mision {

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
    }

    @Override
    public void preparar() throws ExcesoDesgasteException, FaltaCombustibleException, ExcesoEnergiaException{
        if(this.asistente.getNave().tieneCombustible(combConsumido))
            if(this.asistente.getNave().sePuedeDesgastar(desgaste)){
                    this.asistente.solicitarEjecucionSaltoWarp();
            }
            else{
                this.asistente.escribirBitacora("M-01","ERROR: el desgaste de la mision excede lo permitido para la nave ");
                throw new ExcesoDesgasteException("ERROR: el desgaste de la mision excede lo permitido para la nave ",this.asistente.getNave().getDesgaste(),desgaste);

            }
        else {
                this.asistente.escribirBitacora("M-01","ERROR: La nave no tiene el combustible necesario para ejecutar la mision");
                throw new FaltaCombustibleException("ERROR: La nave no tiene el combustible necesario para ejecutar la mision",combConsumido,this.asistente.getNave().getCombustible());
            }
        this.estado = Estado.PREPARADA;
        this.ruta = this.asistente.calcRuta(this.obj);
    }

    @Override
    public void ejecutar() throws ExcesoEnergiaException,FaltaCombustibleException, ExcesoDesgasteException {
        this.asistente.getNave().usarCombustible(combConsumido);
        this.asistente.getNave().realizarDesgaste(desgaste);
        this.asistente.getNave().cargarEnerg(energCargada);
        this.asistente.solicitarEjecucionSaltoWarp();
        this.estado = Estado.EJECUTADA;

        boolean naveViva=true;
        boolean alcanzoDestino=false;
        // 2. Ciclo de travesía
        while (naveViva && !alcanzoDestino) {
            // --- A. Turno de la Nave ---

            // A.1. Inspección de posición actual utilizando el Tablero
            ElementoDelUniverso elemento = this.asistente.getTablero().inspeccionarPosicion();
            if (elemento != null) {
                elemento.interactuar(this.getNave());
                naveViva = asistente.estaVivaNave();
            }

            //metodo de asistente que valide esta nave es asistente.estaVivaNave que es boolean
            // Validación inmediata de destrucción post-interacción
            if (getNave().getDesgaste() >= 100 || getNave().getEnergia() <= 0 || getNave().getCombustible() <= 0) {
                this.result=Resultado.FALLIDA;
                //seter boolean
                //deberiamos lanzar excepcion??
                return;
            }
            else {
                // A.2. Escaneo
                elemento = this.asistente.getRadarCA().analizarPasoSig(this.ruta);


                //Cuando se solicitaria el saltoWarp?? y como hago que dure varios turnos
                // A.3. Planificación y movimiento
                if(elemento==null){
                    try{
                        asistente.solicitarSalto(ruta.getFirst());
                        ruta.removeFirst();
                    }catch(FaltaCombustibleException ignored){
                    }
                    //nave consume 2 de combustible
                }
                else {
                    this.asistente.solicitarSaltoWarp();//no lanza excepcion, salto simple

                }

                // --- B. Turno del Tablero ---
                Destino.getInstance().turnoDestino();

                // Nota técnica: Pausa en cada iteración
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                }
            }
        }

        // 3. Verificación en destino
        // Solo se llega aquí si el bucle terminó porque alcanzó el destino y sigue viva.
        if (misionExigeRecursosAdicionales()) {
            if (!nave.conservaRecursosExigidos()) {
                fallarMision("Fallo: La nave no conservó los recursos exigidos para la entrega.");
            } else {
                completarMisionExitosamente();
            }
        } else {
            completarMisionExitosamente();
        }
    }
    ;
    @Override
    public void evaluar() {
        System.out.println("Evaluando resultados");
        this.estado = Estado.EVALUADA;
        this.result = Resultado.EXITOSA;
    }

    @Override
    public InformeMision cerrar() {
        this.asistente.escribirBitacora("Mision-01", "Misión finalizada, para mas informacion consultar el informe correspondiente");
        Estado lastState = this.estado;
        this.estado = Estado.CERRADA;
        return new InformeMision("M01",4, 5,4, result, lastState);
    }
}
