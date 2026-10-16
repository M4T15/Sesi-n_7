package Vista_Tienda;
import Modelo_TIenda.Producto;

import java.util.Scanner;

public class Vista {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- GESTIÓN DE HARDWARE Y PERIFÉRICOS ---");
        System.out.println("1. Listar todos los registros");
        System.out.println("2. Agregar un nuevo registro");
        System.out.println("3. Buscar un registro por su identificador");
        System.out.println("4. Eliminar un registro por su identificador");
        System.out.println("5. Salir");
        System.out.print("Elija una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }

    public Producto pedirDatosNuevoRegistro() {
        try {
            System.out.print("Ingrese ID (entero): ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese Descripción del artículo: ");
            String desc = scanner.nextLine();
            System.out.print("Ingrese Precio (decimal): ");
            double precio = Double.parseDouble(scanner.nextLine());
            return new Producto(id, desc, precio);
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato de número incorrecto.");
            return null;
        }
    }

    public int pedirId(String accion) {
        System.out.print("Ingrese el ID del registro a " + accion + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número entero.");
            return -1;
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}