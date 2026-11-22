package MVC.CrearMision.Vista;

import misiones.InformeMision;

import java.awt.event.ActionListener;

public interface IVistaGameOver {
    String VOLVER_INICIO = "VOLVER_INICIO";

    void mostrarInforme(InformeMision informe);
    void setControlador(ActionListener c);
    void setVisible(boolean b);
    void dispose();
}
