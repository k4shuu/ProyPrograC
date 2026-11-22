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

    public ControladorLogin(IVistaLogin vista, Path archivo) {
        this.vista = vista;
        this.servicio = new Servicio(new UsuarioDAOSQL(archivo));

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

    private void intentarLogin() {
        String user = vista.getUsuario();
        String pass = vista.getPassword();

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarMensaje("Por favor, complete todos los campos.");
            return;
        }

        try {
            Usuario usuarioLogueado = servicio.validarCredenciales(user, pass);
            vista.mostrarMensaje("¡Acceso concedido! Bienvenido, " + usuarioLogueado.getUsername());
            vista.cerrarVentana();

            //ACÁ IRÁ EL CÓDIGO UNA VEZ AUTENTICADO EL USUARIO! - INICIO

            IVistaCreacion vistaCreacion = new VistaCreacionNaveTripulacion();

            vistaCreacion.setVisible(true);
            ControladorCreacion controlador=new ControladorCreacion(vistaCreacion);
            this.vista.dispose();
            //FIN

        } catch (CredencialesInvalidasException e) {
            vista.mostrarMensaje("Error de autenticación: " + e.getMessage());
        }
        catch(PersistenciaException e){
            vista.mostrarMensaje("Error de persistencia: " + e.getMessage());
        }
    }

    private void intentarRegistro() {
        String user = vista.getUsuario();
        String pass = vista.getPassword();

        if (user.isEmpty() || pass.isEmpty()) {
            vista.mostrarMensaje("Por favor, complete los campos para registrarse.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(user, pass);

        try {
            Usuario u=servicio.registrarUsuario(nuevoUsuario);
            vista.mostrarMensaje("¡Usuario registrado con éxito! Ya puede iniciar sesión.");
            vista.limpiarCampos();
        } catch (UsuarioYaExisteException e) {
            vista.mostrarMensaje("El nombre de usuario ya existe en la base de datos.");
        } catch (PersistenciaException e) {
            vista.mostrarMensaje(e.getMessage());
        }


    }
}