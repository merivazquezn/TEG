package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Pais {

    private String nombre;
    private LinkedList<Ejercito> ejercitos;
    private Jugador jugador;
    private ArrayList<Pais> limitrofes;

    public Pais(String nombrePais) {
        this.nombre = nombrePais;
        this.ejercitos = new LinkedList<Ejercito>();
        this.jugador = new JugadorNulo();
        this.limitrofes = new ArrayList<Pais>();
    }


    public void colocarEjercitos(int cantidadEjercitos) {
        for(int i = 0; i < cantidadEjercitos; i++) {
            this.ejercitos.push(new Ejercito());
        }
    }

    public int getCantidadEjercitos() {
        return this.ejercitos.size();
    }

    public int eliminarEjercitos(int cantidadEjercitosEliminar) {
        if(this.getCantidadEjercitos() < cantidadEjercitosEliminar) {
            throw new CantidadInvalidaDeEjercitosException();
        }
        for(int i = 0; i < cantidadEjercitosEliminar; i++) {
            this.ejercitos.pop();
        }
        return this.ejercitos.size();
    }

    public void transferirEjercitosA(Pais destino, int cantidad) {
        Jugador propietarioDestino = destino.getJugador();
        if(!this.jugador.equals(propietarioDestino))
            throw new MovimientoDeEjercitosInvalidoException();
        if (this.getCantidadEjercitos() <= cantidad)
            throw new CantidadInvalidaDeEjercitosException();

        this.eliminarEjercitos(cantidad);
        destino.colocarEjercitos(cantidad);
    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;
        this.jugador.asignarPais(this);
    }

    public boolean esDeJugador(Jugador unJugador) {
        return this.jugador.equals(unJugador);
    }

    public Jugador getJugador() { return this.jugador; }

    public void agregarLimitrofe(Pais pais) {
        this.limitrofes.add(pais);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void serConquistadoPor(Jugador jugadorConquistador){
        this.jugador.perdioPaisAnte(this, jugadorConquistador);
        this.asignarJugador(jugadorConquistador);
    }
}

