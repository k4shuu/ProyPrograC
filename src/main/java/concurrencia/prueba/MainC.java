package concurrencia.prueba;

import concurrencia.vista.*;
import concurrencia.controlador.Controlador;
import concurrencia.modelo.PlataformaDeCarga;
import concurrencia.observer.Observador;

public class MainC {
    public static void main(String[] args) {
        PlataformaDeCarga plataforma=new PlataformaDeCarga(3);


        VistaConcurrencia vista=new VistaConcurrencia();
        vista.setVisible(true);
        plataforma.agregarObservador((Observador) vista);
       // Controlador controlador =new Controlador(vista ,plataforma, vistaPrincipal);
        //un controlador por vista ideal


    }
}
