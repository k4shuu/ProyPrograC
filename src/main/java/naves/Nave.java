package naves;

import asistente.AsistenteComando;
import bitacora.Bitacora;
import misiones.Mision;
import warp.MotorWarp;

public abstract class Nave {
    protected String identidad;
    protected int combustible;
    protected int energia;
    protected int desgaste;
    private final MotorWarp motorWarp;
    private AsistenteComando asistente;



    public Nave(String identidad,int combustible, int energia, int desgaste) {
        this.identidad = identidad;
        this.combustible = combustible;
        this.energia = energia;
        this.desgaste = desgaste;
        this.motorWarp = new MotorWarp();
    }

    /**
     * pre-cond: mision!=null
     * post-cond: se ejecuto la mision
     * @param mision
     */
    public void ejecutarMision(Mision mision){
        this.asistente.ejecutarMision(mision);
    }


    /**
     * precond: combustible>0
     * postcondicion: atributo combustible se vio modificado o se lanzo una excepcion
     * @param comb
     * @return
     */
    public boolean tieneCombustible(int comb){
        boolean cond;
        cond = this.combustible >= comb;
        return cond;
    }
    public boolean sePuedeDesgastar(int desgaste){
        boolean cond;
        cond = this.desgaste+desgaste<=100;
        return cond;
    }




    public boolean reqMant(){return this.desgaste > 80;}

    /**
     * pre-cond: combustible>0 y combustible<=100
     * post-cond: el combustible incremento
     * @param comb
     */
    public void cargarComb(int comb){
        if(this.combustible+comb<=100)
            this.combustible += comb;
        else{
            throw new IllegalArgumentException("El combustible ingresado no se pudo cargar por que excedia la capacidad de la nave");
        }
    }

    /**
     * pre-cond: energia>0 y combustible<=100
     * post-cond: la energia incremento
     * @param energ
     */
    public void cargarEnerg(int energ){
        if(this.energia+energ<=100)
            this.energia += energ;
        else
            throw new IllegalArgumentException("No fue posible completar la carga de energia porque excedia la capacidad de la nave");

    }

    /**
     * post-cond: el valor del desgaste es 0
     */
    public void mantenimiento(){this.desgaste = 0;}

    /**
     * pre-cond:combustible>0 y combustible<=100
     * post-cond: el valor del combustible decremento
     * @param comb
     */
    public void usarCombustible(int comb){
        if(this.combustible-comb>=0)
            this.combustible-=comb;
        else
            throw new IllegalArgumentException("La operacion de uso de combustible no fue posible porque el combustible disponible era menor al esperado");
    }

    /**
     * pre-cond: desgaste>0 y desgaste<=100
     * post-cond: desgaste aumento
     * @param desgaste
     */
    public void realizarDesgaste(int desgaste){
        if(this.desgaste+desgaste<=100)
             this.desgaste+=desgaste;
        else
            throw new IllegalArgumentException("La operacion de desgaste no fue posible porque excedia la capacidad maxima de desgaste de la nave");
    }
    //GETTERS

    public String getIdentidad() {return identidad;}
    public int getCombustible() {return combustible;}
    public int getEnergia() {return energia;}
    public int getDesgaste() {return desgaste;}
    public MotorWarp getMotorWarp() {return motorWarp;}
    public AsistenteComando getAsistente(){
        return this.asistente;
    }

    @Override
    public String toString(){
        return "Identidad: "+this.identidad+" combustible: "+this.combustible+" energia: "+this.energia+" desgaste: "+this.desgaste;
    }
}