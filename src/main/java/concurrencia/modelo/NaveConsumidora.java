package concurrencia.modelo;

import java.util.concurrent.ThreadLocalRandom;

public class NaveConsumidora implements Runnable {
    private String nombre;
    private PlataformaDeCarga plataforma;

    public NaveConsumidora(String nombre, PlataformaDeCarga plataforma) {
        this.nombre = nombre;
        this.plataforma = plataforma;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void run() {
        try {
            int tiempoAleatorio = ThreadLocalRandom.current().nextInt(2000, 4001);
            Thread.sleep(tiempoAleatorio);
            this.plataforma.consumir(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}