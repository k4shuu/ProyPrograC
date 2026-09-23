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

}
