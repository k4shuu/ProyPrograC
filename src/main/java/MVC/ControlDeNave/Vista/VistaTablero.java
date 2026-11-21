package MVC.ControlDeNave.Vista;

import destinoytablero.ElementoDelUniverso;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import elementos.Asteroide;
import elementos.EstacionesDeCarga;
import naves.NaveCarguero;
import naves.NaveCombate;
import naves.NaveExploradora;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaTablero extends JPanel {
    private Tablero tablero;
    private List<Posicion> caminoNave = new ArrayList<>();

    // Almacenamiento de imágenes para no recargarlas en cada renderizado
    private Map<String, Image> imagenes = new HashMap<>();

    public VistaTablero(Tablero tablero) {
        this.tablero = tablero;
        setBackground(new Color(15, 15, 25)); // Espacio profundo oscuro
        cargarImagenes();
    }

    private void cargarImagenes() {
        String[] nombres = {
                "nave_jugador.png", "destino.png", "asteroide.png", "estacion.png",
                "nave_combate.png", "nave_carguero.png", "nave_exploradora.png"
        };

        for (String nombre : nombres) {
            URL url = getClass().getResource("/imagenes/" + nombre);
            if (url != null) {
                imagenes.put(nombre, new ImageIcon(url).getImage());
            } else {
                System.err.println("No se encontró la imagen: /imagenes/" + nombre);
            }
        }
    }

    public void setCamino(List<Posicion> camino) {
        this.caminoNave = (camino != null) ? new ArrayList<>(camino) : new ArrayList<>();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tablero == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int filas = tablero.getX();
        int columnas = tablero.getY();
        int anchoCelda = getWidth() / columnas;
        int altoCelda = getHeight() / filas;

        Posicion navePos = tablero.getPosicionNave();
        Posicion objPos = tablero.getObjetivo();
        ElementoDelUniverso[][] matriz = tablero.verTablero();

        // 1. Dibujar Grilla y Elementos del Universo
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int px = j * anchoCelda;
                int py = i * altoCelda;

                // Borde de celda espacial
                g2.setColor(new Color(40, 40, 60));
                g2.drawRect(px, py, anchoCelda, altoCelda);

                ElementoDelUniverso elem = matriz[i][j];
                if (elem != null) {
                    dibujarElemento(g2, elem, px, py, anchoCelda, altoCelda);
                }
            }
        }

        // 2. Dibujar Ruta pendiente (Caminos amarillos)
        if (caminoNave != null) {
            g2.setColor(new Color(255, 230, 0, 180)); // Amarillo semi-transparente
            for (Posicion p : caminoNave) {
                int px = p.getY() * anchoCelda;
                int py = p.getX() * altoCelda;
                g2.fillOval(px + anchoCelda / 3, py + altoCelda / 3, anchoCelda / 3, altoCelda / 3);
            }
        }

        // 3. Dibujar Destino (Green Portal)
        if (objPos != null) {
            int px = objPos.getY() * anchoCelda;
            int py = objPos.getX() * altoCelda;
            Image imgDestino = imagenes.get("destino.png");
            if (imgDestino != null) {
                g2.drawImage(imgDestino, px + 2, py + 2, anchoCelda - 4, altoCelda - 4, this);
            } else {
                // Fallback clásico en verde
                g2.setColor(new Color(0, 200, 80));
                g2.fillRect(px + 2, py + 2, anchoCelda - 4, altoCelda - 4);
            }
        }

        // 4. TU NAVE (Cian con borde de resalto)
        if (navePos != null) {
            int px = navePos.getY() * anchoCelda;
            int py = navePos.getX() * altoCelda;

            // Halo de selección para destacarla de todo lo demás
            g2.setColor(new Color(0, 255, 255, 60));
            g2.fillRect(px + 1, py + 1, anchoCelda - 2, altoCelda - 2);
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(px + 1, py + 1, anchoCelda - 2, altoCelda - 2);

            Image imgJugador = imagenes.get("nave_jugador.png");
            if (imgJugador != null) {
                g2.drawImage(imgJugador, px + 2, py + 2, anchoCelda - 4, altoCelda - 4, this);
            } else {
                // Fallback clásico en Cian
                int[] xs = {px + anchoCelda / 2, px + 3, px + anchoCelda - 3};
                int[] ys = {py + 3, py + altoCelda - 3, py + altoCelda - 3};
                g2.setColor(Color.CYAN);
                g2.fillPolygon(xs, ys, 3);
            }
            g2.setStroke(new BasicStroke(1)); // Restablece
        }
    }

    private void dibujarElemento(Graphics2D g2, ElementoDelUniverso elem, int px, int py, int w, int h) {
        String archivo = null;

        if (elem instanceof Asteroide) {
            archivo = "asteroide.png";
        } else if (elem instanceof EstacionesDeCarga) {
            archivo = "estacion.png";
        } else if (elem instanceof NaveCombate) {
            archivo = "nave_combate.png";
        } else if (elem instanceof NaveCarguero) {
            archivo = "nave_carguero.png";
        } else if (elem instanceof NaveExploradora) {
            archivo = "nave_exploradora.png";
        }

        Image img = (archivo != null) ? imagenes.get(archivo) : null;
        if (img != null) {
            g2.drawImage(img, px + 3, py + 3, w - 6, h - 6, this);
        } else {
            // Fallback en caso de que alguna imagen falle: usar los colores definidos antes
            g2.setColor(Color.RED);
            g2.fillOval(px + 4, py + 4, w - 8, h - 8);
        }
    }
}