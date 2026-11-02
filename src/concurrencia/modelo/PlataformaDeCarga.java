import java.util.ArrayList;

public class PlataformaDeCarga implements Observable {
    private ArrayList<Observador> observadores=new ArrayList<>();
    private Bateria bateria;
    public PlataformaDeCarga(){
        this.bateria=null;
    }
    public synchronized void Depositar(GeneradoraDeEnergia generador,Bateria bateria) throws InterruptedException {
        while(this.bateria!=null){
            System.out.println("Generadora: "+generador.getNombre()+" esperando a que se libere espacio en la Plataforma.");
            this.notificarObservadores("Generadora: "+generador.getNombre()+" esperando a que se libere espacio en la Plataforma.");
            wait();
        }
        System.out.println("Generadora: "+generador.getNombre()+" deposito bateria");
        this.notificarObservadores("Generadora: "+generador.getNombre()+" deposito bateria");
        this.bateria=bateria;
        notifyAll();
    }
    public synchronized void Consumir(NaveConsumidora nave) throws InterruptedException {
        while(this.bateria==null){
            System.out.println("Nave: "+nave.getNombre()+" esperando por falta de baterías en la Plataforma.");
            this.notificarObservadores("Nave: "+nave.getNombre()+" esperando por falta de baterías en la Plataforma.");
            wait();
        }
        System.out.println("Bateria retirada y utilizada por "+nave.getNombre());
        this.notificarObservadores("Bateria retirada y utilizada por "+nave.getNombre());
        this.bateria=null;
        notifyAll();
    }

    @Override
    public void agregarObservador(Observador observador) {
        this.observadores.add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
        this.observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for(Observador o :observadores)
            o.actualizar(mensaje);
    }
}
