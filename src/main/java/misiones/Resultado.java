package misiones;

/**
 * Posibles resultados finales de una misión.
 */
public enum Resultado {
    /** La nave alcanzó el objetivo y cumplió las condiciones de entrega. */
    EXITOSA,
    /** La misión se ejecutó, pero no pudo cumplir su objetivo. */
    FALLIDA,
    /** La misión no pudo continuar por falta de recursos o por una interrupción. */
    CANCELADA
}
