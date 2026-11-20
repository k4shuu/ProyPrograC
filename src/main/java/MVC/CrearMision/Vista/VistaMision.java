package MVC.CrearMision.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaMision extends JFrame implements IVistaMision {
    private JComboBox<String> comboMisiones;
    private JButton btnComenzar;

    public VistaMision() {
        setTitle("Selección de Misión");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Título
        JLabel lblTitulo = new JLabel("Seleccione la misión a ejecutar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTitulo);

        // Desplegable con los códigos
        String[] opciones = {"M01 - Intercepción", "M02 - Recolección", "M03 - Retorno"};
        comboMisiones = new JComboBox<>(opciones);
        JPanel panelCombo = new JPanel();
        panelCombo.add(comboMisiones);
        add(panelCombo);

        // Botón de inicio
        btnComenzar = new JButton("Comenzar Misión");
        btnComenzar.setActionCommand(INICIAR_MISION); // Asignamos el comando
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnComenzar);
        add(panelBoton);
    }

    @Override
    public String getCodigoMisionSeleccionada() {
        String seleccion = (String) comboMisiones.getSelectedItem();
        return seleccion.substring(0, 3);
    }

    @Override
    public void setControlador(ActionListener c) {
        btnComenzar.addActionListener(c);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
