package testing;

import asistente.AsistenteComando;
import bitacora.Bitacora;
import excepciones.ExcesoDesgasteException;
import excepciones.ExcesoEnergiaException;
import excepciones.FaltaCombustibleException;
import misiones.InformeMision;
import misiones.Mision;
import misiones.MisionFactory;
import naves.Nave;
import naves.NaveFactory;
import tripulacion.CargoFactory;
import tripulacion.OrigenFactory;

public class DebugHappyRoad {

    public static void main(String[] args) {
        probarMision("M-01 - Intercepción", "M01", "CARGUERO", 100, 60, 0, 5);
        probarMision("M-02 - Recolección", "M02", "EXPLORADORA", 60, 80, 0, 5);
        probarMision("M-03 - Retorno", "M03", "COMBATE", 80, 100, 0, 0);
    }

    private static void probarMision(
            String nombreCaso,
            String codigoMision,
            String tipoNave,
            int combustibleInicial,
            int energiaInicial,
            int desgasteInicial,
            int variacionEnergiaEsperada
    ) {
        Bitacora bitacora = new Bitacora();
        Nave nave = NaveFactory.crearNave(
                tipoNave,
                "NAVE-" + codigoMision,
                combustibleInicial,
                energiaInicial,
                desgasteInicial
        );

        agregarTripulacionValida(nave);

        AsistenteComando asistente = new AsistenteComando(bitacora, nave);
        Mision mision = MisionFactory.crearMision(codigoMision, asistente);

        System.out.println("========================================");
        System.out.println("CASO: " + nombreCaso);
        mostrarRecursos("ANTES", nave);

        try {
            InformeMision informe = mision.comenzar();

            System.out.println("Resultado de ejecución: MISIÓN COMPLETADA");
            System.out.println("Informe generado:");
            System.out.println(informe);
        } catch (FaltaCombustibleException
                 | ExcesoDesgasteException
                 | ExcesoEnergiaException e) {
            System.out.println("Resultado de ejecución: ERROR");
            System.out.println("Excepción obtenida: " + e.getClass().getSimpleName());
        } finally {
            mostrarRecursos("DESPUÉS", nave);

            int combustibleEsperado = combustibleInicial - 4;
            int energiaEsperada = energiaInicial + variacionEnergiaEsperada;
            int desgasteEsperado = desgasteInicial + 4;

            boolean recursosEsperados =
                    nave.getCombustible() == combustibleEsperado
                    && nave.getEnergia() == energiaEsperada
                    && nave.getDesgaste() == desgasteEsperado;

            System.out.printf(
                    "ESPERADO -> combustible=%d, energía=%d, desgaste=%d%n",
                    combustibleEsperado,
                    energiaEsperada,
                    desgasteEsperado
            );
            System.out.println("Los recursos coinciden con lo esperado: " + recursosEsperados);

            System.out.println("BITÁCORA:");
            mostrarBitacora(bitacora);
            System.out.println("========================================");
        }
    }

    private static void agregarTripulacionValida(Nave nave) {
        nave.agregarTripulante(OrigenFactory.crear(
                CargoFactory.crear("CAPITAN", "Capitán Debug", 10),
                "VULCANO"
        ));
        nave.agregarTripulante(OrigenFactory.crear(
                CargoFactory.crear("CONSEJERO", "Consejero Debug", 5),
                "MARCIANO"
        ));
        nave.agregarTripulante(OrigenFactory.crear(
                CargoFactory.crear("TENIENTE", "Teniente Debug", 3),
                "TERRICOLA"
        ));
        nave.agregarTripulante(OrigenFactory.crear(
                CargoFactory.crear("ALFEREZ", "Alférez Debug 1", 1),
                "TERRICOLA"
        ));
        nave.agregarTripulante(OrigenFactory.crear(
                CargoFactory.crear("ALFEREZ", "Alférez Debug 2", 2),
                "VULCANO"
        ));
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

    private static void mostrarBitacora(Bitacora bitacora) {
        if (bitacora.getEntradas().isEmpty()) {
            System.out.println("(sin entradas)");
        } else {
            bitacora.mostrarBitacora();
        }
    }
}
