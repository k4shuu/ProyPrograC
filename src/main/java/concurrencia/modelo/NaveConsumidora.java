package concurrencia.modelo;
import observer.Observador;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class NaveConsumidora implements Runnable{
    private String nombre;
    private static int aux=0;
    private PlataformaDeCarga plataforma;
    private ArrayList<Observador> observadores=new ArrayList<>();

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

    public String getNombre(){
        return  this.nombre;
    }
    @Override
    public void run() {
        try{
            // Genera un tiempo de espera aleatorio entre 2000 ms (1 segundo) y 4000 ms (3 segundos)
            int tiempoAleatorio = ThreadLocalRandom.current().nextInt(2000, 4001);
            Thread.sleep(tiempoAleatorio);
            this.plataforma.consumir(this);
        }
        catch(InterruptedException e){

        }
    }

}
