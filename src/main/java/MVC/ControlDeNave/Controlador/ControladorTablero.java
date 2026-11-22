package MVC.ControlDeNave.Controlador;

import MVC.ControlDeNave.Vista.IVista;
import MVC.ControlDeNave.Vista.VistaPrincipal;
import concurrencia.vista.IVistaConcurrencia;
import concurrencia.vista.IVistaConcurrencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorTablero implements ActionListener {
    private VistaPrincipal vista;
    private IVistaConcurrencia vistaConcurrencia;

    public ControladorTablero(VistaPrincipal vista, IVistaConcurrencia vistaConcurrencia) {
        this.vista = vista;
        this.vistaConcurrencia = vistaConcurrencia;
        this.vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (IVista.IR_CONCURRENCIA.equals(comando)) {
            vista.setVisible(false);
            ((javax.swing.JFrame) vistaConcurrencia).setVisible(true);
        }
    }
}