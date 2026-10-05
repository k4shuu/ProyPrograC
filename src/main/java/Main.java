import asistente.AsistenteComando;
import bitacora.Bitacora;
import naves.Nave;
import tripulacion.*;
import naves.*;

public class Main {
    public static void main(String[] args) {

        Tripulante capitanVulcano1 = new Vulcano(new Capitan("Juan",7));
        Tripulante consejeroMarciano2 = new Marciano(new Consejero("Lucia",20));
        Tripulante alferezTerricola3 = new Terricola(new Alferez("Paula",1));
        Tripulante tenienteVulcano4 = new Vulcano(new Teniente("Pablo",3));

        //BITÁCORA Y ASISTENTE PRINCIPAL
        Bitacora bitacora = new Bitacora();

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
    }
}