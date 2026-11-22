package concurrencia.controlador;

import MVC.ControlDeNave.Vista.IVista;
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
    private IVista vistaPrincipal;
    private ArrayList<Thread> hilos = new ArrayList<>();
    private int numGenerador = 0;
    private int numNave = 0;

    public ControladorConcurrencia(IVistaConcurrencia vista, PlataformaDeCarga plataforma, IVista vistaPrincipal) {
        this.vista = vista;
        this.plataforma = plataforma;
        this.vistaPrincipal = vistaPrincipal;
        this.vista.setActionListener(this);
        this.plataforma.agregarObservador(vista);
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
                this.vista.setVisible(false);
                this.vistaPrincipal.setVisible(true);
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

    public void cerrar() {
        for (Thread h : this.hilos) {
            h.interrupt();
        }
        this.hilos.clear();
        this.plataforma.quitarObservador(this.vista);
        this.vista.dispose();
    }
}