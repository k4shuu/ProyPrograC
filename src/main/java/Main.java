import asistente.AsistenteComando;
import bitacora.Bitacora;
import excepciones.ExcesoDesgasteException;
import excepciones.ExcesoEnergiaException;
import excepciones.FaltaCombustibleException;
import misiones.Mision;
import naves.Nave;
import tripulacion.*;
import naves.*;

public class Main {
    public static void main(String[] args) {

        Tripulante capitanVulcano1 = new Vulcano(new Capitan("Juan",7));
        Tripulante consejeroMarciano2 = new Marciano(new Consejero("Lucia",20));
        Tripulante alferezTerricola3 = new Terricola(new Alferez("Paula",1));
        Tripulante tenienteVulcano4 = new Vulcano(new Teniente("Pablo",3));
        Tripulante alferezTerricola5=new Terricola(new Alferez("Julia",16));

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

        asistente.mostrarLiquidacionHaberes(nave1);*/

        //ESCENARIO A-Ejecucion correcta
        Nave nave1=NaveFactory.crearNave("carguero","nave01",100,60,0);
        Nave nave2=NaveFactory.crearNave("Exploradora","nave02",60,80,0);
        Nave nave3=NaveFactory.crearNave("Combate","nave03",80,100,0);

        nave1.agregarTripulante(capitanVulcano1);
        nave1.agregarTripulante(consejeroMarciano2);
        nave1.agregarTripulante(alferezTerricola3);
        nave1.agregarTripulante(tenienteVulcano4);
        nave1.agregarTripulante(alferezTerricola5);

        AsistenteComando asistente1=new AsistenteComando(bitacora,nave1);
        Mision mision1= Mision.crearMision("m01",asistente1);
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

        Mision mision2= Mision.crearMision("m02",asistente1);
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

        Mision mision3= Mision.crearMision("m03",asistente1);
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







    }
}