import asistente.AsistenteComando;
import bitacora.Bitacora;
import excepciones.ExcesoDesgasteException;
import excepciones.ExcesoEnergiaException;
import excepciones.FaltaCombustibleException;
import misiones.*;
import naves.Nave;
import tripulacion.*;
import naves.*;

public class Main {
    public static void main(String[] args) {

        Tripulante capitanVulcano1 = new Vulcano(CargoFactory.crear("Capitan","Julia",5));
        Tripulante consejeroMarciano2 = new Marciano(new Consejero("Lucia",20));
        Tripulante alferezTerricola3 = new Terricola(new Alferez("Paula",1));
        Tripulante tenienteVulcano4 = new Vulcano(new Teniente("Pablo",3));
        Tripulante alferezTerricola5 = new Terricola(new Alferez("Julia",16));

        Tripulante nuevoTrip = OrigenFactory.crear(CargoFactory.crear("Capitan", "Pepito", 10), "Vulcano");


        System.out.println(nuevoTrip.getConceptoSueldo());

        //BITÁCORA Y ASISTENTE PRINCIPAL
        Bitacora bitacora = new Bitacora();
        /*
        System.out.println(capitanVulcano1.getConceptoSueldo());
        System.out.println(consejeroMarciano2.getConceptoSueldo());
        System.out.println(alferezTerricola3.getConceptoSueldo());
        System.out.println(tenienteVulcano4.getConceptoSueldo());

        Nave nave1= NaveFactory.crearNave("carguero","nave01",100,60,0);
        AsistenteComando asistente = new AsistenteComando(bitacora, nave1);

        System.out.println(nave1.toString());

        //CARGA DE TRIPULACIÓN
        nave1.agregarTripulante(capitanVulcano1);
        nave1.agregarTripulante(consejeroMarciano2);
        nave1.agregarTripulante(alferezTerricola3);
        nave1.agregarTripulante(tenienteVulcano4);

        asistente.mostrarLiquidacionHaberes(nave1);
        */

        //ESCENARIO A-Ejecucion correcta
        /*
        Nave nave1=NaveFactory.crearNave("carguero","nave01",100,60,0);
        Nave nave2=NaveFactory.crearNave("Exploradora","nave02",60,80,0);
        Nave nave3=NaveFactory.crearNave("Combate","nave03",80,100,0);

        nave1.agregarTripulante(capitanVulcano1);
        nave1.agregarTripulante(consejeroMarciano2);
        nave1.agregarTripulante(alferezTerricola3);
        nave1.agregarTripulante(tenienteVulcano4);
        nave1.agregarTripulante(alferezTerricola5);

        AsistenteComando asistente1=new AsistenteComando(bitacora,nave1);
        Mision mision1= MisionFactory.crearMision("m01",asistente1);
        try{
            System.out.println("Informe de mision: "+mision1.comenzar().toString());
        }
        catch(FaltaCombustibleException e){
            System.out.println(e.getMensaje());
        }
        catch(ExcesoDesgasteException e){
           System.out.println(e.getMensaje());
        }
        catch(ExcesoEnergiaException e){
            System.out.println(e.getMensaje());
        }

        Mision mision2= MisionFactory.crearMision("m02",asistente1);
        try{
            System.out.println("Informe de mision: "+mision2.comenzar().toString());
        }
        catch(FaltaCombustibleException e){
            System.out.println(e.getMensaje());
        }
        catch(ExcesoDesgasteException e){
            System.out.println(e.getMensaje());
        }
        catch(ExcesoEnergiaException e){
            System.out.println(e.getMensaje());
        }

        Mision mision3 = MisionFactory.crearMision("m03",asistente1);
        try{
            System.out.println("Informe de mision: "+mision3.comenzar().toString());
        }
        catch(FaltaCombustibleException e){
            System.out.println(e.getMensaje());
        }
        catch(ExcesoDesgasteException e){
            System.out.println(e.getMensaje());
        }
        catch(ExcesoEnergiaException e){
            System.out.println(e.getMensaje());
        }
        bitacora.mostrarBitacora();
         */

        //ESCENARIO B


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
            System.out.println(e.getMensaje());
        } catch (ExcesoDesgasteException e) {
            System.out.println(e.getMensaje());
        } catch (ExcesoEnergiaException e) {
            System.out.println(e.getMensaje());
        }


        //Desgaste desmedido, no se podra ejecutar mision
        nave4.setDesgaste(97);
        nave4.setCombustible(10);//Ahora hay combustible suficiente

        System.out.println("Desgaste antes de ejecutar la mision: "+nave4.getDesgaste());
        misionB=MisionFactory.crearMision("M02",new AsistenteComando(bitacoraB,nave4));
        try {
            misionB.comenzar();
        } catch (FaltaCombustibleException e) {
            System.out.println(e.getMensaje());
        } catch (ExcesoDesgasteException e) {
            System.out.println("Desgaste luego de intentar ejecutar la mision: "+nave4.getDesgaste());
            System.out.println("Ultima entrada en Bitacora: "+bitacoraB.getUltimaEntrada().toString());
            System.out.println(e.getMensaje());
        } catch (ExcesoEnergiaException e) {
            System.out.println(e.getMensaje());
        }

        //Energia desmedida, no se podra ejecutar mision
        nave4.setEnergia(97);
        nave4.setDesgaste(10);//Ahora hay menos desgaste

        System.out.println("Energia antes de ejecutar la mision: "+nave4.getEnergia());
        misionB=MisionFactory.crearMision("M01",new AsistenteComando(bitacoraB,nave4));
        try {
            misionB.comenzar();
        } catch (FaltaCombustibleException e) {
            System.out.println(e.getMensaje());
        } catch (ExcesoDesgasteException e) {
            System.out.println(e.getMensaje());
        } catch (ExcesoEnergiaException e) {
            System.out.println("Energia luego de intentar ejecutar la mision: "+nave4.getEnergia());
            System.out.println("Ultima entrada en Bitacora: "+bitacoraB.getUltimaEntrada().toString());
            System.out.println(e.getMensaje());
        }

        //ESCENARIO C
        /*
        //CAMINO FELIZ
        System.out.println("\nCAMINO FELIZ: ");
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual()); //DISPONIBLE

        asistente1.solicitarPreparacionSalto(nave1); //DISPONIBLE -> PREPARANDO SALTO
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        asistente1.solicitarEjecucionSalto(nave1);   //PREPARANDO SALTO -> EN WARP
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        asistente1.solicitarDesactivarWarp(nave1);   //EN WARP -> ENFRIAMIENTO (DISPONIBLE EN PRIMERA PARTE)
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        //TRANSICIÓN INVÁLIDA
        System.out.println("\nTRANSICIÓN INVÁLIDA: ");
        asistente1.solicitarPreparacionSalto(nave1);      //PREPARANDO SALTO
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        asistente1.solicitarDesactivarWarp(nave1);        //(TRANSICIÓN INVÁLIDA) PREPARANDO SALTO -> DISPONIBLE
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        asistente1.solicitarFinalizarEnfriamiento(nave1); //(TRANSICIÓN INVÁLIDA) PREPARANDO SALTO -> ENFRIAMIENTO
        System.out.println(nave1.getMotorWarp().getNombreEstadoActual());

        //BITÁCORA COMPLETA
        System.out.println("\nBITÁCORA COMPLETA: ");
        asistente1.getBitacora().mostrarBitacora();
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
            System.out.println(e.getMensaje());
        }
        try{
            naveD1.usarCombustible(120);
        }
        catch(FaltaCombustibleException e){
            System.out.println(e.getMensaje());
        }
        try{
            naveD1.realizarDesgaste(120);
        }
        catch(ExcesoDesgasteException e){
            System.out.println(e.getMensaje());
        }
        try{
            naveD1.usarCombustible(120);
        }
        catch(FaltaCombustibleException e){
            System.out.println(e.getMensaje());
        }
        System.out.print(naveD1.toString());
         */
    }
}