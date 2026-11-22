package MVC.CrearMision.Controlador;

import MVC.ControlDeNave.Vista.VistaPrincipal;
import MVC.CrearMision.Vista.IVistaMision;
import MVC.CrearMision.Vista.VistaGameOver;
import MVC.RegistroLogin.Controlador.ControladorLogin;
import MVC.RegistroLogin.Vista.VistaLogin;
import asistente.AsistenteComando;
import misiones.Mision;
import misiones.MisionFactory;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class ControladorMision implements ActionListener {
    private IVistaMision vistaMision;
    private VistaPrincipal vistaTablero;
    private VistaGameOver vistaGameOver;
    private AsistenteComando asistente;

    public ControladorMision(IVistaMision vistaMision, VistaPrincipal vistaTablero, VistaGameOver vistaGameOver, AsistenteComando asistente) {
        this.vistaMision = vistaMision;
        this.vistaTablero = vistaTablero;
        this.vistaGameOver = vistaGameOver;
        this.asistente = asistente;

        this.vistaMision.setControlador(this);
        this.vistaGameOver.setControlador(this); // Escucha el botón de Game Over
    }

    public void iniciar() {
        vistaMision.setVisible(true);
        vistaTablero.setVisible(false);
        vistaGameOver.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (IVistaMision.INICIAR_MISION.equals(comando)) {
            iniciarMisionLogica();
        } else if (VistaGameOver.VOLVER_INICIO.equals(comando)) {
            volverAlLogin();
        }
    }

    private void iniciarMisionLogica() {
        String codigo = vistaMision.getCodigoMisionSeleccionada();

        try {
            Mision misionActual = MisionFactory.crearMision(codigo, asistente);

            // Actualizamos el panel de "Misiones" en la VistaPrincipal
            vistaTablero.mostrarMisionActual(codigo);

            vistaMision.setVisible(false);
            vistaTablero.setVisible(true);

            EjecutorMision ejecutor = new EjecutorMision(misionActual, vistaTablero, vistaGameOver);
            Thread hilo = new Thread(ejecutor);
            hilo.start();

        } catch (Exception ex) {
            vistaMision.mostrarMensaje("Error al iniciar misión: " + ex.getMessage());
        }
    }

    private void volverAlLogin() {
        vistaGameOver.dispose();
        vistaTablero.dispose();

        VistaLogin vistaLogin = new VistaLogin();
        new ControladorLogin(vistaLogin, Path.of("usuarios.db"));
        vistaLogin.setVisible(true);
    }
}