# _Registro del uso de IA_

## _Consulta 1: "Implementacion del patron Decorator"_
* Herramienta utilizada: Gemini
* Proposito de la consulta: El cómo aplicar correctamente el patrón Decorator con la clase Tripulante,
considerando los siguientes aspectos: cargo, origen y antigüedad.
* Componente afectado: Las subclases de Tripulante: Cargo y Origen.
* Resultado aprovechado: El aspecto de antigüedad no debía ser considerado a la hora de implementar el patrón (ya que era irrelevante en cuanto al cálculo del sueldo) 
y se descartó la alternativa dada por la IA de volver a la clase Tripulante una interfaz (puesto que consideramos que los atributos antigüedad e identidad debían ser comunes a todas las subclases y no solamente al cargo).
* Modificacion realizada:La clase Origen se convirtió en DecoratorOrigen y se le agregó el atributo de tripulante (objeto a decorar). 
Además,se crearon las clases concretas del decorador, cada una con el método implementado de getSueldo.

