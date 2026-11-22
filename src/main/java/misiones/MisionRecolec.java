package misiones;

import asistente.AsistenteComando;

/**
 * Misión de recolección que recompensa a la nave con gemas al llegar al objetivo.
 */
public class MisionRecolec extends Mision{

    /**
     * Crea una misión de recolección y configura sus costos iniciales.
     * precond: el asistente es válido
     * postcond: la misión queda creada con código M02 y sus costos definidos
     * @param asistente asistente que coordina la misión
     */
    public MisionRecolec(AsistenteComando asistente) {
        super("M02", asistente);
        assert asistente != null : "El asistente no puede ser nulo";
        setCombConsumido(4);
        setEnergCargada(10);
        setDesgaste(4);

    }

    /**
     * Agrega entre 20 y 100 gemas a la nave y actualiza el informe.
     * precond: la misión finalizó exitosamente
     * postcond: la nave recibe la recompensa y se actualizan los balances
     */
    @Override
    protected void aplicarResultadoExitoso() {
        assert result == Resultado.EXITOSA : "Solo una misión exitosa puede otorgar gemas";
        assert informe != null : "Debe existir un informe para actualizar los balances";

        int recompensa = azar.nextInt(20, 101);
        getNave().addGemas(recompensa);
        informe.actualizarBalances(getNave());

    }

}
