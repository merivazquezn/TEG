package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListaJugadoresTest {

    @Test
    public void test01laListaDeJugadoresDevuelveLosJugadoresEnElOrdenCorrecto(){
        ArrayList<Jugador> arrayJugadores = new ArrayList<Jugador>();
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Jugador otroJugadorMas = new Jugador();
        arrayJugadores.add(unJugador);
        arrayJugadores.add(otroJugador);
        arrayJugadores.add(otroJugadorMas);
        ListaJugadores lista = new ListaJugadores(arrayJugadores);
        Jugador jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(unJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugadorMas));
    }

    @Test
    public void test02laListaDeJugadoresDevuelveLaCantidadCorrectaDeJugadores(){
        ArrayList<Jugador> arrayJugadores = new ArrayList<Jugador>();
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Jugador otroJugadorMas = new Jugador();
        arrayJugadores.add(unJugador);
        arrayJugadores.add(otroJugador);
        arrayJugadores.add(otroJugadorMas);
        ListaJugadores lista = new ListaJugadores(arrayJugadores);
        assertEquals(3, lista.size());
    }

    @Test
    public void test03SeLePideReiniciarALaListaYVuelveAlPrimerJugador(){
        ArrayList<Jugador> arrayJugadores = new ArrayList<Jugador>();
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Jugador otroJugadorMas = new Jugador();
        arrayJugadores.add(unJugador);
        arrayJugadores.add(otroJugador);
        arrayJugadores.add(otroJugadorMas);
        ListaJugadores lista = new ListaJugadores(arrayJugadores);
        Jugador jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(unJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugador));
        lista.reiniciar();
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(unJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugador));
    }

}
