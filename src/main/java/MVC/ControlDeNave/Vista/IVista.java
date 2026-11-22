package MVC.ControlDeNave.Vista;

import java.awt.event.ActionListener;

public interface IVista {
    String IR_CONCURRENCIA = "IR_CONCURRENCIA";
    // Permite mostrar u ocultar la ventana
    void setVisible(boolean visible);

    // Permite al controlador suscribirse a los eventos de la vista
    void setControlador(ActionListener c);
    void dispose();
}