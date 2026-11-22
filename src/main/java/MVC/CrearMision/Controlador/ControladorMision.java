package MVC.CrearMision.Controlador;

import MVC.ControlDeNave.Controlador.ControladorTablero;
import MVC.ControlDeNave.Vista.VistaPrincipal;
import MVC.CrearMision.Vista.IVistaGameOver;
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
    private AsistenteComando asistente;
    private ControladorTablero controladorTablero; // Solo referencia al controlador, no a la vista

    public ControladorMision(IVistaMision vistaMision, AsistenteComando asistente) {
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
        if (IVistaMision.INICIAR_MISION.equals(e.getActionCommand())) {
            transicionarAlTablero();
        } else if (VistaGameOver.VOLVER_INICIO.equals(comando)) {
            transicionarAlLogin();
        }
    }

    private void transicionarAlTablero() {
        String codigo = vistaMision.getCodigoMisionSeleccionada();

        try {
            Mision misionActual = MisionFactory.crearMision(codigo, asistente);

            // Se crea el Tablero recién ahora
            VistaPrincipal vistaTablero = new VistaPrincipal(asistente);

            // Actualizamos el panel de "Misiones" en la VistaPrincipal
            vistaTablero.mostrarMisionActual(codigo);
            this.controladorTablero = new ControladorTablero(vistaTablero);
            this.controladorTablero.mostrar();

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
        if (this.controladorTablero != null) {
            this.controladorTablero.cerrar();
        }

        // Se crea Game Over recién ahora
        IVistaGameOver vistaGameOver = new VistaGameOver();
        vistaGameOver.mostrarInforme(informe);
        vistaGameOver.setControlador(e -> {
            if (IVistaGameOver.VOLVER_INICIO.equals(e.getActionCommand())) {
                vistaGameOver.dispose();
                transicionarAlLogin();
            }
        });
        vistaGameOver.setVisible(true);
    }

    private void transicionarAlLogin() {
        VistaLogin vistaLogin = new VistaLogin();
        new ControladorLogin(vistaLogin, Path.of("usuarios.db"));
        vistaLogin.setVisible(true);
    }
}