package concurrencia.modelo;

import java.util.ArrayList;
import observer.Observable;

public class PlataformaDeCarga extends Observable {
    private ArrayList<Bateria> baterias;
    private int limite;

    public PlataformaDeCarga(int limite) {
        this.limite = limite;
        this.baterias = new ArrayList<>();
    }

    public synchronized void depositar(GeneradoraDeEnergia generador, Bateria bateria) throws InterruptedException {
        while (this.baterias.size() == this.limite) {
            this.notificarObservadores("Generadora: " + generador.getNombre() + " esperando a que se libere espacio en la Plataforma.");
            wait();
        }
        this.notificarObservadores("Generadora: " + generador.getNombre() + " deposito bateria");
        this.baterias.add(bateria);
        notifyAll();
    }

    public synchronized void consumir(NaveConsumidora nave) throws InterruptedException {
        while (this.baterias.isEmpty()) {
            this.notificarObservadores("Nave: " + nave.getNombre() + " esperando por falta de baterías en la Plataforma.");
            wait();
        }
        this.notificarObservadores("Bateria retirada y utilizada por " + nave.getNombre());
        this.baterias.removeFirst();
        notifyAll();
    }
}