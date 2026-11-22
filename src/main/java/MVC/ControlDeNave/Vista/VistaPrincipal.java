package MVC.ControlDeNave.Vista;

import asistente.AsistenteComando;
import observer.Observador;
import destinoytablero.Posicion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaPrincipal extends JFrame implements Observador, IVista {
    private AsistenteComando asistente;

    // Componentes Gráficos
    private VistaTablero vistaTablero;
    private JProgressBar barEnergia;
    private JProgressBar barDesgaste;
    private JProgressBar barCombustible;
    private JTextArea txtBitacora;
    private JTextArea txtMisiones;
    private JButton btnConcurrencia;

    public VistaPrincipal(AsistenteComando asistente) {
        this.asistente = asistente;

        // Observable
        this.asistente.getTablero().agregarObservador(this);

        configurarVentana();
        inicializarComponentes();
        actualizar(null); // Carga inicial
    }

    private void configurarVentana() {
        setTitle("Centro de Control - Navegación");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    public void mostrarCamino(List<Posicion> camino) {
        if (vistaTablero != null) {
            vistaTablero.setCamino(camino);
        }
    }

    private void inicializarComponentes() {
        // --- Mitad Izquierda: Tablero ---
        vistaTablero = new VistaTablero(asistente.getTablero());
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Radar Espacial"));
        panelIzquierdo.add(vistaTablero, BorderLayout.CENTER);
        JLabel lblLeyenda = new JLabel(
                "<html><center>" +
                        "▲ <b>Nave del Jugador</b> (Cian, recuadro blanco) | 🌀 <b>Portal Destino</b> (Verde)<br>" +
                        "☄️ <b>Asteroide</b> (A) | 🛠️ <b>Estación de Carga</b> (+) | 🛑 <b>Naves Enemigas:</b> Combatiente (C) | Carguero (G) | Exploradora (X)" +
                        "</center></html>",
                SwingConstants.CENTER
        );
        lblLeyenda.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panelIzquierdo.add(lblLeyenda, BorderLayout.SOUTH);

        // --- Mitad Derecha: Paneles de Estado (4 filas, 1 columna) ---
        JPanel panelDerecho = new JPanel(new GridLayout(4, 1, 5, 5));

        // 1. Recursos
        JPanel panelRecursos = new JPanel(new GridLayout(3, 2, 5, 5));
        panelRecursos.setBorder(BorderFactory.createTitledBorder("Recursos Vitales"));
        barEnergia = new JProgressBar(0, 100); barEnergia.setStringPainted(true); barEnergia.setForeground(Color.YELLOW);
        barDesgaste = new JProgressBar(0, 100); barDesgaste.setStringPainted(true); barDesgaste.setForeground(Color.CYAN);
        barCombustible = new JProgressBar(0, 100); barCombustible.setStringPainted(true); barCombustible.setForeground(Color.RED);

        panelRecursos.add(new JLabel("Energía:")); panelRecursos.add(barEnergia);
        panelRecursos.add(new JLabel("Desgaste:")); panelRecursos.add(barDesgaste);
        panelRecursos.add(new JLabel("Combustible:")); panelRecursos.add(barCombustible);

        // 2. Bitácora
        JPanel panelBitacora = new JPanel(new BorderLayout());
        panelBitacora.setBorder(BorderFactory.createTitledBorder("Bitácora de Sucesos"));
        txtBitacora = new JTextArea(); txtBitacora.setEditable(false);
        panelBitacora.add(new JScrollPane(txtBitacora), BorderLayout.CENTER);

        // 3. Historial de Misiones
        JPanel panelMisiones = new JPanel(new BorderLayout());
        panelMisiones.setBorder(BorderFactory.createTitledBorder("Misiones"));
        txtMisiones = new JTextArea(); txtMisiones.setEditable(false);
        panelMisiones.add(new JScrollPane(txtMisiones), BorderLayout.CENTER);

        // 4. Botón Concurrencia
        JPanel panelNavegacion = new JPanel();
        btnConcurrencia = new JButton("Ir a Módulo de Concurrencia");
        btnConcurrencia.setActionCommand(IVista.IR_CONCURRENCIA);
        panelNavegacion.add(btnConcurrencia);

        panelDerecho.add(panelRecursos);
        panelDerecho.add(panelBitacora);
        panelDerecho.add(panelMisiones);
        panelDerecho.add(panelNavegacion);

        // --- JSplitPane al 50% ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdo, panelDerecho);
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public void actualizar(String mensaje) {
        if (asistente == null) return;

        // Actualizar ruta restante antes de repintar
        if (asistente.getRuta() != null) {
            mostrarCamino(asistente.getRuta());
        } else {
            vistaTablero.repaint();
        }

        // Actualizar barras leyendo de la Nave
        if (asistente.getNave() != null) {
            barEnergia.setValue(asistente.getNave().getEnergia());
            barDesgaste.setValue(asistente.getNave().getDesgaste());
            barCombustible.setValue(asistente.getNave().getCombustible());
        }

        // Actualizar textos leyendo de la Bitácora
        if (asistente.getBitacora() != null) {
            txtBitacora.setText(asistente.getBitacora().getHistorialBitacora());
        }
    }

    @Override
    public void setControlador(ActionListener c) {
        btnConcurrencia.addActionListener(c);
    }

    public void mostrarMisionActual(String codigoMision) {
        String descripcion;
        switch (codigoMision.toUpperCase()) {
            case "M01":
                descripcion = "M-01: Intercepción y Asistencia\nObjetivo: Alcanzar nave aliada y transferir recursos.";
                break;
            case "M02":
                descripcion = "M-02: Recolección\nObjetivo: Llegar al destino y extraer gemas.";
                break;
            case "M03":
                descripcion = "M-03: Retorno a Punto Seguro\nObjetivo: Regresar a la base para recargar y reparar.";
                break;
            default:
                descripcion = "Misión en curso: " + codigoMision;
                break;
        }
        txtMisiones.setText("MISIÓN ACTUAL:\n" + descripcion);
    }
}