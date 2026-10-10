package misiones;

import bitacora.Entrada;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class InformeMision {
    private final LocalDateTime fechaHora;

    private final String cod;
    private final int combGastado;
    private final int energiaCargada;
    private final int desgaste;
    private final Resultado result;
    private final Estado lastState;

    /**
     * precond: cod != null o vacio
     * precond: comb, energia y desgaste >= 0
     * @param cod
     * @param comb
     * @param energ
     * @param desgaste
     */
    public InformeMision(String cod, int comb,int energ, int desgaste, Resultado result, Estado estado) {
        assert !Objects.equals(cod,""): "Ingrese un codigo de mision valido";
        assert comb >=0 : "Combustible gastado no        assert comb >=0 : \"Combustible gastado no puede ser negativo\";\n puede ser negativo";
        assert energ >=0 : "Energia cargada no puede ser negativa";
        assert desgaste >=0 : "Desgaste generado no puede ser negativo";
        this.fechaHora = LocalDateTime.now();
        this.cod = cod;
        this.combGastado = comb;
        this.energiaCargada = energ;
        this.desgaste = desgaste;
        this.result = result;
        this.lastState = estado;
    }

    @Override
    public String toString() {
        return "[" + fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "] [ Codigo de Misión: " + cod + " ]: \n" +
                "Combustible utilizado: " + combGastado + "\n" +
                "Energía recargada: " + energiaCargada + "\n" +
                "Desgaste generado: " + desgaste;
    }

    public LocalDateTime getFechaHora() {return fechaHora;}
    public String getCod() {return cod;}
    public int getCombGastado() {return combGastado;}
    public int getEnergiaCargada() {return energiaCargada;}
    public int getDesgaste() {return desgaste;}
}