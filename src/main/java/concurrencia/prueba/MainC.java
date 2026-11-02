public class MainC {
    public static void main(String[] args) {
        PlataformaDeCarga plataforma=new PlataformaDeCarga();


        VistaConcurrencia vista=new VistaConcurrencia();
        vista.setVisible(true);
        plataforma.agregarObservador(vista);
        Controlador controlador=new Controlador(vista,plataforma);

    }
}
