package Controlador;


import Vista.IVistaCreacion;
import naves.Nave;

public class ControladorCreacion {

    private IVistaCreacion vista;
    private Nave nave;

    // ... (constructor, listeners y lógica de agregar tripulantes omitidos) ...

    private void transicionarAlComando() {
        // 1. Se instancia la vista de la siguiente sección (Control de la Nave)
        //VistaCentroComando vistaComando = new VistaCentroComando();

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
}
