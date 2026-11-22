import MVC.RegistroLogin.Controlador.ControladorLogin;
import MVC.RegistroLogin.Vista.VistaLogin;

import javax.swing.SwingUtilities;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaLogin vistaLogin = new VistaLogin();
            Path rutaBD = Path.of("usuarios.db");
            new ControladorLogin(vistaLogin, rutaBD);
            vistaLogin.setVisible(true);
        });
    }
}