package MVC.ControlDeNave;

import MVC.RegistroLogin.Controlador.ControladorLogin;
import MVC.RegistroLogin.Vista.VistaLogin;

import javax.swing.SwingUtilities;

public class MainPrueba {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaLogin vistaLogin = new VistaLogin();
            new ControladorLogin(vistaLogin);
            vistaLogin.setVisible(true);
        });
    }
}