package Vista;

public interface IVistaLogin {
    String getUsuario();
    String getPassword();
    void mostrarMensaje(String mensaje);
    void limpiarCampos();
    void cerrarVentana();
}