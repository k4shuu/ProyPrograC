package MVC.CrearMision.Vista;

import misiones.InformeMision;
import misiones.Resultado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaGameOver extends JFrame {
    public static final String VOLVER_INICIO = "VOLVER_INICIO";

    private JLabel lblTitulo;
    private JTextArea txtInforme;
    private JButton btnVolverInicio;

    public VistaGameOver() {
        setTitle("Fin de la Misión");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitulo = new JLabel("GAME OVER", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.RED);
        add(lblTitulo, BorderLayout.NORTH);

        txtInforme = new JTextArea();
        txtInforme.setEditable(false);
        txtInforme.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtInforme.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(txtInforme), BorderLayout.CENTER);

        // Botón para volver al Login
        btnVolverInicio = new JButton("Volver al Inicio");
        btnVolverInicio.setActionCommand(VOLVER_INICIO);
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolverInicio);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public void mostrarInforme(InformeMision informe) {
        if (informe != null) {
            if (informe.getResult() == Resultado.EXITOSA) {
                lblTitulo.setText("¡MISIÓN EXITOSA!");
                lblTitulo.setForeground(new Color(0, 150, 0));
            } else {
                lblTitulo.setText("GAME OVER");
                lblTitulo.setForeground(Color.RED);
            }
            txtInforme.setText(informe.toString());
        } else {
            txtInforme.setText("La misión falló antes de generar un informe.");
        }
    }

    public void setControlador(ActionListener c) {
        btnVolverInicio.addActionListener(c);
    }
}
