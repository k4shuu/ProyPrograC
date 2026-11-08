package MVC.ControlDeNave;

import MVC.ControlDeNave.Vista.VistaPrincipal;
import concurrencia.modelo.PlataformaDeCarga;
import concurrencia.observer.Observador;
import concurrencia.vista.VistaConcurrencia;
import destinoytablero.Destino;
import destinoytablero.ElementoDelUniverso;
import destinoytablero.Tablero;
import naves.Nave;
import bitacora.Bitacora;
import MVC.ControlDeNave.Controlador.ControladorTablero;
import naves.NaveFactory;

import java.util.ArrayList;

public class MainPrueba {

    public static void main(String[] args) {
        try {
            // 1. Crear la lista de elementos (asteroides, planetas, etc.)
            ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();

            Tablero tablero = new Tablero(20, 20, elementos);
            Destino destino = new Destino(tablero);
            Nave nave = NaveFactory.crearNave("carguero","nave01",100,60,0);
            Bitacora bitacora = new Bitacora();

            PlataformaDeCarga plataforma = new PlataformaDeCarga(3);

            VistaPrincipal vistaPrincipal = new VistaPrincipal(tablero, nave, bitacora);
            VistaConcurrencia vistaConcurrencia = new VistaConcurrencia();
            plataforma.agregarObservador((Observador) vistaConcurrencia);

            ControladorTablero ctrlTablero = new ControladorTablero(tablero, destino, vistaPrincipal, vistaConcurrencia);

            // El controlador de concurrencia necesita conocer la vista principal para volver a ella
            concurrencia.controlador.Controlador ctrlConcurrencia = new concurrencia.controlador.Controlador(vistaConcurrencia, plataforma, vistaPrincipal);

            // 4. Iniciar
            ctrlTablero.iniciar();

        } catch (Exception e) {
            // Captura las excepciones de dimensiones o cantidad de elementos del tablero
            System.err.println("Error al iniciar el tablero: " + e.getMessage());
            e.printStackTrace();
        }
    }
}