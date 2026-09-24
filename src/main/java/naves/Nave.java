package naves;

import bitacora.Bitacora;

public abstract class Nave {
    protected String identidad;
    protected int combustible;
    protected int energia;
    protected int desgaste;
    protected Bitacora bit;

    public Nave(String identidad,int combustible, int energia, int desgaste) {
        this.identidad = identidad;
        this.combustible = combustible;
        this.energia = energia;
        this.desgaste = desgaste;
        this.bit = new Bitacora();

    }
    @Override
    public String toString(){
        return "Identidad: "+this.identidad+" combustible: "+this.combustible+" energia: "+this.energia+" desgaste: "+this.desgaste;
    }

    public boolean puedeConsumir(int comb,int energ,int desg){
        boolean cond = true;
        cond &= comb >= 0 && this.combustible >= comb;
        cond &= energ >= 0 && this.energia >= energ;
        cond &= desg >= 0 && this.desgaste + desg < 100;
        return cond;
    }

    public boolean reqMant(){
        return this.desgaste > 80;
    }

    public void cargarComb(int comb){this.combustible += comb;}
    public void cargarEnerg(int energ){ this.energia += energ; }
    public void mantenimiento(int desg){ this.desgaste = 0; }

    public Bitacora getBit() {return bit;}
    public String getIdentidad() {return identidad;}
    public int getCombustible() {return combustible;}
    public int getEnergia() {return energia;}
    public int getDesgaste() {return desgaste;}
}
