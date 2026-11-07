package MVC.RegistroLogin.Controlador;

import MVC.RegistroLogin.Vista.IVistaLogin;
import MVC.RegistroLogin.Vista.VistaLogin;
import persistencia.UsuarioDAO;
import persistencia.UsuarioDTO;
import excepciones.CredencialesInvalidasException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin {
    private IVistaLogin vista;
    private UsuarioDAO usuarioDAO;

    public ControladorLogin(IVistaLogin vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();

        if (vista instanceof VistaLogin) {
            VistaLogin v = (VistaLogin) vista;

            v.setLoginListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    intentarLogin();
                }
            });

            v.setRegistroListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    intentarRegistro();
                }
            });
        }
    }

    private void intentarLogin() {
        String user = vista.getUsuario();
        String pass = vista.getPassword();

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }

        try {
            UsuarioDTO usuarioLogueado = usuarioDAO.validarCredenciales(user, pass);
            vista.mostrarMensaje("¡Acceso concedido! Bienvenido, " + usuarioLogueado.getUsername());
            vista.cerrarVentana();

            //ACÁ IRÁ EL CÓDIGO UNA VEZ AUTENTICADO EL USUARIO! - INICIO
            Vista.VistaCreacionNaveTripulacion vistaCreacion = new Vista.VistaCreacionNaveTripulacion();
            vistaCreacion.setVisible(true);
            //FIN

        } catch (CredencialesInvalidasException e) {
            vista.mostrarMensaje("Error de autenticación: " + e.getMessage());
        }
    }

    private void intentarRegistro() {
        String user = vista.getUsuario();
        String pass = vista.getPassword();

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarMensaje("Por favor, complete los campos para registrarse.");
            return;
        }

        UsuarioDTO nuevoUsuario = new UsuarioDTO(user, pass);
        boolean registrado = usuarioDAO.registrarUsuario(nuevoUsuario);

        if (registrado) {
            vista.mostrarMensaje("¡Usuario registrado con éxito! Ya puede iniciar sesión.");
            vista.limpiarCampos();
        } else
            vista.mostrarMensaje("El nombre de usuario ya existe en la base de datos.");
    }
}