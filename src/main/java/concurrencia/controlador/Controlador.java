package concurrencia.controlador;

import concurrencia.modelo.GeneradoraDeEnergia;
import concurrencia.modelo.NaveConsumidora;
import concurrencia.modelo.PlataformaDeCarga;
import concurrencia.vista.IVistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador implements ActionListener {
    private IVistaPrincipal vista;
    private ArrayList<GeneradoraDeEnergia> generadores=new ArrayList<>();
    private ArrayList<NaveConsumidora> naves=new ArrayList<>();
    private PlataformaDeCarga plataforma;
    private int numGenerador=0;
    private int numNave=0;
    private JFrame vistaPrincipal;

    public Controlador(IVistaPrincipal vista,PlataformaDeCarga plataforma, JFrame vistaPrincipal){
        this.vista=vista;
        this.vista.setActionListener(this);
        this.plataforma=plataforma;
        this.vistaPrincipal=vistaPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.vista.limpiarError();
        switch (e.getActionCommand()){
            case IVistaPrincipal.CREARNAVE: this.crearNave();
            break;
            case IVistaPrincipal.CREARGENERADOR: this.crearGenerador();
            break;
            case IVistaPrincipal.VOLVER: // <-- NUEVO
                ((JFrame) this.vista).setVisible(false); // Oculta concurrencia
                this.vistaPrincipal.setVisible(true);      // Muestra el tablero principal
                break;
        }
    }
    public void crearNave(){
        //Las naves y los generadores deben ser  observable?? me genera problemas para pasarles vista que es IVISTA y no Observador aca
        String nombre="N"+(++this.numNave);
        NaveConsumidora nave=new NaveConsumidora(nombre,plataforma);
        this.naves.add(nave);
        this.vista.registrarEvento("¡Ingresó una nueva Nave: "+nave.getNombre()+" al universo paralelo!");
        Thread h=new Thread(nave);
        h.start();
    }
    public void crearGenerador(){
        String nombre="G"+(++this.numGenerador);
        //Las naves y los generadores deben ser  observable?? me genera problemas para pasarles vista que es IVISTA y no Observador aca
        GeneradoraDeEnergia generador=new GeneradoraDeEnergia(nombre,plataforma);
        this.generadores.add(generador);
        this.vista.registrarEvento("¡Ingresó un nuevo Generador: "+generador.getNombre()+" al universo paralelo!");
        Thread h=new Thread(generador);
        h.start();
    }
}
