package concurrencia.modelo;

import java.util.ArrayList;
import observer.Observable;

public class PlataformaDeCarga extends Observable {
    private ArrayList<Bateria> baterias;
    private int limite;


    /**
     * pre-cond: limite>0
     * post-cond: el atributo limite se le asigna el valor dado por parametro
     * @param limite
     */
    public PlataformaDeCarga(int limite) {
        assert limite>0: "El limite debe ser un valor positivo";
        this.limite = limite;
        this.baterias = new ArrayList<>();
    }

    /**
     * pre-cond:generador!=null
     * pre-cond: bateria!=null
     * post-cond: se deposito una bateria, por lo que baterias aumenta en 1
     * @param generador
     * @param bateria
     * @throws InterruptedException
     */
    public synchronized void depositar(GeneradoraDeEnergia generador, Bateria bateria) throws InterruptedException {
        assert generador!=null:"El generador debe ser distinto de null";
        assert bateria!=null: "La bateria debe ser distinta de null";
        while (this.baterias.size() == this.limite) {
            this.notificarObservadores("Generadora: " + generador.getNombre() + " esperando a que se libere espacio en la Plataforma.");
            wait();
        }
        this.notificarObservadores("Generadora: " + generador.getNombre() + " deposito bateria");
        this.baterias.add(bateria);
        notifyAll();
    }

    /**
     * pre-cond: nave!=null
     * post-cond: se consume una bateria por lo que baterias decrementa en 1
     * @param nave
     * @throws InterruptedException
     */
    public synchronized void consumir(NaveConsumidora nave) throws InterruptedException {
        assert nave!=null:"La nave debe ser distinta de null";
        while (this.baterias.isEmpty()) {
            this.notificarObservadores("Nave: " + nave.getNombre() + " esperando por falta de baterías en la Plataforma.");
            wait();
        }
        this.notificarObservadores("Bateria retirada y utilizada por " + nave.getNombre());
        this.baterias.removeFirst();
        notifyAll();
    }
}