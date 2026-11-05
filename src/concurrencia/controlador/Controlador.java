import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador implements ActionListener {
    private IVistaPrincipal vista;
    private ArrayList<GeneradoraDeEnergia> generadores=new ArrayList<>();
    private ArrayList<NaveConsumidora> naves=new ArrayList<>();
    private PlataformaDeCarga plataforma;
    private int numGenerador=0;
    private int numNave=0;

    public Controlador(IVistaPrincipal vista,PlataformaDeCarga plataforma){
        this.vista=vista;
        this.vista.setActionListener(this);
        this.plataforma=plataforma;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.vista.limpiarError();
        switch (e.getActionCommand()){
            case IVistaPrincipal.CREARNAVE: this.crearNave();
            break;
            case IVistaPrincipal.CREARGENERADOR: this.crearGenerador();
            break;
        }
    }
    public void crearNave(){
        //Las naves y los generadores deben ser  observable?? me genera problemas para pasarles vista que es IVISTA y no Observador aca
        String nombre="N"+(++this.numNave);
        NaveConsumidora nave=new NaveConsumidora(nombre,plataforma);
        this.naves.add(nave);
        this.vista.registrarEvento("¡Ingresó una nueva Nave: "+nave.getNombre()+" al universo paralelo!");
        Thread h=new Thread(nave);
        h.start();
    }
    public void crearGenerador(){
        String nombre="G"+(++this.numGenerador);
        //Las naves y los generadores deben ser  observable?? me genera problemas para pasarles vista que es IVISTA y no Observador aca
        GeneradoraDeEnergia generador=new GeneradoraDeEnergia(nombre,plataforma);
        this.generadores.add(generador);
        this.vista.registrarEvento("¡Ingresó un nuevo Generador: "+generador.getNombre()+" al universo paralelo!");
        Thread h=new Thread(generador);
        h.start();
    }
}
