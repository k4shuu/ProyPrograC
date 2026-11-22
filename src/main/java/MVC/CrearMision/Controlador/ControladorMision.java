package MVC.CrearMision.Controlador;

import MVC.ControlDeNave.Controlador.ControladorTablero;
import MVC.ControlDeNave.Vista.VistaPrincipal;
import MVC.CrearMision.Vista.IVistaGameOver;
import MVC.CrearMision.Vista.IVistaMision;
import MVC.CrearMision.Vista.VistaGameOver;
import MVC.RegistroLogin.Controlador.ControladorLogin;
import MVC.RegistroLogin.Vista.VistaLogin;
import asistente.AsistenteComando;
import concurrencia.controlador.ControladorConcurrencia;
import concurrencia.modelo.PlataformaDeCarga;
import concurrencia.vista.IVistaConcurrencia;
import concurrencia.vista.VistaConcurrencia;
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
        this.asistente = asistente;
        this.vistaMision.setControlador(this);
    }

    public void iniciar() {
        vistaMision.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (IVistaMision.INICIAR_MISION.equals(e.getActionCommand())) {
            transicionarAlTablero();
        }
    }

    private void transicionarAlTablero() {
        String codigo = vistaMision.getCodigoMisionSeleccionada();
        try {
            Mision misionActual = MisionFactory.crearMision(codigo, asistente);

            // Se crea el Tablero recién ahora
            VistaPrincipal vistaTablero = new VistaPrincipal(asistente);
            vistaTablero.mostrarMisionActual(codigo);

            PlataformaDeCarga plataforma = new PlataformaDeCarga(3);
            IVistaConcurrencia vistaConcurrencia = new VistaConcurrencia();

            ControladorConcurrencia cConcurrencia = new ControladorConcurrencia(vistaConcurrencia, plataforma, vistaTablero);
            ControladorTablero cTablero = new ControladorTablero(vistaTablero, vistaConcurrencia, cConcurrencia);

            ((JFrame) this.vistaMision).dispose();
            cTablero.iniciarMision(misionActual);

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