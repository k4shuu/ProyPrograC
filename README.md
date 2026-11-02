# ProyPrograC
Trabajo Práctico Grupal (TPG) - Programación C 2026
Software Embarcado de una Nave Interestelar Tripulada (Entrega 1)

1. Requisitos de ejecución
   Entorno de desarrollo: IntelliJ IDEA (o cualquier entorno Java compatible).
   Kit de Desarrollo (JDK): OpenJDK 26 (versión 26.0.2 o superior).
   Sistema de construcción: Estructura de proyecto Java estándar configurada en el IDE.
   
2. Comandos de compilación y ejecución
   El proyecto está configurado para compilarse y ejecutarse de manera directa desde el entorno de desarrollo IntelliJ IDEA.

3. Forma de iniciar el sistema
   El sistema se inicia ejecutando el archivo principal de demostración ubicado en la raíz del proyecto:

Abrir el proyecto en IntelliJ IDEA.
Navegar hasta la clase principal Main.java en la raíz.
Hacer clic derecho sobre el archivo o el método main y seleccionar Run 'Main.main()'.
4. Forma de ejecutar las verificaciones
   Las evidencias funcionales y escenarios obligatorios se encuentran implementados mediante bloques en el archivo Main.java y se pueden verificar ejecutándolos o descomentándolos individualmente:

Escenario A (Ejecución correcta de Misiones):
Implementación: Requiere NaveFactory, AsistenteComando y MisionFactory.
Ejecución: Se crean las naves, se asigna tripulación y se invoca el ciclo mediante mision.comenzar() para M-01, M-02 y M-03, imprimiendo el informe y la Bitacora.

Escenario B (Recursos insuficientes / Errores controlados):
Implementación: Utiliza Nave con recursos límite y excepciones específicas (FaltaCombustibleException, ExcesoDesgasteException, ExcesoEnergiaException).
Ejecución: Al intentar ejecutar misiones sin recursos, se capturan las excepciones, se comprueba que el estado previo se conserva y se registra el motivo en la Bitacora.

Escenario C (Motor Warp - Patrón State):
Implementación: Se apoya en la clase MotorWarp y sus clases de estado (EstadoDisponible, EstadoEnWarp, etc.) orquestadas desde AsistenteComando.
Ejecución: Se prueba el camino feliz secuencial (solicitarPreparacionSalto(), solicitarEjecucionSalto(), etc.) y posteriormente se fuerzan transiciones inválidas para verificar su rechazo.

Escenario D (Contratos y manejo de excepciones):
Implementación: Métodos de validación interna de la clase Nave (cargarEnerg, usarCombustible, realizarDesgaste).
Ejecución: Se ejecutan operaciones que exceden los límites (capacidades máximas o valores negativos) dentro de bloques try-catch, asegurando que no se produzcan cambios parciales en la nave.
Liquidación de Haberes (Patrón Decorator):
Ejecución: Se instancian tripulantes combinando clases abstractas de cargos y decoradores de origen/antigüedad, y se comprueba llamando al método getConceptoSueldo() o mediante asistente.mostrarLiquidacionHaberes().

5. Configuración local necesaria
   No se requieren bases de datos externas, archivos de configuración complejos ni dependencias adicionales, ya que todo opera en un entorno controlado en memoria para esta Entrega 1.
   
6. Etiqueta correspondiente a la entrega
   Git Tag: entrega-e1
