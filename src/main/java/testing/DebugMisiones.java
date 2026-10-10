package testing;
import asistente.*;
import bitacora.*;
import excepciones.*;
import misiones.*;
import naves.*;

public class DebugMisiones {
    public static void main(String[] args) {

        /*
         * Dejar solamente una invocación sin comentar.
         */

         //M-01: FaltaCombustibleExceptio
        probarCaso(
                "M-01 sin combustible suficiente",
                "M01",
                3,
                60,
                0,
                "FaltaCombustibleException"
        );

         //M-02: FaltaCombustibleException
        probarCaso(
                  "M-02 sin combustible suficiente",
                  "M02",
                  3,
                  60,
                  0,
                  "FaltaCombustibleException"
        );
         //M-03: FaltaCombustibleException

        probarCaso(
                "M-03 sin combustible suficiente",
                "M03",
                3,
                60,
                0,
                "FaltaCombustibleException"
        );

        // M-01: ExcesoDesgasteException
        probarCaso(
                "M-01 excede el desgaste máximo",
                "M01",
                100,
                60,
                97,
                "ExcesoDesgasteException"
        );
        // M-02: ExcesoDesgasteException
        probarCaso(
                "M-02 excede el desgaste máximo",
                "M02",
                100,
                60,
                97,
                "ExcesoDesgasteException"
        );
        //M-03: ExcesoDesgasteException
        probarCaso(
                "M-03 excede el desgaste máximo",
                "M03",
                100,
                60,
                97,
                "ExcesoDesgasteException"
        );
        /*
         * Estas dos pruebas alcanzan ExcesoEnergiaException porque
         * M-01 y M-02 actualmente ejecutan cargarEnerg(5).
         */

        //M-01: ExcesoEnergiaException
        probarCaso(
                "M-01 excede la energía máxima",
                "M01",
                100,
                98,
                0,
                "ExcesoEnergiaException"
        );
        // M-02: ExcesoEnergiaException
        probarCaso(
                "M-02 excede la energía máxima",
                "M02",
                100,
                98,
                0,
                "ExcesoEnergiaException"
        );
    }

    private static void probarCaso(String nombreCaso, String codigoMision, int combustibleInicial, int energiaInicial, int desgasteInicial, String excepcionEsperada) {
        Bitacora bitacora = new Bitacora();
        Nave nave = NaveFactory.crearNave(
                "CARGUERO",
                "NAVE-DEBUG",
                combustibleInicial,
                energiaInicial,
                desgasteInicial
        );
        AsistenteComando asistente = new AsistenteComando(bitacora, nave);
        Mision mision = MisionFactory.crearMision(codigoMision, asistente);

        int combustibleAnterior = nave.getCombustible();
        int energiaAnterior = nave.getEnergia();
        int desgasteAnterior = nave.getDesgaste();

        String excepcionObtenida = "NINGUNA";
        String mensajeObtenido = "La misión terminó sin excepciones";

        System.out.println("========================================");
        System.out.println("CASO: " + nombreCaso);
        System.out.println("Excepción esperada: " + excepcionEsperada);

        mostrarRecursos("ANTES", combustibleAnterior, energiaAnterior, desgasteAnterior);

        try {
            mision.comenzar();
        } catch (FaltaCombustibleException e) {
            excepcionObtenida = FaltaCombustibleException.class.getSimpleName();
            mensajeObtenido = e.getMensaje();

        } catch (ExcesoDesgasteException e) {
            excepcionObtenida = ExcesoDesgasteException.class.getSimpleName();
            mensajeObtenido = e.getMensaje();

        } catch (ExcesoEnergiaException e) {
            excepcionObtenida = ExcesoEnergiaException.class.getSimpleName();
            mensajeObtenido = e.getMensaje();

        } finally {
            System.out.println();
            System.out.println("Excepción obtenida: " + excepcionObtenida);
            System.out.println("Mensaje: " + mensajeObtenido);
            System.out.println("Excepción correcta: " + excepcionEsperada.equals(excepcionObtenida));

            mostrarRecursos("DESPUÉS", nave.getCombustible(), nave.getEnergia(), nave.getDesgaste());

            boolean recursosSinCambios =
                combustibleAnterior == nave.getCombustible()
                && energiaAnterior == nave.getEnergia()
                && desgasteAnterior == nave.getDesgaste();

            System.out.println("Los recursos conservaron el estado anterior: " + recursosSinCambios);

            if (!recursosSinCambios) {
                System.out.println("DEBUG: se detectaron modificaciones parciales");
            }

            System.out.println();
            System.out.println("BITÁCORA:");
            bitacora.mostrarBitacora();
            System.out.println("========================================");
        }
    }

    private static void mostrarRecursos(String momento, int combustible, int energia, int desgaste) {
        System.out.printf(
                "%s -> combustible=%d, energía=%d, desgaste=%d%n",
                momento,
                combustible,
                energia,
                desgaste
        );
    }
}
