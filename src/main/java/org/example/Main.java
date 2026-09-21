package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
        
        Tripulante capitan1=new Capitan("Juan",7);
        Tripulante vulcano1=new Vulcano(capitan1);
        Tripulante consejero2=new Consejero("Lucia",20);
        Tripulante marciano2=new Marciano(consejero2);
        Tripulante alferez3= new Alferez("Paula",1);
        Tripulante terricola3= new Terricola(alferez3);
        Tripulante teniente4=new Teniente("Pablo",3);
        Tripulante vulcano4=new Vulcano(teniente4);
        
        System.out.println(vulcano1.getConceptoSueldo());
        System.out.println(marciano2.getConceptoSueldo());
        System.out.println(terricola3.getConceptoSueldo());
        System.out.println(vulcano4.getConceptoSueldo());
        
    }
}