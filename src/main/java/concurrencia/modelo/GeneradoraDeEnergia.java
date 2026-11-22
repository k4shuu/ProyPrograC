package concurrencia.modelo;

import java.util.concurrent.ThreadLocalRandom;

public class GeneradoraDeEnergia implements Runnable{
    private String nombre;
    private PlataformaDeCarga plataforma;
    private static int aux=0;

    /**
     * pre-cond: plataforma!=null
     * post-cond: atributo plataforma se le asigna el valor dado por parametro
     * @param plataforma
     */
    public GeneradoraDeEnergia(PlataformaDeCarga plataforma){
        assert plataforma!=null:"La plataforma debe ser distinta ded null";
        aux++;
        this.nombre="G"+aux;
        this.plataforma=plataforma;
    }
    public String getNombre(){
        return this.nombre;
    }
    public Bateria crearBateria(){
        return new Bateria();
    }
    @Override
    public void run() {
        try{
            Bateria bateria=this.crearBateria();
            // Genera un tiempo de espera aleatorio entre 1000 ms (1 segundo) y 3000 ms (3 segundos)
            int tiempoAleatorio = ThreadLocalRandom.current().nextInt(1000, 3001);
            Thread.sleep(tiempoAleatorio);
            this.plataforma.depositar(this,bateria);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
