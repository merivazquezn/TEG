package edu.fiuba.algo3.modelo;

import java.util.LinkedList;

public class Pais {

    private String nombre;
    private LinkedList<Ejercito> ejercitos;

    public Pais(String nombrePais) {
        this.nombre = nombrePais;
        this.ejercitos = new LinkedList<Ejercito>();
    }

    public void colocarEjercitos(int cantidadEjercitos) {
        for(int i = 0; i < cantidadEjercitos; i++) {
            ejercitos.push(new Ejercito());
        }
    }

    public int getCantidadEjercitos() {
        return ejercitos.size();
    }

    public int eliminarEjercitos(int cantidadEjercitosEliminar) {
        if(this.getCantidadEjercitos() < cantidadEjercitosEliminar) {
            throw new EjercitosInsuficientesException();
        }
        for(int i = 0; i < cantidadEjercitosEliminar; i++) {
            ejercitos.pop();
        }
        return ejercitos.size();
    }

    public void transferirEjercitosA(Pais destino, int cantidad) {
        //TODO: comprobar que sean del mismo jugador
        if (this.getCantidadEjercitos() <= cantidad)
            throw new EjercitosInsuficientesException();

        this.eliminarEjercitos(cantidad);
        destino.colocarEjercitos(cantidad);
    }
}
