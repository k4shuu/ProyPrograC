package misiones;

import naves.Nave;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Informe de una misión que registra su fecha, resultado, último estado
 * evaluado y las variaciones de recursos de la nave.
 * Los balances se expresan como el valor final menos el valor que tenía la
 * nave al crear el informe.
 */
public class InformeMision {
    private final LocalDateTime fechaHora;
    private final String cod;
    private Resultado result;
    private Estado lastState;

    private final int combustibleInicial;
    private final int energiaInicial;
    private final int desgasteInicial;
    private final int gemasIniciales;

    private int balanceCombustible;
    private int balanceEnergia;
    private int balanceDesgaste;
    private int balanceGemas;

    /**
     * Crea un informe y toma una instantánea de los recursos de la nave.
     * precond: el código no es nulo ni vacío
     * precond: la nave es válida y sus recursos no son negativos
     * @param cod código de la misión informada
     * @param nave nave cuyos recursos iniciales se registran
     */
    public InformeMision(String cod, Nave nave) {
        assert cod != null && !cod.isBlank() : "El código de misión no puede ser nulo ni estar vacío";
        assert nave != null : "La nave no puede ser nula";
        assert nave.getCombustible() >= 0 : "El combustible inicial no puede ser negativo";
        assert nave.getEnergia() >= 0 : "La energía inicial no puede ser negativa";
        assert nave.getDesgaste() >= 0 : "El desgaste inicial no puede ser negativo";
        assert nave.getGemas() >= 0 : "La cantidad inicial de gemas no puede ser negativa";

        this.fechaHora = LocalDateTime.now();
        this.cod = cod;
        this.combustibleInicial = nave.getCombustible();
        this.energiaInicial = nave.getEnergia();
        this.desgasteInicial = nave.getDesgaste();
        this.gemasIniciales = nave.getGemas();

    }

    /**
     * Finaliza el informe con el resultado y el último estado evaluado.
     * precond: la nave, el resultado y el estado son distintos de null
     * postcond: se actualizan los balances y se registran el resultado y el estado recibidos
     * @param nave nave utilizada para calcular los balances finales
     * @param resultado resultado obtenido por la misión
     * @param estado último estado alcanzado por la misión
     */
    public void finalizar(Nave nave, Resultado resultado, Estado estado) {
        assert nave != null : "La nave no puede ser nula";
        assert resultado != null : "El resultado no puede ser nulo";
        assert estado != null : "El estado no puede ser nulo";

        actualizarBalances(nave);
        this.result = resultado;
        this.lastState = estado;

    }

    /**
     * Genera una representación legible del informe y sus balances.
     *
     * @return texto con la fecha, el código, el resultado y los balances
     */
    @Override
    public String toString() {
        return "[" + fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                "] Misión: " + cod + "\n" +
                "Resultado: " + result + "\n" +
                "Balance de combustible: " +
                formatoBalance(balanceCombustible) + "\n" +
                "Balance de energía: " +
                formatoBalance(balanceEnergia) + "\n" +
                "Balance de desgaste: " +
                formatoBalance(balanceDesgaste) + "\n" +
                "Balance de gemas: " +
                formatoBalance(balanceGemas);
    }

    /**
     * Recalcula los balances con los recursos actuales de la nave.
     * precond: la nave es distinta de null
     * postcond: cada balance refleja la diferencia entre el recurso actual y el inicial
     * @param nave nave cuyos recursos actuales se utilizarán para calcular los balances
     */
    public void actualizarBalances(Nave nave) {
        assert nave != null : "La nave no puede ser nula";

        this.balanceCombustible = nave.getCombustible() - combustibleInicial;
        this.balanceEnergia = nave.getEnergia() - energiaInicial;
        this.balanceDesgaste = nave.getDesgaste() - desgasteInicial;
        this.balanceGemas = nave.getGemas() - gemasIniciales;

    }

    /**
     * Agrega el signo positivo a los balances mayores que cero.
     *
     * @param balance balance que se desea representar
     * @return balance en formato textual
     */
    private String formatoBalance(int balance) {
        return balance > 0 ? "+" + balance : String.valueOf(balance);
    }

    public LocalDateTime getFechaHora() {return fechaHora;}
    public String getCod() {return cod;}
    public Resultado getResult() {
        return result;
    }
    public Estado getLastState() {
        return lastState;
    }
}
