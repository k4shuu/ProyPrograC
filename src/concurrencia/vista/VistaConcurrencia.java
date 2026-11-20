import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaConcurrencia extends JFrame implements IVistaPrincipal, Observador {

    private JButton btnCrearNave;
    private JButton btnCrearGeneradora;
    private JTextArea txtConsola;
    private JLabel lblError; // Componente para notificar errores

    public VistaConcurrencia() {
        // Configuración de la ventana
        this.setTitle("Visor del Universo Paralelo");
        this.setSize(750, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Panel principal
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contentPane.setBackground(new Color(240, 240, 240));
        this.setContentPane(contentPane);

        // Panel de Controles sin el campo de texto
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelControles.setBackground(new Color(240, 240, 240));

        btnCrearGeneradora = new JButton("Crear Generadora de Energía");
        btnCrearGeneradora.setForeground(Color.BLACK);

        btnCrearNave = new JButton("Crear Nave Consumidora");
        btnCrearNave.setForeground(Color.BLACK);

        panelControles.add(btnCrearGeneradora);
        panelControles.add(btnCrearNave);

        // Panel del Visor
        txtConsola = new JTextArea();
        txtConsola.setEditable(false);
        txtConsola.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtConsola.setBackground(Color.WHITE);
        txtConsola.setForeground(Color.BLACK);

        JScrollPane scrollConsola = new JScrollPane(txtConsola);
        scrollConsola.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Visor de Eventos - Plataforma de Carga",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                null,
                Color.BLACK
        );
        scrollConsola.setBorder(borde);

        // Panel inferior para mensajes de error
        lblError = new JLabel(" ");
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);

        // Ensamblaje final
        contentPane.add(panelControles, BorderLayout.NORTH);
        contentPane.add(scrollConsola, BorderLayout.CENTER);
        contentPane.add(lblError, BorderLayout.SOUTH);

        this.btnCrearGeneradora.setActionCommand(CREARGENERADOR);
        this.btnCrearNave.setActionCommand(CREARNAVE);
    }

    public JButton getBtnCrearNave() {
        return btnCrearNave;
    }

    public JButton getBtnCrearGeneradora() {
        return btnCrearGeneradora;
    }

    public void registrarEvento(String mensaje) {
        txtConsola.append(mensaje + "\n");
        txtConsola.setCaretPosition(txtConsola.getDocument().getLength());
    }

    public void setActionListener(ActionListener listener) {
        this.btnCrearNave.addActionListener(listener);
        this.btnCrearGeneradora.addActionListener(listener);
    }

    // Métodos para gestionar la visualización de errores
    public void setError(String mensaje) {
        lblError.setText(mensaje);
    }

    public void limpiarError() {
        lblError.setText(" ");
    }

    @Override
    public void actualizar(String mensaje) {
        // Ejecución obligatoria en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> this.registrarEvento(mensaje));
    }
}