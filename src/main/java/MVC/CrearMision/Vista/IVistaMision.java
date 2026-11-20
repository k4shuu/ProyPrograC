package MVC.CrearMision.Vista;

import java.awt.event.ActionListener;

public interface IVistaMision {
    String INICIAR_MISION = "INICIAR_MISION"; // boton

    void setVisible(boolean visible);
    void setControlador(ActionListener c);
    String getCodigoMisionSeleccionada(); // eleccion del usuario
    void mostrarMensaje(String mensaje);
}