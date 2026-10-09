package misiones;

import bitacora.Entrada;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InformeMision {
    private final LocalDateTime fechaHora;

    private final String cod;
    private final int combGastado;
    private final int energiaCargada;
    private final int desgaste;


    public InformeMision(String cod, int comb,int energ, int desgaste) {
        this.fechaHora = LocalDateTime.now();
        this.cod = cod;
        this.combGastado = comb;
        this.energiaCargada = energ;
        this.desgaste = desgaste;
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