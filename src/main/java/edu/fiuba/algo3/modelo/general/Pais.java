package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.*;

import java.util.LinkedList;

public class Pais {

    private String nombre;
    private LinkedList<Ejercito> ejercitos;
    private Jugador jugador;


    public Pais(String nombrePais) {
        this.nombre = nombrePais;
        this.ejercitos = new LinkedList<Ejercito>();
        this.jugador = new JugadorNulo();
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
            throw new CantidadInvalidaDeEjercitosException();
        }
        for(int i = 0; i < cantidadEjercitosEliminar; i++) {
            ejercitos.pop();
        }
        return ejercitos.size();
    }

    public void transferirEjercitosA(Pais destino, int cantidad) {
        //TODO: comprobar que sean del mismo jugador
        if (this.getCantidadEjercitos() <= cantidad)
            throw new CantidadInvalidaDeEjercitosException();

        this.eliminarEjercitos(cantidad);
        destino.colocarEjercitos(cantidad);
    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean esDeJugador(Jugador unJugador) {
        return this.jugador.equals(unJugador);
    }

    public Jugador getJugador() { return this.jugador; }



}
