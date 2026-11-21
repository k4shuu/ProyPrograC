package concurrencia.modelo;
import concurrencia.observer.Observador;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class NaveConsumidora implements Runnable{
    private String nombre;
    private PlataformaDeCarga plataforma;
    private ArrayList<Observador> observadores=new ArrayList<>();
    public NaveConsumidora(String nombre,PlataformaDeCarga plataforma){
        this.nombre=nombre;
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
