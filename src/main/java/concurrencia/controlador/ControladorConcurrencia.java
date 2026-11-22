package concurrencia.controlador;

import concurrencia.modelo.GeneradoraDeEnergia;
import concurrencia.modelo.NaveConsumidora;
import concurrencia.modelo.PlataformaDeCarga;
import concurrencia.vista.IVistaConcurrencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorConcurrencia implements ActionListener {
    private IVistaConcurrencia vista;
    private PlataformaDeCarga plataforma;
    private Runnable alVolver;
    private ArrayList<Thread> hilos = new ArrayList<>();
    private int numGenerador = 0;
    private int numNave = 0;

    public ControladorConcurrencia(IVistaConcurrencia vista, PlataformaDeCarga plataforma, Runnable alVolver) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.plataforma = plataforma;
        this.alVolver = alVolver;
        this.plataforma.agregarObservador(vista);
    }

    public void mostrar() {
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.vista.limpiarError();
        switch (e.getActionCommand()) {
            case IVistaConcurrencia.CREARNAVE:
                this.crearNave();
                break;
            case IVistaConcurrencia.CREARGENERADOR:
                this.crearGenerador();
                break;
            case IVistaConcurrencia.VOLVER:
                this.cerrar();
                if (this.alVolver != null) {
                    this.alVolver.run();
                }
                break;
        }
    }

    public void crearNave() {
        String nombre = "N" + (++this.numNave);
        NaveConsumidora nave = new NaveConsumidora(nombre, plataforma);
        this.vista.registrarEvento("¡Ingresó una nueva Nave: " + nave.getNombre() + " al universo paralelo!");
        Thread h = new Thread(nave);
        this.hilos.add(h);
        h.start();
    }

    public void crearGenerador() {
        String nombre = "G" + (++this.numGenerador);
        GeneradoraDeEnergia generador = new GeneradoraDeEnergia(nombre, plataforma);
        this.vista.registrarEvento("¡Ingresó un nuevo Generador: " + generador.getNombre() + " al universo paralelo!");
        Thread h = new Thread(generador);
        this.hilos.add(h);
        h.start();
    }

    private void detenerHilos() {
        for (Thread h : this.hilos) {
            h.interrupt();
        }
        this.hilos.clear();
    }
    public void cerrar() {
        this.detenerHilos();
        this.plataforma.quitarObservador(this.vista);
        this.vista.dispose();
    }
}