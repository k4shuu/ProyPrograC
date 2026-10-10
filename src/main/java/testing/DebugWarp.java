package testing;

import asistente.AsistenteComando;
import bitacora.Bitacora;
import naves.Nave;
import naves.NaveFactory;

public class DebugWarp {

    public static void main(String[] args) {
        probarCaminoEsperado();
        probarTransicionesInvalidas();
    }

    private static void probarCaminoEsperado() {
        Bitacora bitacora = new Bitacora();
        Nave nave = NaveFactory.crearNave("EXPLORADORA", "NAVE-WARP-OK", 60, 80, 0);
        AsistenteComando asistente = new AsistenteComando(bitacora, nave);

        System.out.println("========================================");
        System.out.println("CASO: recorrido completo del Motor Warp");
        mostrarEstado("INICIAL", nave);

        probarTransicion(
                "Preparar salto desde Disponible",
                nave,
                "Preparando salto",
                () -> asistente.solicitarPreparacionSalto(nave)
        );

        probarTransicion(
                "Ejecutar salto desde Preparando salto",
                nave,
                "En warp",
                () -> asistente.solicitarEjecucionSalto(nave)
        );

        probarTransicion(
                "Desactivar Warp desde En warp",
                nave,
                "Disponible",
                () -> asistente.solicitarDesactivarWarp(nave)
        );

        System.out.println("BITÁCORA:");
        mostrarBitacora(bitacora);
        System.out.println("========================================");
    }

    private static void probarTransicionesInvalidas() {
        Bitacora bitacora = new Bitacora();
        Nave nave = NaveFactory.crearNave("EXPLORADORA", "NAVE-WARP-ERROR", 60, 80, 0);
        AsistenteComando asistente = new AsistenteComando(bitacora, nave);

        System.out.println("========================================");
        System.out.println("CASO: transiciones inválidas del Motor Warp");
        mostrarEstado("INICIAL", nave);

        probarTransicion(
                "Ejecutar salto desde Disponible",
                nave,
                "Disponible",
                () -> asistente.solicitarEjecucionSalto(nave)
        );

        probarTransicion(
                "Desactivar Warp desde Disponible",
                nave,
                "Disponible",
                () -> asistente.solicitarDesactivarWarp(nave)
        );

        probarTransicion(
                "Preparar salto desde Disponible",
                nave,
                "Preparando salto",
                () -> asistente.solicitarPreparacionSalto(nave)
        );

        probarTransicion(
                "Desactivar Warp desde Preparando salto",
                nave,
                "Preparando salto",
                () -> asistente.solicitarDesactivarWarp(nave)
        );

        probarTransicion(
                "Finalizar enfriamiento desde Preparando salto",
                nave,
                "Preparando salto",
                () -> asistente.solicitarFinalizarEnfriamiento(nave)
        );

        System.out.println("BITÁCORA:");
        mostrarBitacora(bitacora);
        System.out.println("========================================");
    }

    private static void probarTransicion(
            String nombreCaso,
            Nave nave,
            String estadoEsperado,
            Runnable operacion
    ) {
        String estadoAnterior = nave.getMotorWarp().getNombreEstadoActual();

        System.out.println("PRUEBA: " + nombreCaso);
        System.out.println("Estado anterior: " + estadoAnterior);
        operacion.run();

        String estadoPosterior = nave.getMotorWarp().getNombreEstadoActual();
        System.out.println("Estado posterior: " + estadoPosterior);
        System.out.println("Estado esperado: " + estadoEsperado);
        System.out.println("Transición correcta: " + estadoEsperado.equals(estadoPosterior));
    }

    private static void mostrarEstado(String momento, Nave nave) {
        System.out.println(
                momento + " -> " + nave.getMotorWarp().getNombreEstadoActual()
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
