package Ejercicio_3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContadorPalabras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File archivo = seleccionarArchivoConsola(sc);
        int totalLineas=0;
        int totalPalabras=0;
        int totalCaracteres=0;
        Map<String, Integer> frecuenciaPalabras = new HashMap<>();
        try (FileReader fr = new FileReader(archivo);
             BufferedReader entrada = new BufferedReader(fr)) {
            String linea = entrada.readLine();
            while (linea != null) {
                totalLineas++;
                totalCaracteres += linea.length();
                StringBuilder palabraActual = new StringBuilder();
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if (Character.isLetterOrDigit(c)) {
                        palabraActual.append(c);
                    } else {
                        if (palabraActual.length() > 0) {
                            totalPalabras++;
                            registrarPalabra(frecuenciaPalabras, palabraActual.toString());
                            palabraActual.setLength(0);
                        }
                    }
                }
                if (palabraActual.length() > 0) {
                    totalPalabras++;
                    registrarPalabra(frecuenciaPalabras, palabraActual.toString());
                }
                linea = entrada.readLine();
            }
            double promedioPalabras = (totalLineas > 0) ? (double) totalPalabras / totalLineas : 0.0;
            System.out.println("\n=== RESULTADOS DEL ANÁLISIS ===");
            System.out.println("Archivo analizado: "+archivo.getName());
            System.out.println("Total de líneas: "+totalLineas);
            System.out.println("Total de palabras: "+totalPalabras);
            System.out.println("Total de caracteres (sin saltos de línea): "+totalCaracteres);
            System.out.printf("Promedio de palabras por línea: %.2f%n",promedioPalabras);
            mostrarPalabrasMasFrecuentes(frecuenciaPalabras);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static File seleccionarArchivoConsola(Scanner sc) {
        while (true) {
            System.out.print("Introduce la ruta o nombre del archivo con la sistaxis.txt: ");
            String ruta = sc.nextLine().trim();
            File archivo = new File(ruta);
            if (archivo.exists() && archivo.isFile()) {
                return archivo;
            } else {
                System.out.println("Error: El archivo '" + ruta + "' no existe o es un directorio. Intente de nuevo.\n");
            }
        }
    }
    private static void registrarPalabra(Map<String, Integer> mapa, String palabra) {
        String palabraNormalizada = palabra.toLowerCase();
        if (mapa.containsKey(palabraNormalizada)) {
            mapa.put(palabraNormalizada, mapa.get(palabraNormalizada) + 1);
        } else {
            mapa.put(palabraNormalizada, 1);
        }
    }
    private static void mostrarPalabrasMasFrecuentes(Map<String, Integer> mapa) {
        if (mapa.isEmpty()) {
            System.out.println("Palabras más frecuentes: Ninguna (archivo vacío).");
            return;
        }
        int maxFrecuencia = 0;
        for (int conteo : mapa.values()) {
            if (conteo > maxFrecuencia) {
                maxFrecuencia = conteo;
            }
        }
        System.out.println("\nPalabras más frecuentes (con " + maxFrecuencia + " apariciones):");
        for (Map.Entry<String, Integer> entrada : mapa.entrySet()) {
            if (entrada.getValue() == maxFrecuencia) {
                System.out.println(" - \"" + entrada.getKey() + "\": " + entrada.getValue() + " veces");
            }
        }
    }
}