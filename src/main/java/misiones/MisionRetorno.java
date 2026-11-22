package misiones;

import asistente.AsistenteComando;

/**
 * Misión de retorno que reabastece y repara la nave al llegar al objetivo.
 */
public class MisionRetorno extends Mision{

    /**
     * Crea una misión de retorno y configura sus costos iniciales.
     * precond: el asistente es válido
     * postcond: la misión queda creada con código M03 y sus costos definidos
     * @param asistente asistente que coordina la misión
     */
    public MisionRetorno(AsistenteComando asistente) {
        super("M03", asistente);
        assert asistente != null : "El asistente no puede ser nulo";
        setCombConsumido(4);
        setDesgaste(4);

    }

    /**
     * Repone combustible y energía, repara la nave y actualiza el informe.
     * precond: la misión finalizó exitosamente
     * postcond: se recargan los recursos, se repara la nave y se actualizan los balances
     */
    @Override
    protected void aplicarResultadoExitoso() {
        assert result == Resultado.EXITOSA : "Solo una misión exitosa puede reabastecer la nave";
        assert informe != null : "Debe existir un informe para actualizar los balances";

        getNave().setCombustible(100);
        getNave().setEnergia(100);
        getNave().mantenimiento();
        informe.actualizarBalances(getNave());

    }
}
