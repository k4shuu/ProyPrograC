import java.util.concurrent.ThreadLocalRandom;

public class GeneradoraDeEnergia implements Runnable{
    private String nombre;
    private PlataformaDeCarga plataforma;

    public GeneradoraDeEnergia(String nombre,PlataformaDeCarga plataforma){
        this.nombre=nombre;
        this.plataforma=plataforma;
    }
    public String getNombre(){
        return this.nombre;
    }
    public Bateria crearBateria(){
        System.out.println("Batería producida por "+this.nombre);
        return new Bateria();
    }
    @Override
    public void run() {
        try{
            Bateria bateria=this.crearBateria();
            // Genera un tiempo de espera aleatorio entre 1000 ms (1 segundo) y 3000 ms (3 segundos)
            int tiempoAleatorio = ThreadLocalRandom.current().nextInt(1000, 3001);
            Thread.sleep(tiempoAleatorio);
            this.plataforma.Depositar(this,bateria);
        }
        catch (InterruptedException e){

        }
    }
}
