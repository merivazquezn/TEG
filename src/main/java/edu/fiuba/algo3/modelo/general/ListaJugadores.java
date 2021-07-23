package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.JugadorNulo;

import java.util.ArrayList;

public class ListaJugadores {

    ArrayList<Jugador> listaJugadores;
    int indiceActual;

    public ListaJugadores(ArrayList<Jugador> arrayJugadores){
        this.listaJugadores = arrayJugadores;
        this.indiceActual = 0;
    }

    public Jugador siguiente(){
        if(indiceActual > listaJugadores.size())
            return new JugadorNulo();
        Jugador jugadorActual = listaJugadores.get(indiceActual);
        indiceActual++;
        return jugadorActual;
    }

    public int size(){
        return listaJugadores.size();
    }

    public void reiniciar(){
        this.indiceActual = 0;
    }

}
