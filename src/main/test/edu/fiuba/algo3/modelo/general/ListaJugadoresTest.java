package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.jugador.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ListaJugadoresTest {

    @Test
    public void test01laListaDeJugadoresDevuelveLosJugadoresEnElOrdenCorrecto(){
        Jugador.reiniciarClase();
        Randomizador randomMock = mock(Randomizador.class);
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        when(randomMock.generar(eq(0),eq(3))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(3, randomMock, listaObjetivos);
        Jugador jugadorActual = lista.siguiente();
        assertEquals(1, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(3, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(2, jugadorActual.getNumero());
    }

    @Test
    public void test02laListaDeJugadoresDevuelveLaCantidadCorrectaDeJugadores(){
        Randomizador randomMock = mock(Randomizador.class);
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        when(randomMock.generar(eq(0),eq(3))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(3, randomMock, listaObjetivos);
        assertEquals(3, lista.size());
    }

    @Test
    public void test03SeLePideReiniciarALaListaYVuelveAlPrimerJugador(){
        Jugador.reiniciarClase();
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(3))).thenReturn(0);
        ListaJugadores lista = new ListaJugadores(3, randomMock, listaObjetivos);
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

        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());

        Randomizador randomMock = mock(Randomizador.class);
        when(randomMock.generar(eq(0),eq(3))).thenReturn(1);

        ListaJugadores lista = new ListaJugadores(3, randomMock, listaObjetivos);
        Jugador jugadorActual = lista.siguiente();

        assertEquals(2, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(1, jugadorActual.getNumero());
        jugadorActual = lista.siguiente();
        assertEquals(3, jugadorActual.getNumero());
    }

    @Test
    public void test05siSeAvanzaMasDeLaCantidadDeJugadoresSeDevuelveJugadorNulo(){
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores lista = new ListaJugadores(3, new Randomizador(), listaObjetivos);
        lista.siguiente();
        lista.siguiente();
        lista.siguiente();
        Jugador jugadorActual = lista.siguiente();
        assertTrue(jugadorActual.esNulo());
    }

    @Test
    public void test06SeInicializaLaListaNoHayGanadores(){
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador(), listaObjetivos);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        assertFalse(listaJugadores.hayGanador(tablero));
    }

    @Test
    public void test07Jugador1GanaYEsElJugadorGanador(){
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        Tablero tablero = new Tablero(new HashMap<String, Continente>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<Tarjeta>(), new Randomizador()));

        ObjetivoGeneral objetivo = mock(ObjetivoGeneral.class);
        listaObjetivos.add(objetivo);
        listaObjetivos.add(objetivo);
        ListaJugadores lista = new ListaJugadores(2, new Randomizador(), listaObjetivos);

        when(objetivo.haGanado(tablero)).thenReturn(true);
        assertFalse(lista.jugadorGanador(tablero).esNulo());
    }



    @Test
    public void test08SeInicializaLaListaNoHayGanadoresElGanadorEsUnJugadorNulo(){
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador(), listaObjetivos);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        assertTrue(listaJugadores.jugadorGanador(tablero).esNulo());

    }
}
