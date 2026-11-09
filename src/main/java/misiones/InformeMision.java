package misiones;

import bitacora.Entrada;
import naves.Nave;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
     * precond: cod != null o vacio
     * precond: comb, energia y desgaste >= 0
     * @param cod
     * @param nave
     */
    public InformeMision(String cod, Nave nave) {
        this.fechaHora = LocalDateTime.now();
        this.cod = cod;
        this.combustibleInicial = nave.getCombustible();
        this.energiaInicial = nave.getEnergia();
        this.desgasteInicial = nave.getDesgaste();
        this.gemasIniciales = nave.getGemas();
    }

    /**
     * precond: nave, resultado y estado deben ser distintos de null
     * postcond: se actualizan los balances y se registra el resultado y el último estado de la misión
     * @param nave nave utilizada para calcular los balances finales
     * @param resultado resultado obtenido por la misión
     * @param estado último estado alcanzado por la misión
     */
    public void finalizar(Nave nave, Resultado resultado, Estado estado) {
        actualizarBalances(nave);
        this.result = resultado;
        this.lastState = estado;
    }
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
     * precond: nave debe ser distinta de null
     * postcond: los balances reflejan la diferencia entre los recursos actuales y los iniciales
     * @param nave nave cuyos recursos actuales se utilizarán para calcular los balances
     */
    public void actualizarBalances(Nave nave) {
        this.balanceCombustible = nave.getCombustible() - combustibleInicial;
        this.balanceEnergia = nave.getEnergia() - energiaInicial;
        this.balanceDesgaste = nave.getDesgaste() - desgasteInicial;
        this.balanceGemas = nave.getGemas() - gemasIniciales;
    }

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
