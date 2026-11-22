package MVC.CreacionNaveTripulacion.Controlador;

import MVC.ControlDeNave.Vista.VistaPrincipal;
import MVC.CrearMision.Controlador.ControladorMision;
import MVC.CrearMision.Vista.VistaGameOver;
import MVC.CreacionNaveTripulacion.Vista.IVistaCreacion;
import MVC.CrearMision.Vista.VistaMision;
import MVC.CrearMision.Vista.IVistaMision;

import asistente.AsistenteComando;
import bitacora.Bitacora;
import concurrencia.modelo.PlataformaDeCarga;
import destinoytablero.Destino;
import destinoytablero.ElementoDelUniverso;
import destinoytablero.Tablero;
import elementos.Asteroide;
import elementos.EstacionesDeCarga;
import excepciones.CantidadDeElementosSuperaCasillaException;
import excepciones.DimensionesDeTableroInconsistentesException;
import naves.*;
import tripulacion.CargoFactory;
import tripulacion.OrigenFactory;
import tripulacion.Tripulante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        // 1. Se instancia la vista de la siguiente sección (Control de la Nave)
        //VistaCentroComando vistaComando = new VistaCentroComando();
        System.out.println("CLICK EN IR AL COMANDO");


        // 1. Modelos base
        ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
        // Asteroides
        for (int i = 0; i < 6; i++) {
            elementos.add(new Asteroide());
        }

        // Estaciones de carga
        for (int i = 0; i < 3; i++) {
            elementos.add(new EstacionesDeCarga());
        }

        // Naves enemigas (respetando las precondiciones de Nave: valores > 0 y <= 100)
        elementos.add(new NaveCombate("Enemigo-C1", 100, 100, 10));
        elementos.add(new NaveCarguero("Enemigo-G1", 100, 100, 10));
        elementos.add(new NaveExploradora("Enemigo-E1", 100, 100, 10));

        Tablero tablero = null;
        try {
            tablero = new Tablero(20, 20, elementos, null);
        } catch (DimensionesDeTableroInconsistentesException e) {
            throw new RuntimeException(e);
        } catch (CantidadDeElementosSuperaCasillaException e) {
            throw new RuntimeException(e);
        }
        Destino.init(tablero);

        Bitacora bitacora = new Bitacora();


        // Ajusta el orden/cantidad de parámetros según tu constructor real de AsistenteComando
        AsistenteComando asistente = new AsistenteComando(bitacora,nave,tablero);

        VistaMision vistaM=new VistaMision();
        VistaPrincipal vistaP=new VistaPrincipal(asistente,tablero,this.nave,bitacora);
        VistaGameOver vistaG=new VistaGameOver();
        ControladorMision controlM=new ControladorMision(vistaM,vistaP,vistaG,asistente);

        vistaM.setVisible(true);
        this.vista.dispose();




        // 2. Se instancia el entorno/tablero (si tu arquitectura lo requiere aquí)
        //Tablero tablero = new Tablero();

        // 3. Se crea el controlador de la nueva vista inyectándole la vista y los modelos listos
        //ControladorCentroComando cComando = new ControladorCentroComando(vistaComando, this.nave, tablero);

        // 4. Se hace visible la nueva ventana
        //vistaComando.setVisible(true);

        // 5. Se destruye la ventana actual. Esto elimina la interfaz gráfica de creación
        // de la memoria y finaliza el ciclo de vida de este ControladorCreacion.
        //this.vista.dispose();
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
        this.vista.agregarMensaje(tripulante.getInfo()+tripulante.getConceptoSueldo());
        this.vista.actualizarNomina(this.nave.calcularTotalHaberes());

    }
    public void crearNave(){
        this.vista.clearError();
        String nombreNave=this.vista.getNombreNave();
        if(nombreNave==null || nombreNave.isEmpty()){
            this.vista.setError("Ingrese un nombre de nave valido");
        }
        String tipoNave=this.vista.getTipoNaveSeleccionada();
        Nave nave= NaveFactory.crearNave(tipoNave,nombreNave,100,100,0);
        this.nave=nave;
        this.vista.limpiarFormularioTripulante();
    }

}
