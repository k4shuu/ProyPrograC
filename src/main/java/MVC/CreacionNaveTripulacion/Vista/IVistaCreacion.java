package Vista;

import observer.Observador;

import java.awt.event.ActionListener;

public interface IVistaCreacion extends Observador {
    String CREARNAVE="CREARNAVE";
    String CREARTRIPULANTE="CREARTRIPULANTE";
    String IRALCOMANDO="IRALCOMANDO";

    void setActionListener(ActionListener a);
    String getNombreNave();
    void setError(String mensaje);
    String getTipoNaveSeleccionada();
    String getNombreNuevoTripulante();
    String getAntiguedadNueva();
    String getOrigenNuevoTripulante();
    String getCargoNuevoTripulante();
    public String getCantConsejos();
    void clearError();
}
