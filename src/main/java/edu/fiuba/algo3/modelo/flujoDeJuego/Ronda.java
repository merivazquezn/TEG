package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Ronda {

    private Tablero tablero;
    private ListaJugadores listaJugadores;
    private Jugador jugadorActual;
    private Fase faseActual;

    public Ronda(Tablero tablero, ListaJugadores listaJugadores){
        this.tablero = tablero;
        this.listaJugadores = listaJugadores;
        this.jugadorActual = this.listaJugadores.siguiente();
    }

    public void seProdujoConquista(){

    }

    public void seRealizoCanje(){

    }

    public Fase obtenerFaseActual(){
        return this.faseActual;
    }

    public Jugador siguienteJugador(){
        this.jugadorActual = this.listaJugadores.siguiente();

        return this.jugadorActual;
    }

    public void reiniciarLista(){
        this.listaJugadores.reiniciar();
        this.jugadorActual = this.listaJugadores.siguiente();
    }

    public void realizarJugada(Jugada jugada){
        jugada.ejecutar(this.tablero, this);
    }

    public Jugador jugadorActual () { return this.jugadorActual; }
}
