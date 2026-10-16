package Ejercicio_1_y_2;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        System.out.println("\n1 Mostrando personajes iniciales:");
        gestor.mostrarPersonajes();
        
        System.out.println("\n2 Prueba para agregar nuevo personaje:");
        Personaje Mago = new Personaje("Mago", 2, 5, 1, 6);
        gestor.añadirPersonaje(Mago);
        
        System.out.println("\n3 Prueba para agregar un personaje existente:");
        Personaje duplicado = new Personaje("Guerrero", 5, 5, 5, 5);
        gestor.añadirPersonaje(duplicado);
        
        System.out.println("\n4 Prueba de modificacion de personaje:");
        gestor.modificarPersonaje("Arquero", 3, 5, 2, 10);
        
        System.out.println("\n5 Preuba de validaciones:");
        gestor.modificarPersonaje("Caballero", -1, 4, 4, 2);
        
        System.out.println("\n6 Prueba para eliminar personaje:");
        gestor.borrarPersonaje("Guerrero");
        
        System.out.println("\n7 Mostrar resultados finales:");
        gestor.mostrarPersonajes();
        
        System.out.println("\n8 Mostrar Estadisticas:");
        gestor.mostrarEstadisticas();
        
        System.out.println("\n9 Mostrar atributos actualizados:");
        gestor.actualizarAtributo("Arquero", "ataque", 10);
        
    }
}