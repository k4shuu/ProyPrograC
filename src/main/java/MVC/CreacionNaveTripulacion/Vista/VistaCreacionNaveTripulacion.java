package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCreacionNaveTripulacion extends JFrame implements IVistaCreacion {

    // --- Componentes de la Columna Izquierda (Creación) ---
    private JTextField txtNombreNave;
    private JComboBox<String> comboTipoNave;
    private JButton btnInstanciarNave;

    private JTextField txtNombreTripulante;
    private JComboBox<String> comboOrigenTripulante;
    private JTextField txtAntiguedad;
    private JComboBox<String> comboCargoTripulante;

    // Componentes dinámicos para el Consejero
    private JLabel lblCantConsejos;
    private JTextField txtCantConsejos;

    private JButton btnAgregarTripulante;

    // --- Componentes de la Columna Derecha (Mensajes) ---
    private JTextArea txtAreaMensajes;
    private JLabel lblTotalNomina;
    private JLabel lblMensajeError;

    private JButton btnIrAlComando;

    public VistaCreacionNaveTripulacion() {
        this.setTitle("Hangar Central - Preparación de Misión");
        this.setSize(1000, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("🚀 ENSAMBLAJE Y RECLUTAMIENTO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentral.add(crearPanelIzquierdo());
        panelCentral.add(crearPanelDerecho());

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        this.setContentPane(panelPrincipal);
    }

    private JPanel crearPanelIzquierdo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("[2] CONFIGURACIÓN DE NAVE"));

        panel.add(new JLabel("Nombre de la Nave:"));
        txtNombreNave = new JTextField();
        txtNombreNave.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(txtNombreNave);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Tipo de Nave:"));
        comboTipoNave = new JComboBox<>(new String[]{"Exploradora", "Carguero", "Combate"});
        panel.add(comboTipoNave);
        panel.add(Box.createVerticalStrut(10));

        btnInstanciarNave = new JButton("INSTANCIAR NAVE");
        btnInstanciarNave.setActionCommand(CREARNAVE);
        panel.add(btnInstanciarNave);

        panel.add(Box.createVerticalStrut(20));
        panel.add(new JSeparator());
        panel.add(Box.createVerticalStrut(20));

        JLabel lblReclutamiento = new JLabel("RECLUTAMIENTO");
        lblReclutamiento.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblReclutamiento);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Nombre del Tripulante:"));
        txtNombreTripulante = new JTextField();
        txtNombreTripulante.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(txtNombreTripulante);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Planeta/Ciudad de Origen:"));
        comboOrigenTripulante = new JComboBox<>(new String[]{"Vulcano", "Terricola", "Marciano"});
        panel.add(comboOrigenTripulante);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Antigüedad (Años):"));
        txtAntiguedad = new JTextField();
        txtAntiguedad.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(txtAntiguedad);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Cargo asignado:"));
        comboCargoTripulante = new JComboBox<>(new String[]{"Capitan", "Consejero", "Alferez", "Teniente"});
        panel.add(comboCargoTripulante);

        // Campos dinámicos para el Consejero
        lblCantConsejos = new JLabel("Cantidad de Consejos:");
        txtCantConsejos = new JTextField();
        txtCantConsejos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Inicialmente ocultos
        lblCantConsejos.setVisible(false);
        txtCantConsejos.setVisible(false);

        panel.add(lblCantConsejos);
        panel.add(txtCantConsejos);
        panel.add(Box.createVerticalStrut(15));

        // Lógica visual: Mostrar el campo solo si elige "Consejero"
        comboCargoTripulante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean esConsejero = "Consejero".equals(comboCargoTripulante.getSelectedItem());
                lblCantConsejos.setVisible(esConsejero);
                txtCantConsejos.setVisible(esConsejero);

                VistaCreacionNaveTripulacion.this.revalidate();
                VistaCreacionNaveTripulacion.this.repaint();
            }
        });

        btnAgregarTripulante = new JButton("AGREGAR TRIPULANTE");
        panel.add(btnAgregarTripulante);
        btnAgregarTripulante.setActionCommand(IVistaCreacion.CREARTRIPULANTE);

        return panel;
    }

    private JPanel crearPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBorder(BorderFactory.createTitledBorder("[3] LISTA DE PRE-EMBARQUE"));

        // Se reemplaza la JTable por un JTextArea para los carteles
        txtAreaMensajes = new JTextArea();
        txtAreaMensajes.setEditable(false);
        txtAreaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtAreaMensajes.setMargin(new Insets(5, 5, 5, 5));
        panel.add(new JScrollPane(txtAreaMensajes), BorderLayout.CENTER);

        JPanel panelEstado = new JPanel();
        panelEstado.setLayout(new BoxLayout(panelEstado, BoxLayout.Y_AXIS));

        // Sub-panel horizontal para la nómina
        JPanel panelNomina = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        lblTotalNomina = new JLabel("Total Nómina: $ 0.0");
        lblTotalNomina.setFont(new Font("Arial", Font.BOLD, 12));

        panelNomina.add(lblTotalNomina);

        panelEstado.add(Box.createVerticalStrut(10));
        panelEstado.add(panelNomina);
        panelEstado.add(Box.createVerticalStrut(15));

        lblMensajeError = new JLabel(" ");
        lblMensajeError.setFont(new Font("Arial", Font.BOLD, 12));
        lblMensajeError.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEstado.add(lblMensajeError);
        panelEstado.add(Box.createVerticalStrut(10));

        btnIrAlComando = new JButton("IR AL COMANDO");
        btnIrAlComando = new JButton("IR AL COMANDO");
        btnIrAlComando.setActionCommand(IVistaCreacion.IRALCOMANDO);
        btnIrAlComando.setEnabled(false);


        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(btnIrAlComando);

        panelEstado.add(btnPanel);
        panel.add(panelEstado, BorderLayout.SOUTH);

        return panel;
    }

    // =========================================================================
    // GETTERS PARA EL CONTROLADOR
    // =========================================================================

    public JButton getBtnInstanciarNave() { return btnInstanciarNave; }
    public JButton getBtnAgregarTripulante() { return btnAgregarTripulante; }
    public JButton getBtnIrAlComando() { return btnIrAlComando; }

    public String getNombreNave() { return txtNombreNave.getText(); }
    public String getTipoNaveSeleccionada() { return (String) comboTipoNave.getSelectedItem(); }

    public String getNombreNuevoTripulante() { return txtNombreTripulante.getText(); }
    public String getAntiguedadNueva() { return txtAntiguedad.getText(); }
    public String getOrigenNuevoTripulante() { return (String) comboOrigenTripulante.getSelectedItem(); }
    public String getCargoNuevoTripulante() { return (String) comboCargoTripulante.getSelectedItem(); }

    public String getCantConsejos() { return txtCantConsejos.getText(); }

    // =========================================================================
    // MÉTODOS DE ACTUALIZACIÓN VISUAL
    // =========================================================================

    public void actualizarNomina(double totalSalario) {
        lblTotalNomina.setText("Total Nómina: $ " + totalSalario);
    }

    @Override
    public void habilitarIrAlComando(boolean habilitar) {
        this.btnIrAlComando.setEnabled(habilitar);
    }

    public void setError(String mensaje) {
        lblMensajeError.setText(mensaje);
        lblMensajeError.setForeground(Color.RED);
    }

    public void clearError(){
        lblMensajeError.setText(" ");
    }



    public void marcarComoConfirmado() {
        btnIrAlComando.setEnabled(true);
        btnAgregarTripulante.setEnabled(false);
    }

    public void limpiarFormularioTripulante() {
        txtNombreTripulante.setText("");
        txtAntiguedad.setText("");
        txtCantConsejos.setText("");
        comboOrigenTripulante.setSelectedIndex(0);
        comboCargoTripulante.setSelectedIndex(0);
        txtNombreTripulante.requestFocus();
    }

    @Override
    public void agregarMensaje(String mensaje){
        this.txtAreaMensajes.append(mensaje + "\n");
    }

    @Override
    public void setActionListener(ActionListener a) {
        this.btnInstanciarNave.addActionListener(a);
        this.btnAgregarTripulante.addActionListener(a);
        this.btnIrAlComando.addActionListener(a);
    }


}