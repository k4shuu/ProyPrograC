package MVC.CreacionNaveTripulacion.Vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface IVistaCreacion {
    String CREARNAVE="CREARNAVE";
    String CREARTRIPULANTE="CREARTRIPULANTE";
    String IRALCOMANDO="IR-AL-COMANDO";

    void setActionListener(ActionListener a);
    String getNombreNave();
    void setError(String mensaje);
    String getTipoNaveSeleccionada();
    String getNombreNuevoTripulante();
    String getAntiguedadNueva();
    String getOrigenNuevoTripulante();
    String getCargoNuevoTripulante();
    String getCantConsejos();
    void clearError();
    void limpiarFormularioTripulante();
    void agregarMensaje(String mensaje);
    void actualizarNomina(double total);
    void setVisible(boolean b);
    void dispose();
}
