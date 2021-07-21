package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.jugador.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObjetivoTest {

    @Test
    public void test01unJugadorCon30PaisesCumpleElObjetivoGeneral(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(unJugador);
        listaJugadores.add(otroJugador);
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(30);
        assertTrue(objetivo.haGanado(tablero, listaJugadores));
    }

    @Test
    public void test02unJugadorCon29PaisesNoCumpleElObjetivoGeneral(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(unJugador);
        listaJugadores.add(otroJugador);
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(29);
        assertFalse(objetivo.haGanado(tablero, listaJugadores));
    }

    @Test
    public void test03unObjetivoDe3PaisesEnCadaContienteEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(unJugador);
        listaJugadores.add(otroJugador);
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(29);
        assertFalse(objetivo.haGanado(tablero, listaJugadores));
    }

}
