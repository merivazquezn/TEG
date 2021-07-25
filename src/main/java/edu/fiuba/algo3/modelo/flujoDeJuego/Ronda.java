package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;

public class Ronda {

    private Tablero tablero;
    private ListaJugadores listaJugadores;
    private Jugador jugadorActual;
    private Fase faseActual;
    private int cantidadAColocar;

    public Ronda(Tablero tablero, ListaJugadores listaJugadores){
        this.tablero = tablero;
        this.listaJugadores = listaJugadores;
        this.jugadorActual = this.listaJugadores.siguiente();
        this.faseActual = new FaseInicial5Fichas();
        this.cantidadAColocar = 5;
    }

    public int cantidadAColocar(){
        return this.cantidadAColocar;
    }

    public void establecerCantidadAColocar(int cantidad){
        this.cantidadAColocar = cantidad;
    }

    public void actualizarCantidadAColocar(){
        this.cantidadAColocar = 0;
        this.cantidadAColocar += this.jugadorActual.cantidadPaises()/2;
        this.cantidadAColocar += this.tablero.cantidadEjercitosPorContinente(this.jugadorActual);
    }

    public void terminar(){
        this.faseActual = this.faseActual.siguienteFase(this);
    }

    public void seColocaronEjercitos(int cantidad){
        this.cantidadAColocar -= cantidad;
        if(this.cantidadAColocar <= 0)
            this.faseActual = this.faseActual.siguienteFase(this);
    }

    public void seProdujoConquista(){
        this.faseActual = new FaseReagruparPorConquista();
    }

    public void seRealizoCanje(){
        this.jugadorActual.realizarCanje();
        this.cantidadAColocar += this.jugadorActual.cantidadAColocarPorCanje();
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
