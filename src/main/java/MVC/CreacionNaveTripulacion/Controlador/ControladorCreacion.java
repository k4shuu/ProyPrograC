package MVC.CreacionNaveTripulacion.Controlador;

import MVC.ControlDeNave.Controlador.ControladorTablero;
import MVC.ControlDeNave.Vista.VistaPrincipal;
import MVC.CreacionNaveTripulacion.Vista.IVistaCreacion;
import MVC.CrearMision.Controlador.ControladorMision;
import MVC.CrearMision.Vista.VistaGameOver;
import MVC.CrearMision.Vista.VistaMision;
import asistente.AsistenteComando;
import bitacora.Bitacora;
import concurrencia.controlador.ControladorConcurrencia;
import concurrencia.modelo.PlataformaDeCarga;
import observer.Observador;
import concurrencia.vista.IVistaConcurrencia;
import concurrencia.vista.VistaConcurrencia;
import destinoytablero.Destino;
import destinoytablero.ElementoDelUniverso;
import destinoytablero.Tablero;
import elementos.Asteroide;
import elementos.EstacionesDeCarga;
import naves.*;
import tripulacion.CargoFactory;
import tripulacion.OrigenFactory;
import tripulacion.Tripulante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class ControladorCreacion implements ActionListener {

    private IVistaCreacion vista;
    private Nave nave;

    // ... (constructor, listeners y lógica de agregar tripulantes omitidos) ...

    public ControladorCreacion(IVistaCreacion vista){
        this.vista=vista;
        this.nave=null;
        this.vista.setActionListener(this);
    }


    private void transicionarAlComando() {
        try {
            ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
            for (int i = 0; i < 50; i++) elementos.add(new Asteroide());
            for (int i = 0; i < 5; i++) elementos.add(new EstacionesDeCarga());
            elementos.add(new NaveCombate("Enemigo-C1", 100, 100, 10));
            elementos.add(new NaveCarguero("Enemigo-G1", 100, 100, 10));
            elementos.add(new NaveExploradora("Enemigo-E1", 100, 100, 10));
            elementos.add(new NaveCombate("Enemigo-C2", 100, 100, 10));
            elementos.add(new NaveCarguero("Enemigo-G2", 100, 100, 10));
            elementos.add(new NaveExploradora("Enemigo-E2", 100, 100, 10));

            Tablero tablero = new Tablero(20, 20, elementos, null);
            Destino.init(tablero);
            Bitacora bitacora = new Bitacora();
            AsistenteComando asistente = new AsistenteComando(bitacora, this.nave, tablero);

            // Solo crea la siguiente pantalla: Misión
            VistaMision vistaMision = new VistaMision();
            ControladorMision cMision = new ControladorMision(vistaMision, asistente);
            cMision.iniciar();

            ((JFrame) this.vista).dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar el entorno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case IVistaCreacion.CREARNAVE:
                crearNave();
                break;
            case IVistaCreacion.CREARTRIPULANTE:
                crearTripulante();
                break;
            case IVistaCreacion.IRALCOMANDO:
                transicionarAlComando();
                break;
        }
    }

    public void crearTripulante() {
        this.vista.clearError();
        // 1. Validar dependencia principal (Nave)
        if (this.nave == null) {
            this.vista.setError("Debe crear una nave para agregar tripulante.");
            return;
        }

        // 2. Validar Nombre
        String nombre = this.vista.getNombreNuevoTripulante();
        if (nombre == null || nombre.trim().isEmpty()) {
            this.vista.setError("Ingrese un nombre de tripulante válido.");
            return;
        }

        // 3. Validar Origen y Cargo
        String origen = this.vista.getOrigenNuevoTripulante();
        if (origen == null || origen.trim().isEmpty()) {
            this.vista.setError("Seleccione un origen válido.");
            return;
        }

        String cargo = this.vista.getCargoNuevoTripulante();
        if (cargo == null || cargo.trim().isEmpty()) {
            this.vista.setError("Seleccione un cargo válido.");
            return;
        }

        // 4. Validar y parsear Antigüedad
        int antiguedad;
        try {
            antiguedad = Integer.parseInt(this.vista.getAntiguedadNueva());
            if (antiguedad < 0) {
                this.vista.setError("Ingrese una antigüedad positiva.");
                return;
            }
        } catch (NumberFormatException e) {
            this.vista.setError("Ingrese una antigüedad numérica válida.");
            return;
        }

        // 5. Validar y parsear Consejos (Solo si aplica)
        int cantConsejos = 0;
        if ("Consejero".equals(cargo)) { // Escrito al revés para evitar NullPointerException
            try {
                cantConsejos = Integer.parseInt(this.vista.getCantConsejos());
                if (cantConsejos < 0) {
                    this.vista.setError("Ingrese una cantidad de consejos positiva.");
                    return;
                }
            } catch (NumberFormatException e) {
                this.vista.setError("Ingrese una cantidad de consejos válida.");
                return;
            }
        }

        Tripulante tripulante = OrigenFactory.crear(CargoFactory.crear(cargo, nombre, antiguedad, cantConsejos), origen);
        if (nave.tieneCapitan() && tripulante.getCargo().equals("Capitan")){
            this.vista.setError("No puede haber mas de un capitan");
            return ;
        }
        if(this.nave.buscaTripulante(tripulante)){
            this.vista.setError("No puede haber un tripulante duplicado");
            return;
        }

        this.nave.agregarTripulante(tripulante);
        this.vista.agregarMensaje(tripulante.getInfo() + tripulante.getConceptoSueldo());
        this.vista.actualizarNomina(this.nave.calcularTotalHaberes());
        this.vista.habilitarIrAlComando(this.nave.tieneCapitan());

    }
    public void crearNave(){
        this.vista.clearError();
        String nombreNave=this.vista.getNombreNave();
        if(nombreNave==null || nombreNave.isEmpty()){
            this.vista.setError("Ingrese un nombre de nave valido");
            return;
        }
        String tipoNave=this.vista.getTipoNaveSeleccionada();
        Nave nave= NaveFactory.crearNave(tipoNave,nombreNave,100,100,0);
        this.nave=nave;
        this.vista.limpiarFormularioTripulante();
        this.vista.habilitarIrAlComando(false);
    }

}
