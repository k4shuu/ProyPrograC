package naves;

import destinoytablero.ElementoDelUniverso;
import excepciones.ExcesoCombException;
import excepciones.ExcesoDesgasteException;
import excepciones.ExcesoEnergiaException;
import excepciones.FaltaCombustibleException;
import tripulacion.Tripulante;
import warp.MotorWarp;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Nave implements ElementoDelUniverso {
    protected String identidad;
    protected int combustible;
    protected int energia;
    protected int desgaste;
    protected boolean destruida=false;
    protected final MotorWarp motorWarp;
    protected ArrayList<Tripulante> tripulacion = new ArrayList<>();


    public boolean estaViva(){
        return !this.destruida && energia>0 && desgaste<100 && combustible>0;
    }
    public void colisionConAsteroide(int energia){
        if ((this.energia-energia)<0)
            this.energia=0;
        else
            this.energia-=energia;
    }

    public void ayudaEstacion(int energia){
        this.cargarEnerg(energia);
    }
    public void usarEnergia(int energia){
        if((this.energia-energia)<0) {
            this.energia = 0;
            //faltaria lanzar excepcion aca
        }
        else
            this.energia-=energia;
    }
    public abstract Resultado atacadoPorExploradora();
    public abstract Resultado atacadoPorCarguero();
    public abstract Resultado atacadoPorCombate();

    /**
     * pre-cond: identidad!=null e identidad!=""
     * pre-cond: combustible>0 y combustible<=100
     * pre-cond: energia>0 y energia<=100
     * pre-cond: desgaste>0 y desgaste<=100
     * post-cond: se creo una instancia nave con los parametros dados
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
     * @param comb
     * @return
     */
    public boolean tieneCombustible(int comb){
        assert comb>0 && comb<=100: "El combustible ingresado debe ser mayor a cero y menor o igual a 100" ;
        return this.combustible >= comb;
    }

    /**
     * precond: desgaste>0 y desgaste<=100
     * @param desgaste
     * @return
     */
    public boolean sePuedeDesgastar(int desgaste){
        assert desgaste>0 && desgaste<=100:"El desgaste ingresado debe ser mayor a cero y menor o igual a 100";
        return this.desgaste+desgaste<=100;
    }

    /**
     * pre-cond: energia>0 y energia<=100
     * @param energia
     * @return
     */

    public boolean puedeCargarEnergia(int energia){
        assert energia>0 && energia<=100:"La energia ingresada debe ser mayor a cero y menor o igual a 100";
        return this.energia + energia <= 100;
    }
    public boolean reqMant(){return this.desgaste > 80;}

    /**
     * pre-cond: combustible>0 y combustible<=100
     * post-cond: el combustible incremento de acuerdo al valor ingresado
     * @param comb
     */
    public void cargarComb(int comb){
        assert comb>0 && comb<=100: "El combustible cargado debe ser mayor a cero y menor o igual a 100";
        int combAnterior=this.combustible;
        if(this.combustible+comb<=100)
            this.combustible += comb;
        else{
            this.combustible=100;
        }
        assert combAnterior+comb==this.combustible:"El combustible no incremento de acuerdo al valor ingresado";
    }

    /**
     * pre-cond: energia>0 y energia<=100
     * post-cond: la energia incremento de acuerdo al valor ingresado
     * @param energ
     */
    public void cargarEnerg(int energ){
        int energiaAnterior=this.energia;
        assert energ>0 && energ<=100:"La energia ingresada debe ser mayor a cero y menor o igual a 100";
        if(this.energia+energ<=100)
            this.energia += energ;
        else
            this.energia=100;
        assert energiaAnterior + energ == this.energia : "La energia no incremento de acuerdo al valor ingresado";
    }

    /**
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
     * post-cond: El tamaño de tripulacion se incrementará en 1 (nuevo tripulante)
     * @param tripulante el nuevo tripulante
     */
    public void agregarTripulante(Tripulante tripulante){
        assert tripulante!=null:"El tripulante debe ser distinto de null";
        tripulacion.add(tripulante);
    }

    //GETTERS
    public String getIdentidad() {return identidad;}
    public int getCombustible() {return combustible;}
    public int getEnergia() {return energia;}
    public int getDesgaste() {return desgaste;}
    public MotorWarp getMotorWarp() {return motorWarp;}
    public Iterator<Tripulante> getTripulacion() {
        return this.tripulacion.iterator();
    }
    public void mostrarLiquidacionTripulacion() {
        System.out.println("\nLiquidación de haberes de la tripulación:");

        for (Tripulante tripulante : tripulacion)
            System.out.println(tripulante.getConceptoSueldo());
    }
    public void setCombustible(int comb){this.combustible=comb;}
    public void setEnergia(int energia){this.energia=energia;}
    public void setIdentidad(String identidad){this.identidad=identidad;}
    public void setDesgaste(int desgaste){this.desgaste=desgaste;}
    public void setTripulacion(ArrayList<Tripulante> tripulacion){this.tripulacion=tripulacion;}

    @Override
    public String toString(){
        return "Identidad: "+this.identidad+" combustible: "+this.combustible+" energia: "+this.energia+" desgaste: "+this.desgaste;
    }
}