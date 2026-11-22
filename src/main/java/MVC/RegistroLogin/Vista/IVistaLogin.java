package Vista;

import java.awt.event.ActionListener;

public interface IVistaLogin {
    String getUsuario();
    String getPassword();
    void mostrarMensaje(String mensaje);
    void limpiarCampos();
    void cerrarVentana();
    void dispose();
    void setLoginListener(ActionListener listener);
    void setRegistroListener(ActionListener listener);
}