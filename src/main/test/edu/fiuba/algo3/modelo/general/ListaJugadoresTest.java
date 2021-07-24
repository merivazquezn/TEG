package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListaJugadoresTest {

    @Test
    public void test01laListaDeJugadoresDevuelveLosJugadoresEnElOrdenCorrecto(){
        Jugador.reiniciarClase();
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(2))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(3, randomMock);
        Jugador jugadorActual = lista.siguiente();
        assertEquals(1, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(3, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(2, jugadorActual.getNumero());
    }

    @Test
    public void test02laListaDeJugadoresDevuelveLaCantidadCorrectaDeJugadores(){
        Jugador.reiniciarClase();
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(2))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(3, randomMock);
        assertEquals(3, lista.size());
    }

    @Test
    public void test03SeLePideReiniciarALaListaYVuelveAlPrimerJugador(){
        Jugador.reiniciarClase();
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(2))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(3, randomMock);
        Jugador jugadorActual = lista.siguiente();
        assertEquals(1, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(3, jugadorActual.getNumero());
        lista.reiniciar();
        jugadorActual = lista.siguiente();
        assertEquals(1, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(3, jugadorActual.getNumero());
    }

    @Test
    public void test04seUtilizaUnJugadorInicialDistintoYLaListaLosDevuelveEnElOrdenEsperado(){
        Jugador.reiniciarClase();
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(2))).thenReturn(1);
        ListaJugadores lista = new ListaJugadores(3, randomMock);
        Jugador jugadorActual = lista.siguiente();
        assertEquals(2, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(1, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(3, jugadorActual.getNumero());
    }

}
