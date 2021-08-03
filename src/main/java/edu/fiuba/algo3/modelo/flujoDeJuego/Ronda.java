package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.beans.InvalidationListener;
import java.util.Observable;

public class Ronda extends Observable {

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
        setChanged();
    }

    public void terminar() throws NoSePuedeAvanzarFaseException{
        this.faseActual = this.faseActual.siguienteFase(this);
        setChanged();
    }

    public void seColocaronEjercitos(int cantidad){
        this.cantidadAColocar -= cantidad;
        if(this.cantidadAColocar <= 0)
            this.faseActual = this.faseActual.siguienteFase(this);
        setChanged();
    }

    public void seProdujoConquista(){
        if (this.listaJugadores.hayGanador(this.tablero))
            this.faseActual = new JuegoTerminado();
        else this.faseActual = new FaseReagruparPorConquista();
        setChanged();
    }

    public void seRealizoCanje(){
        this.jugadorActual.realizarCanje();
        this.cantidadAColocar += this.jugadorActual.cantidadAColocarPorCanje();
        setChanged();
    }

    public Fase obtenerFaseActual(){
        return this.faseActual;
    }

    public void reiniciarLista(){
        this.listaJugadores.reiniciar();
        this.jugadorActual = this.listaJugadores.siguiente();
        setChanged();
    }

    public boolean estaAlFinalDeLaLista(){
        return this.listaJugadores.estaAlFinalDeLaLista();
    }

    public void realizarJugada(Jugada jugada){
        jugada.ejecutar(this.tablero, this);
        setChanged();
    }

    public Jugador jugadorActual () { return this.jugadorActual; }

    public String getNombreRonda() {
        return this.faseActual.getNombreRonda();
    }

    public String accionARealizar(){
        return this.faseActual.accionARealizar(this.cantidadAColocar);
    }

    public boolean puedeAvanzar() {
        return this.cantidadAColocar <= 0;
    }

    public void siguienteJugador() {
        this.jugadorActual = this.listaJugadores.siguiente();

    }

}
