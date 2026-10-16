package Controlador_Tienda;
import Modelo_TIenda.Producto;
import Vista_Tienda.Vista;
import java.io.*;
import java.util.ArrayList;

public class Controlador {
    private Vista vista;
    private ArrayList<Producto> inventario;
    private final String ARCHIVO = "hardware.txt";

    public Controlador(Vista vista) {
        this.vista = vista;
        this.inventario = new ArrayList<>();
        leerRegistros(); // Se ejecuta al iniciar la aplicación
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1: listarRegistros(); break;
                case 2: agregarRegistro(); break;
                case 3: buscarRegistro(); break;
                case 4: eliminarRegistro(); break;
                case 5: vista.mostrarMensaje("Saliendo del programa... ¡Éxito en tu entrega!"); break;
                default: vista.mostrarMensaje("Error: Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void leerRegistros() {
        inventario.clear();
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            vista.mostrarMensaje("Archivo no encontrado. Se creará uno nuevo al agregar registros.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            vista.mostrarMensaje("Cargando registros...");
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int id = Integer.parseInt(partes[0]);
                    String desc = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    inventario.add(new Producto(id, desc, precio));
                }
            }
            listarRegistros();
        } catch (IOException | NumberFormatException e) {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void agregarRegistro() {
        Producto p = vista.pedirDatosNuevoRegistro();
        if (p != null) {
            // Verificar que el ID no exista
            for (Producto prod : inventario) {
                if (prod.getId() == p.getId()) {
                    vista.mostrarMensaje("Error: Ya existe un registro con el ID " + p.getId());
                    return;
                }
            }
            inventario.add(p);
            guardarEnArchivo();
            vista.mostrarMensaje("¡Registro agregado exitosamente!");
        }
    }

    private void buscarRegistro() {
        int idBuscado = vista.pedirId("buscar");
        if (idBuscado != -1) {
            for (Producto p : inventario) {
                if (p.getId() == idBuscado) {
                    vista.mostrarMensaje("Registro encontrado:\n" + p.toString());
                    return;
                }
            }
            vista.mostrarMensaje("Error: No se encontró ningún registro con ese ID.");
        }
    }

    private void eliminarRegistro() {
        int idEliminar = vista.pedirId("eliminar");
        if (idEliminar != -1) {
            boolean removido = inventario.removeIf(p -> p.getId() == idEliminar);
            if (removido) {
                guardarEnArchivo();
                vista.mostrarMensaje("¡Registro eliminado exitosamente!");
            } else {
                vista.mostrarMensaje("Error: No se encontró ningún registro con ese ID para eliminar.");
            }
        }
    }

    private void listarRegistros() {
        if (inventario.isEmpty()) {
            vista.mostrarMensaje("No hay registros almacenados.");
        } else {
            vista.mostrarMensaje("--- LISTA DE REGISTROS ---");
            for (Producto p : inventario) {
                vista.mostrarMensaje(p.toString());
            }
        }
    }

    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : inventario) {
                // Usamos punto y coma para separar y no confundir con puntos decimales
                pw.println(p.getId() + ";" + p.getDescripcion() + ";" + p.getPrecio());
            }
        } catch (IOException e) {
            vista.mostrarMensaje("Error grave al guardar los datos: " + e.getMessage());
        }
    }
}