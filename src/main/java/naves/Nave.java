package naves;

import bitacora.Bitacora;
import warp.MotorWarp;

public abstract class Nave {
    protected String identidad;
    protected int combustible;
    protected int energia;
    protected int desgaste;
    private final MotorWarp motorWarp;
    private final Bitacora bitacora;

    public Nave(String identidad,int combustible, int energia, int desgaste, Bitacora bitacora) {
        this.identidad = identidad;
        this.combustible = combustible;
        this.energia = energia;
        this.desgaste = desgaste;
        this.motorWarp = new MotorWarp();
        this.bitacora = bitacora;
    }

    //LÓGICA DE NEGOCIO
    public boolean puedeConsumir(int comb,int energ,int desg){
        boolean cond = true;
        cond &= comb >= 0 && this.combustible >= comb;
        cond &= energ >= 0 && this.energia >= energ;
        cond &= desg >= 0 && this.desgaste + desg < 100;
        return cond;
    }
    public boolean reqMant(){return this.desgaste > 80;}
    public void cargarComb(int comb){this.combustible += comb;}
    public void cargarEnerg(int energ){this.energia += energ;}
    public void mantenimiento(int desg){this.desgaste = 0;}

    //GETTERS
    public Bitacora getBit() {return bitacora;}
    public String getIdentidad() {return identidad;}
    public int getCombustible() {return combustible;}
    public int getEnergia() {return energia;}
    public int getDesgaste() {return desgaste;}
    public MotorWarp getMotorWarp() {return motorWarp;}

    @Override
    public String toString(){
        return "Identidad: "+this.identidad+" combustible: "+this.combustible+" energia: "+this.energia+" desgaste: "+this.desgaste;
    }
}