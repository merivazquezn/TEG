package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.general.ConjuntoTarjetas;
import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.Observable;

public class Ronda extends Observable {

    private Tablero tablero;
    private ListaJugadores listaJugadores;
    private Jugador jugadorActual;
    private Fase faseActual;
    private int cantidadAColocar;

    private Pais conquistador;
    private Pais conquistado;

    boolean seProdujoConquista;
    boolean sePuedeReagrupar;


    public Ronda(Tablero tablero, ListaJugadores listaJugadores){
        this.tablero = tablero;
        this.listaJugadores = listaJugadores;
        this.jugadorActual = this.listaJugadores.siguiente();
        this.faseActual = new FaseInicial5Fichas();
        this.cantidadAColocar = 5;
        this.seProdujoConquista = false;
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
        this.seProdujoConquista = false;
        this.sePuedeReagrupar = false;
        this.faseActual = this.faseActual.siguienteFase(this);
        setChanged();
    }

    public void seColocaronEjercitos(int cantidad){
        this.cantidadAColocar -= cantidad;
        if(this.cantidadAColocar <= 0)
            this.faseActual = this.faseActual.siguienteFase(this);
        setChanged();
    }

    public void producirConquista(){
        if (this.listaJugadores.hayGanador(this.tablero))
            this.faseActual = new JuegoTerminado();
        else this.faseActual = new FaseReagruparPorConquista();
        this.seProdujoConquista = true;
        setChanged();
    }

    public void seRealizoCanje(ConjuntoTarjetas conjuntoTarjetas){
        this.jugadorActual.realizarCanje(conjuntoTarjetas);
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

    public boolean puedeColocar(){
        return this.cantidadAColocar > 0;
    }

    public boolean seProdujoConquista(){
        return this.seProdujoConquista;
    }

    public boolean sePuedeReagrupar(){
        return this.sePuedeReagrupar;
    }

    public void puedeReagrupar(){
        this.sePuedeReagrupar = true;
    }

    public void siguienteJugador() {
        this.jugadorActual = this.listaJugadores.siguiente();

    }

    public void setConquistador(Pais pais) {
        this.conquistador = pais;
    }

    public void setConquistado(Pais pais) {
        this.conquistado = pais;
    }

    public Pais getConquistador() {
        return this.conquistador;
    }
    public Pais getConquistado() {
        return this.conquistado;
    }

    public void habilitarTarjetaPorConquista() {
        this.jugadorActual.habilitarTarjetaPorConquista(this.tablero.obtenerMazo());
        this.jugadorActual.reiniciarEstadoConquista();
    }
    public Jugador jugadorGanador(){
        return this.listaJugadores.jugadorGanador(this.tablero);
    }
    public boolean juegoTerminado(){
        return this.listaJugadores.hayGanador(this.tablero);
    }
}
