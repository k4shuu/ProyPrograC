package MVC.CrearMision.Controlador;

import MVC.ControlDeNave.Controlador.ControladorTablero;
import misiones.Mision;
import misiones.InformeMision;

import javax.swing.*;

public class EjecutorMision implements Runnable {
    private Mision mision;
    private ControladorTablero controlador;

    public EjecutorMision(Mision mision, ControladorTablero controlador) {
        this.mision = mision;
        this.controlador = controlador;
    }

    @Override
    public void run() {
        InformeMision informe = mision.comenzar();

        SwingUtilities.invokeLater(() -> {
            controlador.transicionarAGameOver(informe);
        });
    }
}