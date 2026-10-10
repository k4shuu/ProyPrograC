package testing;

import bitacora.Bitacora;
import excepciones.ExcesoCombException;
import excepciones.ExcesoDesgasteException;
import excepciones.ExcesoEnergiaException;
import excepciones.FaltaCombustibleException;
import naves.Nave;
import naves.NaveFactory;

public class DebugContratos {

    public static void main(String[] args) {
        probarExcesoEnergia();
        probarFaltaCombustible();
        probarExcesoDesgaste();
        probarExcesoCombustible();
    }

    private static void probarExcesoEnergia() {
        Bitacora bitacora = new Bitacora();
        Nave nave = crearNave("NAVE-EXCESO-ENERGIA", 100, 60, 0);
        EstadoRecursos anterior = EstadoRecursos.capturar(nave);

        iniciarCaso("Carga de energía por encima del máximo", nave);

        try {
            nave.cargarEnerg(50);
            mostrarSinExcepcion("ExcesoEnergiaException");
        } catch (ExcesoEnergiaException e) {
            mostrarExcepcion("ExcesoEnergiaException", e.getClass().getSimpleName(), e.getMensaje());
        } finally {
            finalizarCaso(anterior, nave, bitacora);
        }
    }

    private static void probarFaltaCombustible() {
        Bitacora bitacora = new Bitacora();
        Nave nave = crearNave("NAVE-FALTA-COMBUSTIBLE", 100, 60, 0);
        EstadoRecursos anterior = EstadoRecursos.capturar(nave);

        iniciarCaso("Consumo superior al combustible disponible", nave);

        try {
            nave.usarCombustible(120);
            mostrarSinExcepcion("FaltaCombustibleException");
        } catch (FaltaCombustibleException e) {
            mostrarExcepcion("FaltaCombustibleException", e.getClass().getSimpleName(), e.getMensaje());
        } finally {
            finalizarCaso(anterior, nave, bitacora);
        }
    }

    private static void probarExcesoDesgaste() {
        Bitacora bitacora = new Bitacora();
        Nave nave = crearNave("NAVE-EXCESO-DESGASTE", 100, 60, 0);
        EstadoRecursos anterior = EstadoRecursos.capturar(nave);

        iniciarCaso("Desgaste por encima del máximo", nave);

        try {
            nave.realizarDesgaste(120);
            mostrarSinExcepcion("ExcesoDesgasteException");
        } catch (ExcesoDesgasteException e) {
            mostrarExcepcion("ExcesoDesgasteException", e.getClass().getSimpleName(), e.getMensaje());
        } finally {
            finalizarCaso(anterior, nave, bitacora);
        }
    }

    private static void probarExcesoCombustible() {
        Bitacora bitacora = new Bitacora();
        Nave nave = crearNave("NAVE-EXCESO-COMBUSTIBLE", 90, 60, 0);
        EstadoRecursos anterior = EstadoRecursos.capturar(nave);

        iniciarCaso("Carga de combustible por encima del máximo", nave);

        try {
            nave.cargarComb(20);
            mostrarSinExcepcion("ExcesoCombException");
        } catch (ExcesoCombException e) {
            mostrarExcepcion("ExcesoCombException", e.getClass().getSimpleName(), e.getMensaje());
        } finally {
            finalizarCaso(anterior, nave, bitacora);
        }
    }

    private static Nave crearNave(
            String identidad,
            int combustible,
            int energia,
            int desgaste
    ) {
        return NaveFactory.crearNave(
                "CARGUERO",
                identidad,
                combustible,
                energia,
                desgaste
        );
    }

    private static void iniciarCaso(String nombreCaso, Nave nave) {
        System.out.println("========================================");
        System.out.println("CASO: " + nombreCaso);
        mostrarRecursos("ANTES", nave);
    }

    private static void finalizarCaso(
            EstadoRecursos anterior,
            Nave nave,
            Bitacora bitacora
    ) {
        mostrarRecursos("DESPUÉS", nave);
        System.out.println(
                "Los recursos conservaron el estado anterior: "
                        + anterior.coincideCon(nave)
        );

        System.out.println("BITÁCORA:");
        if (bitacora.getEntradas().isEmpty()) {
            System.out.println("(sin entradas)");
        } else {
            bitacora.mostrarBitacora();
        }
        System.out.println("========================================");
    }

    private static void mostrarExcepcion(
            String excepcionEsperada,
            String excepcionObtenida,
            String mensaje
    ) {
        System.out.println("Excepción esperada: " + excepcionEsperada);
        System.out.println("Excepción obtenida: " + excepcionObtenida);
        System.out.println("Excepción correcta: " + excepcionEsperada.equals(excepcionObtenida));
        System.out.println("Mensaje: " + mensaje);
    }

    private static void mostrarSinExcepcion(String excepcionEsperada) {
        System.out.println("Excepción esperada: " + excepcionEsperada);
        System.out.println("Excepción obtenida: NINGUNA");
        System.out.println("Excepción correcta: false");
    }

    private static void mostrarRecursos(String momento, Nave nave) {
        System.out.printf(
                "%s -> combustible=%d, energía=%d, desgaste=%d%n",
                momento,
                nave.getCombustible(),
                nave.getEnergia(),
                nave.getDesgaste()
        );
    }

    private static final class EstadoRecursos {
        private final int combustible;
        private final int energia;
        private final int desgaste;

        private EstadoRecursos(int combustible, int energia, int desgaste) {
            this.combustible = combustible;
            this.energia = energia;
            this.desgaste = desgaste;
        }

        private static EstadoRecursos capturar(Nave nave) {
            return new EstadoRecursos(
                    nave.getCombustible(),
                    nave.getEnergia(),
                    nave.getDesgaste()
            );
        }

        private boolean coincideCon(Nave nave) {
            return combustible == nave.getCombustible()
                   && energia == nave.getEnergia()
                   && desgaste == nave.getDesgaste();
        }
    }
}
