package Ejercicio_1_y_2;

import java.io.Serializable;

//se usa Serializable para el uso de archivos binarios

public class Personaje implements Serializable {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        //Los setters son utiles para relizar validaciones
    	this.nombre = nombre;
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
    }

    public String getNombre() {
    	return nombre; 
    	}
    
    public void setNombre(String nombre) {
    	this.nombre = nombre; 
    	}

    public int getVida() {
    	return vida; 
    	}
    
    public void setVida(int vida) {
        if (vida <= 0)
        	throw new IllegalArgumentException("la vida no pude ser negativa ");
        this.vida = vida;
    }

    public int getAtaque() {
    	return ataque; 
    	}
    
    public void setAtaque(int ataque) {
        if (ataque <= 0) 
        	throw new IllegalArgumentException("no exite ataque negativo no puede ser cero ");
        this.ataque = ataque;
    }

    public int getDefensa() { 
    	return defensa; 
    	}
    
    public void setDefensa(int defensa) {
        if (defensa <= 0) 
        	throw new IllegalArgumentException("la defensa no puede ser negativa ");
        this.defensa = defensa;
    }

    public int getAlcance() {
    	return alcance; 
    	}
    public void setAlcance(int alcance) {
        if (alcance <= 0) 
        	throw new IllegalArgumentException("el alcanze no úede ser negativo ");
        this.alcance = alcance;
    }

    @Override
    public String toString() {
        return String.format("%-12s | Vida: %-2d | Ataque: %-2d | Defensa: %-2d | Alcance: %-2d", 
                nombre, vida, ataque, defensa, alcance);
    }
}
