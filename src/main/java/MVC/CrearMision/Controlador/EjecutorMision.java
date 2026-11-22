package MVC.CrearMision.Controlador;

import misiones.Mision;
import misiones.InformeMision;
import MVC.CrearMision.Vista.VistaGameOver;

import javax.swing.*;

public class EjecutorMision implements Runnable {
    private Mision mision;
    private ControladorMision controlador;

    public EjecutorMision(Mision mision, ControladorMision controlador) {
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