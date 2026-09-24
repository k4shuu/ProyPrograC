import asistente.AsistenteComando;
import bitacora.Bitacora;
import bitacora.EntradaBitacora;
import naves.Nave;
import tripulacion.*;
import naves.*;
import warp.*;

public class Main {
    public static void main(String[] args) {
        Tripulante capitan1=new Capitan("Juan",7);
        Tripulante vulcano1=new Vulcano(capitan1);
        Tripulante consejero2=new Consejero("Lucia",20);
        Tripulante marciano2=new Marciano(consejero2);
        Tripulante alferez3= new Alferez("Paula",1);
        Tripulante terricola3= new Terricola(alferez3);
        Tripulante teniente4=new Teniente("Pablo",3);
        Tripulante vulcano4=new Vulcano(teniente4);

        //BITÁCORA Y ASISTENTE PRINCIPAL
        Bitacora bitacora = new Bitacora();
        AsistenteComando asistente = new AsistenteComando(bitacora);
        
        System.out.println(vulcano1.getConceptoSueldo());
        System.out.println(marciano2.getConceptoSueldo());
        System.out.println(terricola3.getConceptoSueldo());
        System.out.println(vulcano4.getConceptoSueldo());

        Nave nave1= NaveFactory.crearNave("carguero","nave01",100,60,0, bitacora);
        Nave nave2= NaveFactory.crearNave("exploradora","nave45",60,80,0, bitacora);
        Nave nave3= NaveFactory.crearNave("Combate","nave939",80,100,0, bitacora);

        System.out.println(nave1.toString());
        System.out.println(nave2.toString());
        System.out.println(nave3.toString());
    }
}