package MVC.ControlDeNave.Controlador;

import MVC.ControlDeNave.Vista.IVista;
import MVC.ControlDeNave.Vista.VistaPrincipal;
import destinoytablero.Destino;
import destinoytablero.Tablero;
import concurrencia.vista.IVistaPrincipal;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorTablero implements ActionListener{
    private Tablero tablero;
    private Destino destino;
    private VistaPrincipal vista;
    private Timer timerTurnos;
    private IVistaPrincipal vistaConcurrencia;

    public ControladorTablero(Tablero tablero, Destino destino, VistaPrincipal vista, IVistaPrincipal vistaConcurrencia) {
        this.tablero = tablero;
        this.destino = destino;
        this.vista = vista;
        this.vistaConcurrencia = vistaConcurrencia;
        this.vista.setControlador(this);
        iniciarMotorDeJuego();
    }

    private void iniciarMotorDeJuego() {
        timerTurnos = new Timer(1500, this);
    }

    public void iniciar() {
        vista.setVisible(true);
        timerTurnos.start(); // Inicia el movimiento automático
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        // 1. Si presionaron el botón para ir a concurrencia
        if (IVista.IR_CONCURRENCIA.equals(comando)) {
            ((javax.swing.JFrame) vista).setVisible(false);
            ((javax.swing.JFrame) vistaConcurrencia).setVisible(true);
        }
        // 2. Si el evento lo disparó el Timer (el timer no tiene ActionCommand)
        else if (e.getSource() == timerTurnos) {
            destino.turnoDestino();
            // Lógica adicional de tu turno...
        }
    }
}