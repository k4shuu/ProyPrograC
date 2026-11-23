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

Para ejecutar y recorrer el flujo completo de la aplicación, seguí estos pasos:

1. **Iniciar el programa:**  
   Ejecutá la clase principal (`Main`) desde tu IDE o terminal para lanzar la aplicación.

2. **Registro e inicio de sesión:**  
   En la pantalla inicial, creá una nueva cuenta de usuario y luego ingresá con esas credenciales para acceder al simulador.

3. **Creación de la nave y su tripulación:**
   * Ingresá el nombre y tipo de nave, e instanciala.
   * Agregá los tripulantes indicando nombre, origen, cargo y antigüedad (y cantidad de consejos si corresponde).
   * **Requisito obligatorio:** Para poder avanzar al Centro de Comando, la nave debe contar **sí o sí con un Capitán** y un **mínimo de 4 tripulantes en total** (incluyendo al Capitán).

4. **Selección de misión:**  
   Elegí una de las misiones disponibles (`M01 - Intercepción`, `M02 - Recolección` o `M03 - Retorno`) y presioná **Comenzar Misión**.

5. **Ejecución del juego y Módulo de Concurrencia:**
   * Comienza la simulación en el **Tablero de Navegación**, donde podrás observar en tiempo real el radar espacial, la ruta trazada, las barras de recursos vitales (energía, desgaste y combustible) y la bitácora de sucesos.
   * **Visor concurrente:** En cualquier momento durante la misión podés presionar el botón **Ir a Módulo de Concurrencia** para abrir el simulador paralelo de la plataforma de carga (generadores de energía y naves consumidoras). Si deseás regresar al tablero, podés hacerlo mediante el botón **Volver** sin interrumpir la simulación en curso.

6. **Fin de la misión (Game Over):**  
   Cuando la misión finaliza (ya sea con éxito o por fallo), se cierran el tablero y el módulo concurrente y se despliega automáticamente la pantalla de **Game Over / Fin de la Misión** con el informe detallado de balances y resultados. Desde allí podés presionar **Volver al Inicio** para regresar al Login.

---

## Arquitectura y Patrones Implementados

* **MVC desacoplado por interfaces:** Cada pantalla define su propio contrato (`IVista`, `IVistaLogin`, `IVistaCreacion`, `IVistaMision`, `IVistaGameOver`, `IVistaConcurrencia`), evitando que los controladores dependan de componentes concretos de Swing.
* **Patrón Observer unificado:** Centralizado en el paquete `observer` (`Observable` y `Observador`), utilizado para actualizar reactivamente tanto `VistaPrincipal` (a partir de `Tablero`) como `VistaConcurrencia` (a partir de `PlataformaDeCarga`).
* **Concurrencia (Productor-Consumidor):** Sincronización de hilos (`GeneradoraDeEnergia` y `NaveConsumidora`) mediante monitores (`synchronized`, `wait()`, `notifyAll()`) sobre `PlataformaDeCarga`, con actualizaciones seguras en la interfaz mediante `SwingUtilities.invokeLater`.
* **Otros patrones de diseño:** *Factory* (naves, cargos, orígenes y misiones), *Decorator* (orígenes de tripulantes), *State* (`MotorWarp`), *Singleton* (`Destino`) y *DAO/DTO* (persistencia de usuarios).

1. Configuración local necesaria
   No se requieren bases de datos externas, archivos de configuración complejos ni dependencias adicionales, ya que todo opera en un entorno controlado en memoria para esta Entrega 1.
   
2. Etiqueta correspondiente a la entrega
   Git Tag: entrega-e1
