import Controlador.ControladorCreacion;
import Vista.IVistaLogin;
import Vista.VistaCreacionNaveTripulacion;
import Vista.VistaLogin;
import asistente.AsistenteComando;
import bitacora.Bitacora;
import destinoytablero.ElementoDelUniverso;
import destinoytablero.Tablero;
import excepciones.CantidadDeElementosSuperaCasillaException;
import excepciones.DimensionesDeTableroInconsistentesException;
import naves.Nave;
import naves.NaveFactory;
import tripulacion.CargoFactory;
import tripulacion.OrigenFactory;
import tripulacion.Tripulante;
import Controlador.ControladorLogin;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //LOGIN - VENTANA
        /*VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin controlador = new ControladorLogin(vistaLogin);
        vistaLogin.setVisible(true);
         */

        VistaConcurrencia vistaConcurrencia=new VistaConcurrencia();
        Controlador controlador=new Controlador(vistaConcurrencia, new PlataformaDeCarga(5));
        vistaConcurrencia.setVisible(true);
        /*
        VistaCreacionNaveTripulacion vista=new VistaCreacionNaveTripulacion();
        ControladorCreacion con=new ControladorCreacion(vista);
        vista.setVisible(true);

        Tripulante capitanVulcano1    = OrigenFactory.crear(CargoFactory.crear("Capitan","Julia",5,0),"Vulcano");
        Tripulante consejeroMarciano2 = OrigenFactory.crear(CargoFactory.crear("Consejero","Lucia",20,3),"Marciano");

        Tripulante alferezTerricola3  = OrigenFactory.crear(CargoFactory.crear("Alferez","Paula",1,0),"Terricola");
        Tripulante tenienteVulcano4   = OrigenFactory.crear(CargoFactory.crear("Teniente","Pablo",3,0),"Vulcano");
        Tripulante alferezTerricola5  = OrigenFactory.crear(CargoFactory.crear("Alferez","Julia",16,0),"Terricola");

        //BITÁCORA Y ASISTENTE PRINCIPAL
        Bitacora bitacora = new Bitacora();
        Nave nave1 = NaveFactory.crearNave("carguero","nave01",100,60,0);

        //INICIALIZACIÓN TABLERO
        Tablero tablero;
        ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();

        try {
            tablero = new Tablero(20, 20, elementos);
        } catch (DimensionesDeTableroInconsistentesException | CantidadDeElementosSuperaCasillaException e) {
            throw new RuntimeException(e);
        }

        AsistenteComando asistente = new AsistenteComando(bitacora, nave1, tablero);

        //CARGA DE TRIPULACIÓN
        nave1.agregarTripulante(capitanVulcano1);
        nave1.agregarTripulante(consejeroMarciano2);
        nave1.agregarTripulante(alferezTerricola3);
        nave1.agregarTripulante(tenienteVulcano4);

         */

        //TEST SALTOS
        //System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        //asistente.solicitarEjecucionSaltoComun();
        //System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        //asistente.solicitarEjecucionSaltoWarp();
        //System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        //asistente.solicitarEjecucionSaltoWarp();
        //System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        //asistente.solicitarEjecucionSaltoWarp();
        //System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        //asistente.solicitarEjecucionSaltoWarp();
        //System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        //asistente.solicitarEjecucionSaltoWarp();

        //bitacora.mostrarBitacora();



        //ESCENARIO B
        /*
        Bitacora bitacoraB=new Bitacora();

        //Combustible insuficiente
        Nave nave4=NaveFactory.crearNave("Combate","nave03",1,1,0);

        nave4.agregarTripulante(capitanVulcano1);
        nave4.agregarTripulante(consejeroMarciano2);
        nave4.agregarTripulante(alferezTerricola3);
        nave4.agregarTripulante(tenienteVulcano4);
        nave4.agregarTripulante(alferezTerricola5);

        System.out.println("Combustible antes de ejecutar la mision: "+nave4.getCombustible());
        Mision misionB=MisionFactory.crearMision("M01",new AsistenteComando(bitacoraB,nave4));
        try {
            misionB.comenzar();
        } catch (FaltaCombustibleException e) {
            System.out.println("Combustible luego de intentar ejecutar la mision: "+nave4.getCombustible());
            System.out.println("Ultima entrada en Bitacora: "+bitacoraB.getUltimaEntrada().toString());
            System.out.println(e.getMessage());
        } catch (ExcesoDesgasteException e) {
            System.out.println(e.getMessage());
        } catch (ExcesoEnergiaException e) {
            System.out.println(e.getMessage());
        }


        //Desgaste desmedido, no se podra ejecutar mision
        nave4.setDesgaste(97);
        nave4.setCombustible(10);//Ahora hay combustible suficiente

        System.out.println("Desgaste antes de ejecutar la mision: "+nave4.getDesgaste());
        misionB=MisionFactory.crearMision("M02",new AsistenteComando(bitacoraB,nave4));
        try {
            misionB.comenzar();
        } catch (FaltaCombustibleException e) {
            System.out.println(e.getMessage());
        } catch (ExcesoDesgasteException e) {
            System.out.println("Desgaste luego de intentar ejecutar la mision: "+nave4.getDesgaste());
            System.out.println("Ultima entrada en Bitacora: "+bitacoraB.getUltimaEntrada().toString());
            System.out.println(e.getMessage());
        } catch (ExcesoEnergiaException e) {
            System.out.println(e.getMessage());
        }

        //Energia desmedida, no se podra ejecutar mision
        nave4.setEnergia(97);
        nave4.setDesgaste(10);//Ahora hay menos desgaste

        System.out.println("Energia antes de ejecutar la mision: "+nave4.getEnergia());
        misionB=MisionFactory.crearMision("M01",new AsistenteComando(bitacoraB,nave4));
        try {
            misionB.comenzar();
        } catch (FaltaCombustibleException e) {
            System.out.println(e.getMessage());
        } catch (ExcesoDesgasteException e) {
            System.out.println(e.getMessage());
        } catch (ExcesoEnergiaException e) {
            System.out.println("Energia luego de intentar ejecutar la mision: "+nave4.getEnergia());
            System.out.println("Ultima entrada en Bitacora: "+bitacoraB.getUltimaEntrada().toString());
            System.out.println(e.getMessage());
        }

        */

        //ESCENARIO C

        //CAMINO FELIZ
        /*
        System.out.println("\nCAMINO FELIZ: ");
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual()); //DISPONIBLE

        asistente.solicitarPreparacionSalto(); //DISPONIBLE -> PREPARANDO SALTO
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        asistente.solicitarEjecucionSalto();  //PREPARANDO SALTO -> EN WARP
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        asistente.solicitarDesactivarWarp();  //EN WARP -> ENFRIAMIENTO (DISPONIBLE EN PRIMERA PARTE)
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        //TRANSICIÓN INVÁLIDA
        System.out.println("\nTRANSICIÓN INVÁLIDA: ");
        asistente.solicitarPreparacionSalto(); //PREPARANDO SALTO
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        asistente.solicitarFinalizarEnfriamiento(); //(TRANSICIÓN INVÁLIDA) PREPARANDO SALTO -> DISPONIBLE
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        asistente.solicitarDesactivarWarp(); //(TRANSICIÓN INVÁLIDA) PREPARANDO SALTO -> ENFRIAMIENTO
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        //BITÁCORA COMPLETA
        System.out.println("\nBITÁCORA COMPLETA: ");
        asistente.getBitacora().mostrarBitacora();

        */

        // ESCENARIO D - Contrato Invalido

        /*
        Nave naveD1=NaveFactory.crearNave("carguero","naveD1",100,60,0);
        naveD1.agregarTripulante(consejeroMarciano2);
        naveD1.agregarTripulante(alferezTerricola3);
        naveD1.agregarTripulante(tenienteVulcano4);

        System.out.println(naveD1.toString());
        try{
            naveD1.cargarEnerg(50);
        }
        catch(ExcesoEnergiaException e){
            System.out.println(e.getMessage());
        }
        try{
            naveD1.usarCombustible(120);
        }
        catch(FaltaCombustibleException e){
            System.out.println(e.getMessage());
        }
        try{
            naveD1.cargarComb(100);
        }
        catch(ExcesoCombException e){
            System.out.println(e.getMessage());
        }
        try{
            naveD1.realizarDesgaste(120);
        }
        catch(ExcesoDesgasteException e){
            System.out.println(e.getMessage());
        }
        System.out.print(naveD1.toString());
        */
    }
}