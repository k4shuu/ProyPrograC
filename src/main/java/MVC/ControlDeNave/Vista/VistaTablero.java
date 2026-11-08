package MVC.ControlDeNave.Vista;

import destinoytablero.ElementoDelUniverso;
import destinoytablero.Posicion;
import destinoytablero.Tablero;

import javax.swing.*;
import java.awt.*;

public class VistaTablero extends JPanel {
    private Tablero tablero;

    public VistaTablero(Tablero tablero) {
        this.tablero = tablero;
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tablero == null) return;

        int filas = tablero.getX();
        int columnas = tablero.getY();

        // Calcular el tamaño de cada "celda" según el tamaño actual del panel
        int anchoCelda = getWidth() / columnas;
        int altoCelda = getHeight() / filas;

        ElementoDelUniverso[][] matriz = tablero.verTablero();
        Posicion navePos = tablero.getPosicionNave();
        //Posicion objPos = tablero.getObjetivo();

        // Dibujar celdas y elementos
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int px = j * anchoCelda;
                int py = i * altoCelda;

                // Borde de la celda
                g.setColor(Color.DARK_GRAY);
                g.drawRect(px, py, anchoCelda, altoCelda);

                // Elementos del Universo (Rojo)
                if (matriz[i][j] != null) {
                    g.setColor(Color.RED);
                    g.fillOval(px + 2, py + 2, anchoCelda - 4, altoCelda - 4);
                }
            }
        }

        // Dibujar Objetivo (Verde)
       // if (objPos != null) {
         //   g.setColor(Color.GREEN);
           // int px = objPos.getY() * anchoCelda;
           // int py = objPos.getX() * altoCelda;
           // g.fillRect(px + 2, py + 2, anchoCelda - 4, altoCelda - 4);
       // }

        // Dibujar Nave (Cian)
        if (navePos != null) {
            g.setColor(Color.CYAN);
            int px = navePos.getY() * anchoCelda;
            int py = navePos.getX() * altoCelda;
            g.fillPolygon(
                    new int[]{px + anchoCelda / 2, px, px + anchoCelda},
                    new int[]{py, py + altoCelda, py + altoCelda},
                    3
            );
        }
    }
}
