package MVC.ControlDeNave.Controlador;

import MVC.ControlDeNave.Vista.IVista;
import MVC.ControlDeNave.Vista.VistaPrincipal;
import concurrencia.controlador.ControladorConcurrencia;
import concurrencia.modelo.PlataformaDeCarga;
import concurrencia.vista.VistaConcurrencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorTablero implements ActionListener {
    private VistaPrincipal vista;
    private ControladorConcurrencia concurrenciaActiva;

    public ControladorTablero(VistaPrincipal vista) {
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void mostrar() {
        this.vista.setVisible(true);
    }

    private void volverDeConcurrencia() {
        this.concurrenciaActiva = null;
        this.vista.setVisible(true);
    }

    public void cerrar() {
        if (this.concurrenciaActiva != null) {
            this.concurrenciaActiva.cerrar();
            this.concurrenciaActiva = null;
        }
        this.vista.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (IVista.IR_CONCURRENCIA.equals(e.getActionCommand())) {
            this.vista.setVisible(false);

            PlataformaDeCarga plataforma = new PlataformaDeCarga(3);
            VistaConcurrencia vistaConcurrencia = new VistaConcurrencia();
            this.concurrenciaActiva = new ControladorConcurrencia(
                    vistaConcurrencia, plataforma, this::volverDeConcurrencia
            );
            this.concurrenciaActiva.mostrar();
        }
    }
}