package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.*;

import java.util.ArrayList;
import java.util.Observable;

public class Pais extends Observable{

    private final String nombre;
    private int ejercitos;
    private Jugador jugador;
    private final ArrayList<Pais> limitrofes;

    public Pais(String nombrePais) {
        this.nombre = nombrePais;
        this.ejercitos = 1;
        this.jugador = new JugadorNulo();
        this.limitrofes = new ArrayList<Pais>();
    }

    public void colocarEjercitos(int cantidadEjercitos) {
        this.ejercitos += cantidadEjercitos;
        setChanged();
    }

    public int getCantidadEjercitos() {
        return this.ejercitos;
    }

    public int eliminarEjercitos(int cantidadEjercitosEliminar) {
        if(this.ejercitos < cantidadEjercitosEliminar) {
            throw new CantidadInvalidaDeEjercitosException();
        }

        this.ejercitos -= cantidadEjercitosEliminar;
        setChanged();
        return this.ejercitos;
    }

    public void transferirEjercitosA(Pais destino, int cantidad) {
        Jugador propietarioDestino = destino.getJugador();
        if(!this.jugador.equals(propietarioDestino))
            throw new MovimientoDeEjercitosInvalidoException();
        if(!this.limitrofes.contains(destino))
            throw new MovimientoDeEjercitosInvalidoException();
        if (this.getCantidadEjercitos() <= cantidad)
            throw new CantidadInvalidaDeEjercitosException();

        this.eliminarEjercitos(cantidad);
        destino.colocarEjercitos(cantidad);
        setChanged();
    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;

        if (!jugador.tienePais(this))
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
        setChanged();
    }

    public boolean sonLimitrofes(Pais otroPais) {
        return limitrofes.contains(otroPais);
    }
}

