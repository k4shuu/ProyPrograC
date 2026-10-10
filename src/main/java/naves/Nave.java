package naves;

import excepciones.ExcesoCombException;
import excepciones.ExcesoDesgasteException;
import excepciones.ExcesoEnergiaException;
import excepciones.FaltaCombustibleException;
import tripulacion.Tripulante;
import warp.MotorWarp;

import java.util.ArrayList;

public abstract class Nave {
    protected String identidad;
    protected int combustible;
    protected int energia;
    protected int desgaste;
    private final MotorWarp motorWarp;
    private ArrayList<Tripulante> tripulacion = new ArrayList<>();


    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: combustible>0 y combustible<=100
     * pre-cond: energia>0 y energia<=100
     * pre-cond: desgaste>0 y desgaste<=100
     * post-cond: se creo una instancia nave
     * @param identidad
     * @param combustible
     * @param energia
     * @param desgaste
     */
    public Nave(String identidad, int combustible, int energia, int desgaste) {
        assert combustible>0 && combustible<=100: "El combustible ingresado debe ser mayor a cero y menor o igual a 100" ;
        assert energia>0 && energia<=100:"La energia ingresada debe ser mayor a cero y menor o igual a 100";
        assert identidad!=null && !identidad.isEmpty() :"La identidad ingresada debe ser distinta de null y de cadena vacia";
        assert desgaste>0 && desgaste<=100:"El desgaste ingresado debe ser mayor a cero y menor o igual a 100";
        this.identidad = identidad;
        this.combustible = combustible;
        this.energia = energia;
        this.desgaste = desgaste;
        this.motorWarp = new MotorWarp();
        this.tripulacion=new ArrayList<>();
    }

    /**
     * precond: comb>0 y comb<=100
     * postcondicion: el atributo comb no se vio modificado??
     * @param comb
     * @return
     */
    public boolean tieneCombustible(int comb){
        assert comb>0 && comb<=100: "El combustible ingresado debe ser mayor a cero y menor o igual a 100" ;

        return this.combustible >= comb;
    }

    /**
     * precond: desgaste>0 y desgaste<=100
     * postcondicion: no se modifico ell atributo desgaste??
     * @param desgaste
     * @return
     */
    public boolean sePuedeDesgastar(int desgaste){
        boolean cond;

        assert desgaste>0:"El desgaste ingresado debe ser mayor a cero y menor o igual a 100";

        cond = this.desgaste+desgaste<=100;
        return cond;
    }

    public boolean reqMant(){return this.desgaste > 80;}

    /**
     * pre-cond: combustible>0 y combustible<=100
     * post-cond: el combustible incremento de acuerdo al valor ingresado
     * @param comb
     */
    public void cargarComb(int comb) throws ExcesoCombException {
        assert comb>0 && comb<=100: "El combustible cargado debe ser mayor a cero y menor o igual a 100";
        int combAnterior=this.combustible;
        if(this.combustible+comb<=100)
            this.combustible += comb;
        else{
            throw new ExcesoCombException("El combustible ingresado no se pudo cargar por que excedia la capacidad de la nave",combustible,100);
        }
        assert combAnterior+comb==this.combustible:"El combustible no incremento de acuerdo al valor ingresado";
    }

    /**
     * pre-cond: energia>0 y energia<=100
     * post-cond: la energia incremento de acuerdo al valor ingresado
     * @param energ
     */
    public void cargarEnerg(int energ) throws ExcesoEnergiaException {
        int energiaAnterior=this.energia;
        assert energ>0 && energ<=100:"La energia ingresada debe ser mayor a cero y menor o igual a 100";
        if(this.energia+energ<=100)
            this.energia += energ;
        else
            throw new ExcesoEnergiaException("No fue posible completar la carga de energia porque excedia la capacidad de la nave",energia,100);
        assert energiaAnterior+energ==this.energia:"La energia no incremento de acuerdo al valor ingresado";
    }

    /**
     * pre-cond: ???
     * post-cond: el valor del desgaste es 0
     */
    public void mantenimiento(){this.desgaste = 0;}

    /**
     * pre-cond:combustible>0 y combustible<=100
     * post-cond: el valor del combustible decremento de acuerdo al valor ingresado
     * @param comb
     */
    public void usarCombustible(int comb) throws FaltaCombustibleException {
        assert comb>0 && comb<=100:"Combustible ingresado debe ser mayor a cero y menor a 100";
        int combAnterior=this.combustible;
        if(this.combustible-comb>=0)
            this.combustible-=comb;
        else
            throw new FaltaCombustibleException("La operacion de uso de combustible no fue posible porque el combustible disponible era menor al esperado",comb,this.combustible);
        assert combAnterior-comb==this.combustible:"El combustible no decremento segun el valor ingresado";
    }

    /**
     * pre-cond: desgaste>0 y desgaste<=100
     * post-cond: el desgaste aumento de acuerdo al valor ingresado
     * @param desgaste
     */
    public void realizarDesgaste(int desgaste) throws ExcesoDesgasteException {
        assert desgaste>0 && desgaste<=100:"El desgaste ingresado debe ser mayor a cero y menor o igual a 100";
        int desgasteAnterior=this.desgaste;
        if(this.desgaste+desgaste<=100)
             this.desgaste+=desgaste;
        else
            throw new ExcesoDesgasteException("La operacion de desgaste no fue posible porque excedia la capacidad maxima de desgaste de la nave",this.desgaste,desgaste);
        assert desgasteAnterior+desgaste==this.desgaste:"El desgaste no incremento de acuerdo al valor ingresado";
    }

    /**
     * pre-cond: tripulante != null
     * post-con: El tamaño de tripulacion se incrementará en 1 (nuevo tripulante)
     * @param tripulante el nuevo tripulante
     */
    public void agregarTripulante(Tripulante tripulante){
        tripulacion.add(tripulante);
    }

    //GETTERS
    public String getIdentidad() {return identidad;}
    public int getCombustible() {return combustible;}
    public int getEnergia() {return energia;}
    public int getDesgaste() {return desgaste;}
    public MotorWarp getMotorWarp() {return motorWarp;}
    public void mostrarLiquidacionTripulacion() {
        System.out.println("\nLiquidación de haberes de la tripulación:");

        for (Tripulante tripulante : tripulacion)
            System.out.println(tripulante.getConceptoSueldo());
    }

    @Override
    public String toString(){
        return "Identidad: "+this.identidad+" combustible: "+this.combustible+" energia: "+this.energia+" desgaste: "+this.desgaste;
    }
}