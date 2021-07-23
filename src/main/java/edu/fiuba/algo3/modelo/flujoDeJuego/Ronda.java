package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.Tablero;

public class Ronda {

    private Tablero tablero;
    private ListaJugadores listaJugadores;

    public Ronda(Tablero tablero, ListaJugadores listaJugadores){
        this.tablero = tablero;
        this.listaJugadores = listaJugadores;
    }

    public void jugadorDebeReagrupar(){

    }

    public void realizarJugada(Jugada jugada){
        jugada.ejecutar(this.tablero, this);
    }
}
