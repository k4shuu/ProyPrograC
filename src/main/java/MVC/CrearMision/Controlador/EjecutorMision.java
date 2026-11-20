package MVC.CrearMision.Controlador;

import misiones.Mision;
import misiones.InformeMision;
import MVC.CrearMision.Vista.VistaGameOver;
import javax.swing.JFrame;

public class EjecutorMision implements Runnable {
    private Mision mision;
    private JFrame vistaTablero;
    private VistaGameOver vistaGameOver;

    public EjecutorMision(Mision mision, JFrame vistaTablero, VistaGameOver vistaGameOver) {
        this.mision = mision;
        this.vistaTablero = vistaTablero;
        this.vistaGameOver = vistaGameOver;
    }

    @Override
    public void run() {

        InformeMision informe = mision.comenzar();
        vistaTablero.setVisible(false);
        vistaGameOver.mostrarInforme(informe);
        vistaGameOver.setVisible(true);
    }
}