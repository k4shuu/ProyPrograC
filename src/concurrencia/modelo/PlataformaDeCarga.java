import java.util.ArrayList;

public class PlataformaDeCarga implements Observable {
    private ArrayList<Observador> observadores=new ArrayList<>();
    private ArrayList<Bateria> baterias;
    private int limite;

    public PlataformaDeCarga(int limite){
        this.limite=limite;
        this.baterias=new ArrayList<>();
    }
    public synchronized void Depositar(GeneradoraDeEnergia generador,Bateria bateria) throws InterruptedException {
        while(this.baterias.size()==this.limite){
            System.out.println("Generadora: "+generador.getNombre()+" esperando a que se libere espacio en la Plataforma.");
            this.notificarObservadores("Generadora: "+generador.getNombre()+" esperando a que se libere espacio en la Plataforma.");
            wait();
        }
        System.out.println("Generadora: "+generador.getNombre()+" deposito bateria");
        this.notificarObservadores("Generadora: "+generador.getNombre()+" deposito bateria");
        this.baterias.add(bateria);
        notifyAll();
    }
    public synchronized void Consumir(NaveConsumidora nave) throws InterruptedException {
        while(this.baterias.isEmpty()){
            System.out.println("Nave: "+nave.getNombre()+" esperando por falta de baterías en la Plataforma.");
            this.notificarObservadores("Nave: "+nave.getNombre()+" esperando por falta de baterías en la Plataforma.");
            wait();
        }
        System.out.println("Bateria retirada y utilizada por "+nave.getNombre());
        this.notificarObservadores("Bateria retirada y utilizada por "+nave.getNombre());
        this.baterias.removeFirst();
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
