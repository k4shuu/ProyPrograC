package MVC.RegistroLogin.Controlador;

import MVC.CreacionNaveTripulacion.Vista.VistaCreacionNaveTripulacion;
import MVC.RegistroLogin.Vista.IVistaLogin;
import MVC.CreacionNaveTripulacion.Controlador.ControladorCreacion;
import MVC.CreacionNaveTripulacion.Vista.IVistaCreacion;

import MVC.RegistroLogin.Vista.VistaLogin;
import excepciones.PersistenciaException;
import excepciones.UsuarioYaExisteException;
import persistencia.*;
import excepciones.CredencialesInvalidasException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class ControladorLogin {
    private IVistaLogin vista;
    private Servicio servicio;

    /**
     - pre-cond: vista!=null y archivo!=null
     - post-cond: el controlador queda inicializado con su servicio de persistencia y los listeners registrados en la vista
     - @param vista
     - @param archivo
     */
    public ControladorLogin(IVistaLogin vista, Path archivo) {
        assert vista != null : "La vista no puede ser nula";
        assert archivo != null : "El archivo no puede ser nulo";

        this.vista = vista;
        this.servicio = new Servicio(new UsuarioDAOSQL(archivo));

        assert this.servicio != null : "El servicio no puede ser nulo";

        if (vista instanceof VistaLogin v) {
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

    /**
     - pre-cond: vista!=null y servicio!=null
     - post-cond: si las credenciales son válidas, se cierra la vista de login y se abre la vista de creación; si fallan, se muestra el mensaje correspondiente
     */
    private void intentarLogin() {
        assert vista != null : "La vista no puede ser nula";
        assert servicio != null : "El servicio no puede ser nulo";

        String user = vista.getUsuario();
        String pass = vista.getPassword();

        assert user != null && pass != null : "Usuario y contraseña no pueden ser nulos";

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }

        try {
            Usuario usuarioLogueado = servicio.validarCredenciales(user, pass);
            assert usuarioLogueado != null : "El usuario autenticado no puede ser nulo";

            vista.mostrarMensaje("¡Acceso concedido! Bienvenido, " + usuarioLogueado.getUsername());
            vista.cerrarVentana();

            //ACÁ IRÁ EL CÓDIGO UNA VEZ AUTENTICADO EL USUARIO! - INICIO

            IVistaCreacion vistaCreacion = new VistaCreacionNaveTripulacion();

            vistaCreacion.setVisible(true);
            ControladorCreacion controlador = new ControladorCreacion(vistaCreacion);
            //FIN

        } catch (CredencialesInvalidasException e) {
            vista.mostrarMensaje("Error de autenticación: " + e.getMessage());
        } catch (PersistenciaException e) {
            vista.mostrarMensaje("Error de persistencia: " + e.getMessage());
        }
    }

    /**
     - pre-cond: vista!=null y servicio!=null
     - post-cond: si el usuario no existe, queda registrado en la base de datos y se limpian los campos; si ya existe o hay error, se informa en la vista
     */
    private void intentarRegistro() {
        assert vista != null : "La vista no puede ser nula";
        assert servicio != null : "El servicio no puede ser nulo";

        String user = vista.getUsuario();
        String pass = vista.getPassword();

        assert user != null && pass != null : "Usuario y contraseña no pueden ser nulos";

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarMensaje("Por favor, complete los campos para registrarse.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(user, pass);

        try {
            Usuario u = servicio.registrarUsuario(nuevoUsuario);
            assert u != null : "El usuario registrado no puede ser nulo";

            vista.mostrarMensaje("¡Usuario registrado con éxito! Ya puede iniciar sesión.");
            vista.limpiarCampos();
        } catch (UsuarioYaExisteException e) {
            vista.mostrarMensaje("El nombre de usuario ya existe en la base de datos.");
        } catch (PersistenciaException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }
}