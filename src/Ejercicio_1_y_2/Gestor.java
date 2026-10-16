package Ejercicio_1_y_2;

import java.io.*;
import java.util.ArrayList;
//La clase Gestor encarga de realizar operaciones CRUD
public class Gestor {
    private ArrayList<Personaje> personajes = new ArrayList<>();
    private String archivo = "personajes.txt";

    public Gestor() {
        cargarDesdeArchivo();
    }
    
    //Agrega un nuevo personaje sin repetirse
    
    public boolean añadirPersonaje(Personaje nuevo) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                System.out.println("Error: El personaje" + nuevo.getNombre() + " ya fue registado");
                return false;
            }
        }
        personajes.add(nuevo);
        guardarEnArchivo();
        return true;
    }

    //Muestra todos los personajes registrados 
    
    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes.");
        } else {
            for (Personaje p : personajes) {
                System.out.println(p);
            }
        }
    }

    //Modifica los atributos de un peronaje por medio de su nombre 
    
    public void modificarPersonaje(String nombre, int v, int a, int d, int al) {
        boolean encontrado = false;
    	for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                try {
                    p.setVida(v);
                    p.setAtaque(a);
                    p.setDefensa(d);
                    p.setAlcance(al);
                    guardarEnArchivo();
                    System.out.println("Personaje "+ nombre + " modificado con exito");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error de validacion al modificar " + e.getMessage());
                }
                encontrado = true;
                break;
            }
        }
    	if(!encontrado) {
    		System.out.println("Error: No se encontro el personaje "+ nombre + " .");
    	}
    }
    
    //Bprra un m perosnaje por medio de su nombre usando removeIf
    
    public void borrarPersonaje(String nombre) {
    	//remoIf ayuda a identificar un nombre mientras se recorre la lista
    	boolean removido = personajes.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    	if(removido) {
    		guardarEnArchivo();
    		System.out.println("Personaje "+ nombre + " eliminado correctamente.");
    	}else {
    		System.out.println("Error: Personaje "+ nombre + " No encontrado para eliminar.");
    	}
    }
    
    //Guarda la lista actual de los peronajes un archivo de texto
    
    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                pw.println(p.getNombre() + "," + p.getVida() + "," + p.getAtaque() + "," + p.getDefensa() + "," + p.getAlcance());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: ." + e.getMessage());
        }
    }
    //Carga los personajes desde el archivo. Si no se cran por defecto
    private void cargarDesdeArchivo() {
        File f = new File(archivo);
        if (!f.exists()) {
            personajes.add(new Personaje("Caballero", 4, 2, 4, 2));
            personajes.add(new Personaje("Guerrero", 2, 4, 2, 4));
            personajes.add(new Personaje("Arquero", 2, 4, 1, 8));
            guardarEnArchivo();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                	try {
                        String nombre = datos[0];
                        int vida = Integer.parseInt(datos[1]);
                        int ataque = Integer.parseInt(datos[2]);
                        int defensa = Integer.parseInt(datos[3]);
                        int alcance = Integer.parseInt(datos[4]);
                        personajes.add(new Personaje(nombre, vida, ataque, defensa, alcance));
                	}catch ( IllegalArgumentException ex) {
                		System.out.println("Error leyendo datos corruptos de un personaje "+ ex.getMessage());
                	}
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar desde el archivo: " + e.getMessage());
        }
    }
    
    public void mostrarEstadisticas() {
    	if(personajes.isEmpty()) {
    		System.out.println("No hay personsajes para calcular estadisticas.");
    		return;
    	}
    	int sumaVida = 0;
    	int sumaAtaque = 0;
    	int sumaDefensa = 0;
    	int sumaAlcance = 0;
    	
    	for(Personaje p: personajes) {
    		sumaVida += p.getVida();
    		sumaAtaque += p.getAtaque();
    		sumaDefensa += p.getDefensa();
    		sumaAlcance += p.getAlcance();
    		int total = personajes.size();
    		System.out.println("----ESTADISTICAS GENERALES----.");
    		System.out.println("Total de personajes: " + total);
    		System.out.println("Promedio de vida: " + ((double) sumaVida / total));
    		System.out.println("Promedio de Ataque: " + ((double) sumaAtaque / total));
    		System.out.println("Promedio de Defensa: " + ((double) sumaDefensa / total));
    		System.out.println("Promedio de Alcance: " + ((double) sumaAlcance / total));
    	}
    }
    
    public void  actualizarAtributo(String nombre, String atributo, int nuevoValor){
    	boolean encontrado = false;
    	 for(Personaje p: personajes) {
    		 if(p.getNombre().equalsIgnoreCase(nombre)) {
    			 encontrado = true;
    					 try {
    						 //se analiza el nombre en minusculas para evitar errores
    						 switch(atributo.toLowerCase()) {
    						 case "vida":
    							 p.setVida(nuevoValor);
    							 break;
    						
    						 case "ataque":
    							 p.setAtaque(nuevoValor);
    							 break;
    							 
    						 case"defensa":
    							 p.setDefensa(nuevoValor);
    							 break;
    							 
    						 case"alcance":
    							 p.setAlcance(nuevoValor);
    							 
    						 default:
    							 System.out.println("Error: El atributo "+atributo+" no existe");
    							 return;
    						 }
    						 
    						 guardarEnArchivo(); // cuando el switch termine se guardaran los cambios
    						 System.out.println("Atributo "+atributo+" de "+nombre+" actualizado correctamente");
    					 } catch(IllegalArgumentException e) {
    						 System.out.println("Error de validacion: "+ e.getMessage());
    					 }
    					 break;
    		 }
    	 }
    	 if(!encontrado) {
    		 System.out.println("Error: Personaje "+ nombre+ " no encontrado.");
    	 }
    	
    }
}

