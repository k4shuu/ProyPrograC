package concurrencia.prueba;

import concurrencia.vista.*;
import concurrencia.modelo.PlataformaDeCarga;
import observer.Observador;

public class MainC {
    public static void main(String[] args) {
        PlataformaDeCarga plataforma=new PlataformaDeCarga(3);


        VistaConcurrencia vista=new VistaConcurrencia();
        vista.setVisible(true);
        plataforma.agregarObservador((Observador) vista);
       // ControladorConcurrencia controlador =new ControladorConcurrencia(vista ,plataforma, vistaPrincipal);
        //un controlador por vista ideal


    }
}
