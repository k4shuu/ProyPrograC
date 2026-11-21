package MVC.ControlDeNave.Vista;

import MVC.ControlDeNave.Vista.IObservador;

public interface IObservable {
    void agregarObservador(IObservador observador);
    void notificarObservadores();
}