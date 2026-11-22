package misiones;

import asistente.AsistenteComando;
import excepciones.FaltaCombustibleException;
import excepciones.FaltaEnergiaException;

/**
 * Misión de intercepción que transporta una cantidad aleatoria de combustible
 * y energía hasta el objetivo.
 */
public class MisionIntercep extends Mision {
    private final int combustibleSolicitado;
    private final int energiaSolicitada;

    /**
     * Crea una misión de intercepción y define sus costos y recursos solicitados.
     * precond: el asistente es válido
     * postcond: la misión queda creada con código M01 y sus recursos definidos
     * @param asistente asistente que coordina la misión
     */
    public MisionIntercep(AsistenteComando asistente) {
        super("M01", asistente);
        assert asistente != null : "El asistente no puede ser nulo";
        setCombConsumido(4);
        setEnergCargada(5);
        setDesgaste(4);
        combustibleSolicitado = azar.nextInt(51);
        energiaSolicitada = azar.nextInt(51);

    }

    /**
     * Entrega el combustible y la energía solicitados y actualiza el informe.
     * precond: la misión fue exitosa y la nave conserva los recursos solicitados
     * postcond: los recursos se descuentan y se actualizan los balances
     */
    @Override
    protected void aplicarResultadoExitoso() {
        assert result == Resultado.EXITOSA : "Solo una misión exitosa puede entregar recursos";
        assert informe != null : "Debe existir un informe para actualizar los balances";
        assert cumpleCondicionFinal() : "La nave debe conservar los recursos solicitados";

        try {
            getNave().usarCombustible(combustibleSolicitado);
            getNave().usarEnergia(energiaSolicitada);
            informe.actualizarBalances(getNave());
        } catch (FaltaCombustibleException | FaltaEnergiaException ignored) {
        }
    }

    /**
     * Comprueba si la nave conserva los recursos que debe entregar.
     *
     * @return true si la nave dispone del combustible y la energía solicitados; false en caso contrario
     */
    @Override
    protected boolean cumpleCondicionFinal() {
        return getNave().getCombustible() >= combustibleSolicitado && getNave().getEnergia() >= energiaSolicitada;
    }
}
