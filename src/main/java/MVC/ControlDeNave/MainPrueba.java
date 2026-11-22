
package MVC.ControlDeNave;
import MVC.ControlDeNave.Controlador.ControladorTablero;
import MVC.ControlDeNave.Vista.VistaPrincipal;
import MVC.CrearMision.Controlador.ControladorMision;
import MVC.CrearMision.Vista.VistaGameOver;
import MVC.CrearMision.Vista.VistaMision;
import asistente.AsistenteComando;
import bitacora.Bitacora;
import concurrencia.controlador.ControladorConcurrencia;
import concurrencia.modelo.PlataformaDeCarga;
import concurrencia.observer.Observador;
import concurrencia.vista.VistaConcurrencia;
import destinoytablero.Destino;
import destinoytablero.ElementoDelUniverso;
import destinoytablero.Tablero;
import elementos.Asteroide;

import elementos.EstacionesDeCarga;
import naves.*;

import java.util.ArrayList;


public class MainPrueba {

        public static void main(String[] args) {
            try {
                // 1. Modelos base
                ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
                // Asteroides
                for (int i = 0; i < 6; i++) {
                    elementos.add(new Asteroide());
                }

                // Estaciones de carga
                for (int i = 0; i < 3; i++) {
                    elementos.add(new EstacionesDeCarga());
                }

                // Naves enemigas (respetando las precondiciones de Nave: valores > 0 y <= 100)
                elementos.add(new NaveCombate("Enemigo-C1", 100, 100, 10));
                elementos.add(new NaveCarguero("Enemigo-G1", 100, 100, 10));
                elementos.add(new NaveExploradora("Enemigo-E1", 100, 100, 10));

                Tablero tablero = new Tablero(20, 20, elementos, null);
                Destino.init(tablero);
                Nave nave = NaveFactory.crearNave("carguero", "nave01", 100, 60, 0);
                Bitacora bitacora = new Bitacora();
                PlataformaDeCarga plataforma = new PlataformaDeCarga(3);

                // Ajusta el orden/cantidad de parámetros según tu constructor real de AsistenteComando
                AsistenteComando asistente = new AsistenteComando(bitacora,nave,tablero);

                // 2. Vistas
                VistaPrincipal vistaPrincipal = new VistaPrincipal(asistente, tablero, nave, bitacora);
                VistaConcurrencia vistaConcurrencia = new VistaConcurrencia();
                VistaMision vistaMision = new VistaMision();
                VistaGameOver vistaGameOver = new VistaGameOver();

                plataforma.agregarObservador((Observador) vistaConcurrencia);

                // 3. Controladores
                ControladorTablero ctrlTablero = new ControladorTablero(vistaPrincipal, vistaConcurrencia);
                ControladorConcurrencia ctrlConcurrencia = new ControladorConcurrencia(vistaConcurrencia, plataforma, vistaPrincipal);
                ControladorMision ctrlMision = new ControladorMision(vistaMision, vistaPrincipal, vistaGameOver, asistente);

                // 4. Iniciar desde el selector de misiones
                ctrlMision.iniciar();

            } catch (Exception e) {
                System.err.println("Error al iniciar la aplicación: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }