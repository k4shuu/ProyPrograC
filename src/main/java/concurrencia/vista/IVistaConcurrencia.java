package concurrencia.vista;
import observer.Observador;

import java.awt.event.ActionListener;

public interface IVistaConcurrencia extends Observador {
    String CREARNAVE="CREARNAVE";
    String CREARGENERADOR="CREARGENERADOR";
    String VOLVER="VOLVER";

    void setActionListener(ActionListener listener);
    void limpiarError();
    void setError(String mensaje);
    void registrarEvento(String mensaje);
    void setVisible(boolean b);

}
