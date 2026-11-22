package MVC.RegistroLogin.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame implements IVistaLogin {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;

    /**
     - pre-cond: ninguna
     - post-cond: la ventana de login queda configurada, centrada y con sus componentes visuales inicializados
     */
    public VistaLogin() {
        this.setTitle("Acceso al Sistema - Hangar Central");
        this.setSize(400, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnRegistrarse = new JButton("Registrarse");

        panel.add(btnIniciarSesion);
        panel.add(btnRegistrarse);

        this.setContentPane(panel);

        assert txtUsuario != null && txtPassword != null : "Los campos de entrada no deben ser nulos";
        assert btnIniciarSesion != null && btnRegistrarse != null : "Los botones de acción no deben ser nulos";
    }

    /**
     - pre-cond: txtUsuario != null
     - post-cond: retorna el texto ingresado en el campo de usuario sin modificar la vista
     - @returns el nombre de usuario ingresado
     */
    @Override
    public String getUsuario() {
        assert txtUsuario != null : "El campo txtUsuario no puede ser nulo";
        return txtUsuario.getText();
    }

    /**
     - pre-cond: txtPassword != null
     - post-cond: retorna la contraseña ingresada como String sin modificar la vista
     - @returns la contraseña ingresada
     */
    @Override
    public String getPassword() {
        assert txtPassword != null : "El campo txtPassword no puede ser nulo";
        return new String(txtPassword.getPassword());
    }

    /**
     - pre-cond: mensaje != null
     - post-cond: muestra un cuadro de diálogo con el mensaje recibido
     - @param mensaje texto a mostrar en el diálogo
     */
    @Override
    public void mostrarMensaje(String mensaje) {
        assert mensaje != null : "El mensaje a mostrar no puede ser nulo";
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     - pre-cond: txtUsuario != null && txtPassword != null
     - post-cond: vacía los campos de usuario y contraseña y devuelve el foco a txtUsuario
     */
    @Override
    public void limpiarCampos() {
        assert txtUsuario != null && txtPassword != null : "Los campos no pueden ser nulos al limpiar";
        txtUsuario.setText("");
        txtPassword.setText("");
        txtUsuario.requestFocus();
    }

    /**
     - pre-cond: ninguna
     - post-cond: libera los recursos gráficos y cierra la ventana actual
     */
    @Override
    public void cerrarVentana() {
        this.dispose();
    }

    /**
     - pre-cond: btnIniciarSesion != null && listener != null
     - post-cond: el listener queda registrado en btnIniciarSesion
     - @param listener acción a ejecutar al pulsar iniciar sesión
     */
    public void setLoginListener(ActionListener listener) {
        assert btnIniciarSesion != null : "btnIniciarSesion no puede ser nulo";
        assert listener != null : "El listener de login no puede ser nulo";
        btnIniciarSesion.addActionListener(listener);
    }

    /**
     - pre-cond: btnRegistrarse != null && listener != null
     - post-cond: el listener queda registrado en btnRegistrarse
     - @param listener acción a ejecutar al pulsar registrarse
     */
    public void setRegistroListener(ActionListener listener) {
        assert btnRegistrarse != null : "btnRegistrarse no puede ser nulo";
        assert listener != null : "El listener de registro no puede ser nulo";
        btnRegistrarse.addActionListener(listener);
    }
}