package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame implements IVistaLogin {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;

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
    }

    @Override
    public String getUsuario() { return txtUsuario.getText(); }

    @Override
    public String getPassword() { return new String(txtPassword.getPassword()); }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
        txtUsuario.requestFocus();
    }

    @Override
    public void cerrarVentana() {
        this.dispose();
    }

    public void setLoginListener(ActionListener listener) {
        btnIniciarSesion.addActionListener(listener);
    }

    public void setRegistroListener(ActionListener listener) {
        btnRegistrarse.addActionListener(listener);
    }
}