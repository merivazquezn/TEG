package edu.fiuba.algo3.modelo;

import java.util.LinkedList;

public class Pais {

    private String nombre;
    private LinkedList<Ejercito> ejercitos;

    public Pais(String nombrePais) {
        this.nombre = nombrePais;
        this.ejercitos = new LinkedList<Ejercito>();
    }

    public void colocarFichas(int cantidadFichas) {
        for(int i = 0; i < cantidadFichas; i++) {
            ejercitos.push(new Ejercito());
        }
    }

    public int getCantidadFichas() {
        return ejercitos.size();
    }

    public int eliminarFichas(int cantidadEjercitosEliminar) {
        if(this.getCantidadFichas() < cantidadEjercitosEliminar) {
            throw new EjercitosInsuficientesException();
        }
        for(int i = 0; i < cantidadEjercitosEliminar; i++) {
            ejercitos.pop();
        }
    }
}
