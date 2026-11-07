import Controlador.ControladorLogin;
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

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //LOGIN - VENTANA
        /*VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin controlador = new ControladorLogin(vistaLogin);
        vistaLogin.setVisible(true);
         */

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

        //TEST SALTOS
        System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        asistente.solicitarEjecucionSaltoComun();
        System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        asistente.solicitarEjecucionSaltoWarp();
        System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        asistente.solicitarEjecucionSaltoWarp();
        System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        asistente.solicitarEjecucionSaltoWarp();
        System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        asistente.solicitarEjecucionSaltoWarp();
        System.out.println(nave1.getMotorWarp().getEstadoActual().getNombreEstado());
        asistente.solicitarEjecucionSaltoWarp();

        bitacora.mostrarBitacora();
    }
}