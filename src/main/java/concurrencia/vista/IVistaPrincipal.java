package concurrencia.vista;
import javax.swing.*;
import java.awt.event.ActionListener;

public interface IVistaPrincipal extends Observador {
    String CREARNAVE="CREARNAVE";
    String CREARGENERADOR="CREARGENERADOR";
    String VOLVER="VOLVER";

    void setActionListener(ActionListener listener);
    void limpiarError();
    void setError(String mensaje);
    void registrarEvento(String mensaje);

}
