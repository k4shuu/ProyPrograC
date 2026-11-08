package MVC.ControlDeNave.Vista;

import destinoytablero.Observador;
import destinoytablero.Tablero;
import naves.Nave;
import bitacora.Bitacora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPrincipal extends JFrame implements Observador, IVista {
    private Tablero tablero;
    private Nave nave;
    private Bitacora bitacora;

    // Componentes Gráficos
    private VistaTablero vistaTablero;
    private JProgressBar barEnergia;
    private JProgressBar barDesgaste;
    private JProgressBar barCombustible;
    private JTextArea txtBitacora;
    private JTextArea txtMisiones;
    private JButton btnConcurrencia;

    public VistaPrincipal(Tablero tablero, Nave nave, Bitacora bitacora) {
        this.tablero = tablero;
        this.nave = nave;
        this.bitacora = bitacora;

        // ¡Suscripción al Observable de tu paquete!
        this.tablero.agregarObservador(this);

        configurarVentana();
        inicializarComponentes();
        actualizar(); // Carga inicial
    }

    private void configurarVentana() {
        setTitle("Centro de Control - Navegación");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        // --- Mitad Izquierda: Tablero ---
        vistaTablero = new VistaTablero(tablero);
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Radar Espacial"));
        panelIzquierdo.add(vistaTablero, BorderLayout.CENTER);

        // --- Mitad Derecha: Paneles de Estado ---
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

        panelDerecho.add(panelRecursos);
        panelDerecho.add(panelBitacora);
        panelDerecho.add(panelMisiones);

        // --- JSplitPane al 50% ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdo, panelDerecho);
        splitPane.setResizeWeight(0.5); // Ocupa 50% exacto
        add(splitPane, BorderLayout.CENTER);

        JPanel panelNavegacion = new JPanel();
        btnConcurrencia = new JButton("Ir a Módulo de Concurrencia");

        // ASIGNAMOS UN COMANDO AL BOTÓN
        btnConcurrencia.setActionCommand(IVista.IR_CONCURRENCIA);

        panelNavegacion.add(btnConcurrencia);
        panelDerecho.add(panelNavegacion);
    }

    // Método definido en destinoytablero.Observador
    @Override
    public void actualizar() {
        // Obliga al panel del tablero a redibujarse con las nuevas posiciones
        vistaTablero.repaint();

        // Actualizar barras leyendo de la Nave
        if (nave != null) {
            barEnergia.setValue(nave.getEnergia());
            barDesgaste.setValue(nave.getDesgaste());
            barCombustible.setValue(nave.getCombustible());
        }

        // Actualizar textos leyendo de la Bitácora
        if (bitacora != null) {
            txtBitacora.setText(bitacora.getHistorialBitacora());
            // Reemplaza con el método real que devuelva el string o lista de la bitácora
            // txtBitacora.setText(bitacora.obtenerTextoCompleto());
        }
    }
    @Override
    public void setControlador(ActionListener c) {
        // La vista le pega el "listener" (el controlador) a sus propios botones internamente

        btnConcurrencia.addActionListener(c);
    }
}
