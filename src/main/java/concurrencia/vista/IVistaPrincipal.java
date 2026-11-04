import javax.swing.*;
import java.awt.event.ActionListener;

public interface IVistaPrincipal {
    String CREARNAVE="CREARNAVE";
    String CREARGENERADOR="CREARGENERADOR";


    public void setActionListener(ActionListener listener);
    public void limpiarError();
    public void setError(String mensaje);
    public void registrarEvento(String mensaje);
}
