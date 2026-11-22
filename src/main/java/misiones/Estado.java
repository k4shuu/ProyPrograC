package misiones;

/**
 * Etapas del ciclo de vida de una misión.
 */
public enum Estado {
    /** La misión fue creada, pero todavía no se preparó. */
    CREADA,
    /** La ruta y los recursos necesarios fueron validados. */
    PREPARADA,
    /** La misión terminó de ejecutar su recorrido. */
    EJECUTADA,
    /** El resultado de la misión fue incorporado al informe. */
    EVALUADA,
    /** La misión fue finalizada y no admite más operaciones. */
    CERRADA
}
