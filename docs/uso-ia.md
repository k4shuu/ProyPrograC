# _Registro del uso de IA_

## _Consulta 1: "Implementacion del patron Decorator"_
* Herramienta utilizada: Gemini
* Proposito de la consulta: El cómo aplicar correctamente el patrón Decorator con la clase Tripulante,
considerando los siguientes aspectos: cargo, origen y antigüedad.
* Componente afectado: Las subclases de Tripulante: Cargo y Origen.
* Resultado aprovechado: El aspecto de antigüedad no debía ser considerado a la hora de implementar el patrón (ya que era insostenible el decorar una clase por año de antiguedad) 
y se descartó la alternativa dada por la IA de volver a la clase Tripulante una interfaz (puesto que consideramos que los atributos antigüedad e identidad debían ser comunes a todas las subclases y no solamente al cargo).
* Modificacion realizada:La clase Origen se convirtió en DecoratorOrigen y se le agregó el atributo de tripulante (objeto a decorar). 
Además,se crearon las clases concretas del decorador, cada una con el método implementado de getSueldo.


## Consulta 2: "Verificación y Diseño del Patrón State en el Motor Warp"

* Herramienta utilizada: Gemini.
* Propósito de la consulta: Consultar y verificar si la estructura de clases desarrollada (EstadoWarp, clases concretas de estados y MotorWarp) cumplía correctamente con los principios y requerimientos del patrón de diseño State exigidos en el requerimiento E1-02 y la rúbrica de la Entrega 1.
* Componente o documento afectado: Paquete warp (EstadoWarp.java, MotorWarp.java y las clases de estados concretos).
* Resultado aprovechado: Se validó que la arquitectura implementada utilizaba adecuadamente una interfaz común con transiciones delegadas desde el contexto, confirmando que se ajustaba formalmente a la teoría y a las restricciones de la cátedra (evitando condicionales centrales masivos).
* Revisión o modificación realizada por el equipo: El equipo contrastó la estructura sugerida con los diagramas conceptuales del modelo y aseguró la correcta integración de la gestión de excepciones para transiciones inválidas y el registro de eventos en la Bitacora.
* Forma en que se verificó el resultado: Se realizaron pruebas de ejecución controladas (escenarios de transiciones válidas e inválidas del Motor Warp) comprobando mediante consola y bitácora que las restricciones de estado operaban de forma robusta e inequívoca.

