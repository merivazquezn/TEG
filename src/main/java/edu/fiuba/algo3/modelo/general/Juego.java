package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.ArrayList;

public class Juego {

    private ArrayList<Jugador> listaJugadores;

    public Juego(){
        this.listaJugadores = new ArrayList<>();
    }

    public void asignarJugadores (ArrayList<Jugador> jugadores){
        this.listaJugadores = jugadores;
    }

    public int cantidadJugadores(){
        return this.listaJugadores.size();
    }
}
