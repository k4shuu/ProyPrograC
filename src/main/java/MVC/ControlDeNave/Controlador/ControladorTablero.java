package MVC.ControlDeNave.Controlador;

import MVC.ControlDeNave.Vista.IVista;
import MVC.ControlDeNave.Vista.VistaPrincipal;
import MVC.CrearMision.Controlador.EjecutorMision;
import MVC.CrearMision.Vista.IVistaGameOver;
import MVC.CrearMision.Vista.VistaGameOver;
import MVC.RegistroLogin.Controlador.ControladorLogin;
import MVC.RegistroLogin.Vista.VistaLogin;
import concurrencia.controlador.ControladorConcurrencia;
import concurrencia.vista.IVistaConcurrencia;
import misiones.InformeMision;
import misiones.Mision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class ControladorTablero implements ActionListener {
    private VistaPrincipal vista;
    private IVistaConcurrencia vistaConcurrencia;
    private ControladorConcurrencia controladorConcurrencia;

    public ControladorTablero(VistaPrincipal vista, IVistaConcurrencia vistaConcurrencia, ControladorConcurrencia controladorConcurrencia) {
        this.vista = vista;
        this.vistaConcurrencia = vistaConcurrencia;
        this.controladorConcurrencia = controladorConcurrencia;
        this.vista.setControlador(this);
    }

    public void iniciarMision(Mision mision) {
        this.vista.setVisible(true);
        new Thread(new EjecutorMision(mision, this)).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (IVista.IR_CONCURRENCIA.equals(e.getActionCommand())) {
            this.vista.setVisible(false);
            this.vistaConcurrencia.setVisible(true);
        }
    }

    public void transicionarAGameOver(InformeMision informe) {
        if (this.controladorConcurrencia != null) {
            this.controladorConcurrencia.cerrar();
        }
        this.vista.dispose();

        IVistaGameOver vistaGameOver = new VistaGameOver();
        vistaGameOver.mostrarInforme(informe);
        vistaGameOver.setControlador(e -> {
            if (IVistaGameOver.VOLVER_INICIO.equals(e.getActionCommand())) {
                vistaGameOver.dispose();
                VistaLogin vistaLogin = new VistaLogin();
                new ControladorLogin(vistaLogin, Path.of("usuarios.db"));
                vistaLogin.setVisible(true);
            }
        });
        vistaGameOver.setVisible(true);
    }
}