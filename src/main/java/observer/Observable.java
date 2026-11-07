package observer;

import observer.Observador;

import java.util.ArrayList;

public class Observable {
    /** Observadores que reciben las actualizaciones de este objeto. */
    private ArrayList<Observador> observadores = new ArrayList<Observador>();

    /**
     * Registra un observador para recibir las actualizaciones.
     * Un mismo observador no se registra más de una vez.
     * <p><b>Precondición:</b> {@code observador} no es {@code null}.</p>
     * @param observador observador por registrar
     */
    public void agregarObservador(Observador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    /**
     * Quita un observador registrado. Si no estaba registrado, no hace nada.
     * @param observador observador por quitar
     */
    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    /**
     * Notifica a los observadores registrados que este objeto se actualizó.
     */
    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(this,mensaje);
        }
    }
}
