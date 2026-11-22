package MVC.ControlDeNave.Modelo;

import bitacora.Bitacora;
import naves.Nave;
import observer.Observable;

import java.util.List;

public class RecursosYBitacora extends Observable {
    private Nave nave;
    private Bitacora bitacora;
    private String estadoTablero;

    public RecursosYBitacora(Nave nave, Bitacora bitacora) {
        this.nave = nave;
        this.bitacora = bitacora;
        this.estadoTablero = "Sistemas en línea. Esperando órdenes.";
    }

    public void forzarActualizacionVista() {
        notificarObservadores();
    }

    public void setEstadoTablero(String estado) {
        this.estadoTablero = estado;
        notificarObservadores();
    }

    public int getEnergia() { return nave.getEnergia(); }
    public int getDesgaste() { return nave.getDesgaste(); }
    public int getCombustible() { return nave.getCombustible(); }
    public List<String> getRegistrosBitacora() { return bitacora.mostrarBitacora(); }
    public String getEstadoTablero() { return estadoTablero; }
}