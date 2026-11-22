package MVC.CrearMision.Controlador;

import MVC.ControlDeNave.Vista.VistaPrincipal;
import MVC.CrearMision.Vista.IVistaMision;
import MVC.CrearMision.Vista.VistaGameOver;
import MVC.RegistroLogin.Controlador.ControladorLogin;
import MVC.RegistroLogin.Vista.VistaLogin;
import asistente.AsistenteComando;
import misiones.InformeMision;
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
        this.vistaGameOver.setControlador(this);
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
            transicionarAlTablero();
        } else if (VistaGameOver.VOLVER_INICIO.equals(comando)) {
            transicionarAlLogin();
        }
    }

    private void transicionarAlTablero() {
        String codigo = vistaMision.getCodigoMisionSeleccionada();

        try {
            Mision misionActual = MisionFactory.crearMision(codigo, asistente);

            // Actualizamos el panel de "Misiones" en la VistaPrincipal
            vistaTablero.mostrarMisionActual(codigo);

            // Se hace visible el tablero y se destruye la ventana de selección de misión
            vistaTablero.setVisible(true);
            ((JFrame) this.vistaMision).dispose();

            // Se lanza el hilo de la misión
            EjecutorMision ejecutor = new EjecutorMision(misionActual, this);
            new Thread(ejecutor).start();

        } catch (Exception ex) {
            vistaMision.mostrarMensaje("Error al iniciar misión: " + ex.getMessage());
        }
    }

    public void transicionarAGameOver(InformeMision informe) {
        // Se carga el informe, se muestra Game Over y se destruye el tablero
        vistaGameOver.mostrarInforme(informe);
        vistaGameOver.setVisible(true);
        vistaTablero.dispose();
    }

    private void transicionarAlLogin() {
        VistaLogin vistaLogin = new VistaLogin();
        Path rutaBD = Path.of("usuarios.db");
        new ControladorLogin(vistaLogin, rutaBD);
        vistaLogin.setVisible(true);
        // 4. Se destruye la ventana de Game Over
        if (this.vistaGameOver != null) {
            this.vistaGameOver.dispose();
        }
    }
}