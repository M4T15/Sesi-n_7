package Tienda_Perifericos;
import Controlador_Tienda.Controlador;
import Vista_Tienda.Vista;

public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista);
        controlador.iniciar();
    }
}
