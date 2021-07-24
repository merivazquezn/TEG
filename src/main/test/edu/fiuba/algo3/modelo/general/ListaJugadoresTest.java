package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(2))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(arrayJugadores, randomMock);
        Jugador jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(unJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugadorMas));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugador));
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
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(2))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(arrayJugadores, randomMock);
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
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(2))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(arrayJugadores, randomMock);
        Jugador jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(unJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugadorMas));
        lista.reiniciar();
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(unJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugadorMas));
    }

    @Test
    public void test04seUtilizaUnJugadorInicialDistintoYLaListaLosDevuelveEnElOrdenEsperado(){
        ArrayList<Jugador> arrayJugadores = new ArrayList<Jugador>();
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Jugador otroJugadorMas = new Jugador();
        arrayJugadores.add(unJugador);
        arrayJugadores.add(otroJugador);
        arrayJugadores.add(otroJugadorMas);
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(2))).thenReturn(1);
        ListaJugadores lista = new ListaJugadores(arrayJugadores, randomMock);
        Jugador jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(unJugador));
        jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.equals(otroJugadorMas));
    }

}
