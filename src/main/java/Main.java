import concurrencia.controlador.ControladorConcurrencia;
import concurrencia.modelo.PlataformaDeCarga;
import concurrencia.vista.VistaConcurrencia;
import misiones.InformeMision;
import misiones.Mision;
import misiones.MisionFactory;

public class Main {
    public static void main(String[] args) {

        //LOGIN - VENTANA
        MVC.RegistroLogin.Vista.VistaLogin vistaLogin = new MVC.RegistroLogin.Vista.VistaLogin();
        MVC.RegistroLogin.Controlador.ControladorLogin controlador = new MVC.RegistroLogin.Controlador.ControladorLogin(vistaLogin);
        vistaLogin.setVisible(true);



        //INICIALIZACIÓN TABLERO
        //Tablero tablero;
        //ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();

        //try {

        // } catch (DimensionesDeTableroInconsistentesException | CantidadDeElementosSuperaCasillaException e) {
        //     throw new RuntimeException(e);
        //  }
        //tablero = new Tablero(20, 20, elementos);
        //Destino.init(tablero);



    }
}