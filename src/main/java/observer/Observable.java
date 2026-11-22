package observer;

import java.util.ArrayList;

public abstract class Observable {
    private final ArrayList<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    // Para casos como Tablero (sin payload)
    public void notificarObservadores() {
        notificarObservadores(null);
    }

    // Para casos como PlataformaDeCarga (con mensaje o dato)
    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(mensaje);
        }
    }
}