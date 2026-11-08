package MVC.ControlDeNave.Modelo;

import MVC.ControlDeNave.Vista.IObservable;
import MVC.ControlDeNave.Vista.IObservador;
import naves.Nave;
import bitacora.Bitacora;

import java.util.ArrayList;
import java.util.List;

public class RecursosYBitacora implements IObservable {
    private List<IObservador> observadores;

    // Referencias a tus clases reales
    private Nave nave;
    private Bitacora bitacora;
    private String estadoTablero;

    // El constructor ahora exige la nave y la bitácora reales
    public RecursosYBitacora(Nave nave, Bitacora bitacora) {
        this.observadores = new ArrayList<>();
        this.nave = nave;
        this.bitacora = bitacora;
        this.estadoTablero = "Sistemas en línea. Esperando órdenes.";
    }

    @Override
    public void agregarObservador(IObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void notificarObservadores() {
        for (IObservador obs : observadores) {
            obs.actualizar();
        }
    }

    // Método clave: Lo llamarás desde cualquier parte de tu código
    // cuando la nave sufra daños, gaste recursos o se añada algo a la bitácora.
    public void forzarActualizacionVista() {
        notificarObservadores();
    }

    public void setEstadoTablero(String estado) {
        this.estadoTablero = estado;
        notificarObservadores();
    }

    // --- Getters que la Vista usará para leer tus objetos ---
    // Nota: Asegúrate de tener estos getters (o similares) creados en tu clase Nave
    public int getEnergia() { return nave.getEnergia(); }
    public int getDesgaste() { return nave.getDesgaste(); }
    public int getCombustible() { return nave.getCombustible(); }

    // Asumiendo que tu Bitacora tiene un método para obtener los registros
    public List<String> getRegistrosBitacora() { return bitacora.mostrarBitacora(); }

    public String getEstadoTablero() { return estadoTablero; }
}
