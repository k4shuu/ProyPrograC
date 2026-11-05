public interface Observable {
    public void agregarObservador(Observador observador);
    public void quitarObservador(Observador observador);
    public void notificarObservadores(String mensaje);

}
