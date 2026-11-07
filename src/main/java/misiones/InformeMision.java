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

    public void finalizar(Nave nave, Resultado resultado, Estado estado) {
        this.balanceCombustible = nave.getCombustible() - combustibleInicial;
        this.balanceEnergia = nave.getEnergia() - energiaInicial;
        this.balanceDesgaste = nave.getDesgaste() - desgasteInicial;
        this.balanceGemas = nave.getGemas() - gemasIniciales;

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

    private String formatoBalance(int balance) {
        return balance > 0 ? "+" + balance : String.valueOf(balance);
    }

    public LocalDateTime getFechaHora() {return fechaHora;}
    public String getCod() {return cod;}
    public int getCombGastado() {return combGastado;}
    public int getEnergiaCargada() {return energiaCargada;}
    public int getDesgaste() {return desgaste;}
    public Resultado getResult() {
        return result;
    }
    public Estado getLastState() {
        return lastState;
    }
}