import java.util.ArrayList;

public class PlataformaDeCarga extends Observable {
    private ArrayList<Observador> observadores=new ArrayList<>();
    private ArrayList<Bateria> baterias;
    private int limite;

    public PlataformaDeCarga(int limite){
        this.limite=limite;
        this.baterias=new ArrayList<>();
    }
    public synchronized void depositar(GeneradoraDeEnergia generador,Bateria bateria) throws InterruptedException {
        while(this.baterias.size()==this.limite){
            this.notificarObservadores("Generadora: "+generador.getNombre()+" esperando a que se libere espacio en la Plataforma.");
            wait();
        }
        this.notificarObservadores("Generadora: "+generador.getNombre()+" deposito bateria");
        this.baterias.add(bateria);
        notifyAll();
    }
    public synchronized void consumir(NaveConsumidora nave) throws InterruptedException {
        while(this.baterias.isEmpty()){
            this.notificarObservadores("Nave: "+nave.getNombre()+" esperando por falta de baterías en la Plataforma.");
            wait();
        }
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
