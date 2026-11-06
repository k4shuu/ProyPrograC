package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaCreacionNaveTripulacion extends JFrame {

    // --- Componentes de la Columna Izquierda (Creación) ---
    private JComboBox<String> comboTipoNave;
    private JButton btnInstanciarNave;

    // Todos los campos de ingreso pasados a JTextField simples
    private JTextField txtNombreTripulante;
    private JTextField txtOrigenTripulante;
    private JTextField txtAntiguedad;
    private JTextField txtCargoTripulante;

    private JButton btnAgregarTripulante;

    // --- Componentes de la Columna Derecha (Lista) ---
    private JTable tablaTripulantes;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotalNomina;
    private JLabel lblRequisitoCapitan;
    private JLabel lblRequisitoBase;
    private JLabel lblEstadoDespegue;

    private JButton btnConfirmarTripulacion;
    private JButton btnIrAlComando;

    public VistaCreacionNaveTripulacion() {
        this.setTitle("Hangar Central - Preparación de Misión");
        this.setSize(900, 550);
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

        panel.add(new JLabel("Tipo de Nave:"));
        comboTipoNave = new JComboBox<>(new String[]{"Exploradora", "Carguera", "Combate"});
        panel.add(comboTipoNave);
        panel.add(Box.createVerticalStrut(10));

        btnInstanciarNave = new JButton("INSTANCIAR NAVE");
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
        txtOrigenTripulante = new JTextField();
        txtOrigenTripulante.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(txtOrigenTripulante);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Antigüedad (Años):"));
        txtAntiguedad = new JTextField();
        txtAntiguedad.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(txtAntiguedad);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Cargo asignado:"));
        txtCargoTripulante = new JTextField();
        txtCargoTripulante.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(txtCargoTripulante);
        panel.add(Box.createVerticalStrut(15));

        btnAgregarTripulante = new JButton("AGREGAR TRIPULANTE");
        panel.add(btnAgregarTripulante);

        return panel;
    }

    private JPanel crearPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBorder(BorderFactory.createTitledBorder("[3] LISTA DE PRE-EMBARQUE"));

        String[] columnas = {"Nombre", "Origen", "Antigüedad", "Cargo", "Salario"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaTripulantes = new JTable(modeloTabla);
        panel.add(new JScrollPane(tablaTripulantes), BorderLayout.CENTER);

        JPanel panelEstado = new JPanel();
        panelEstado.setLayout(new BoxLayout(panelEstado, BoxLayout.Y_AXIS));

        lblTotalNomina = new JLabel("Total Nómina: $ 0.0");
        lblTotalNomina.setFont(new Font("Arial", Font.BOLD, 12));
        panelEstado.add(lblTotalNomina);
        panelEstado.add(Box.createVerticalStrut(15));

        panelEstado.add(new JLabel("--- REQUISITOS DE DESPEGUE ---"));

        lblRequisitoCapitan = new JLabel("[ ] Capitán asignado (0/1)");
        lblRequisitoBase = new JLabel("[ ] Tripulantes base (0/4)");
        lblEstadoDespegue = new JLabel("ESTADO: 🔴 INCOMPLETO");
        lblEstadoDespegue.setForeground(Color.RED);

        panelEstado.add(lblRequisitoCapitan);
        panelEstado.add(lblRequisitoBase);
        panelEstado.add(Box.createVerticalStrut(10));
        panelEstado.add(lblEstadoDespegue);
        panelEstado.add(Box.createVerticalStrut(15));

        btnConfirmarTripulacion = new JButton("CONFIRMAR TRIPULACIÓN");
        btnConfirmarTripulacion.setEnabled(false);

        btnIrAlComando = new JButton("IR AL COMANDO");
        btnIrAlComando.setEnabled(false);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.add(btnConfirmarTripulacion);
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
    public JButton getBtnConfirmarTripulacion() { return btnConfirmarTripulacion; }
    public JButton getBtnIrAlComando() { return btnIrAlComando; }

    public String getTipoNaveSeleccionada() { return (String) comboTipoNave.getSelectedItem(); }

    public String getNombreNuevoTripulante() { return txtNombreTripulante.getText(); }
    public String getOrigenNuevoTripulante() { return txtOrigenTripulante.getText(); }
    public String getAntiguedadNueva() { return txtAntiguedad.getText(); }
    public String getCargoNuevoTripulante() { return txtCargoTripulante.getText(); }

    public DefaultTableModel getModeloTabla() { return modeloTabla; }

    // =========================================================================
    // MÉTODOS DE ACTUALIZACIÓN VISUAL
    // =========================================================================

    public void actualizarContadores(int caps, int base, double totalSalario, boolean listoParaConfirmar) {
        lblTotalNomina.setText("Total Nómina: $ " + totalSalario);
        lblRequisitoCapitan.setText((caps == 1 ? "[X]" : "[ ]") + " Capitán asignado (" + caps + "/1)");
        lblRequisitoBase.setText((base >= 4 ? "[X]" : "[ ]") + " Tripulantes base (" + base + "/4)");

        if (listoParaConfirmar) {
            lblEstadoDespegue.setText("ESTADO: 🟡 LISTO PARA CONFIRMAR");
            lblEstadoDespegue.setForeground(new Color(204, 153, 0));
            btnConfirmarTripulacion.setEnabled(true);
        } else {
            lblEstadoDespegue.setText("ESTADO: 🔴 INCOMPLETO");
            lblEstadoDespegue.setForeground(Color.RED);
            btnConfirmarTripulacion.setEnabled(false);
            btnIrAlComando.setEnabled(false);
        }
    }

    public void marcarComoConfirmado() {
        lblEstadoDespegue.setText("ESTADO: 🟢 TRIPULACIÓN CONFIRMADA");
        lblEstadoDespegue.setForeground(new Color(0, 153, 0));
        btnConfirmarTripulacion.setEnabled(false);
        btnIrAlComando.setEnabled(true);
    }

    public void limpiarFormularioTripulante() {
        txtNombreTripulante.setText("");
        txtOrigenTripulante.setText("");
        txtAntiguedad.setText("");
        txtCargoTripulante.setText("");
        txtNombreTripulante.requestFocus();
    }
}
