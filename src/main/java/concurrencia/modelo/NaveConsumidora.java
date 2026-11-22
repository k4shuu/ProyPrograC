package concurrencia.modelo;

import java.util.concurrent.ThreadLocalRandom;

public class NaveConsumidora implements Runnable {
    private String nombre;
    private static int aux=0;
    private PlataformaDeCarga plataforma;



    /**
     * pre-cond: plataforma!=Null
     * post-cond: el atributo plataforma se le asigna el valor dado por parametro
     * @param plataforma
     */
    public NaveConsumidora(PlataformaDeCarga plataforma){
        aux++;
        this.nombre="N"+aux;
        this.plataforma=plataforma;

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
