package edu.fiuba.algo3.modelo;

import static org.mockito.Mockito.*;

import edu.fiuba.algo3.modelo.general.Continente;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {


    @Test
    public void test01CantidadEjercitosPorContinenteDevuelve0SiJugadorNoConquistoNingunContinente() {


        ArrayList<Continente> continentes = new ArrayList<Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes);

        Continente americaDelSur = mock(Continente.class);
        Continente americaDelNorte = mock(Continente.class);
        Continente europa = mock(Continente.class);
        Continente asia = mock(Continente.class);
        Continente africa = mock(Continente.class);
        Continente oceania = mock(Continente.class);

        when(americaDelSur.ejercitosParaJugador(jugador)).thenReturn(0);
        when(americaDelNorte.ejercitosParaJugador(jugador)).thenReturn(0);
        when(europa.ejercitosParaJugador(jugador)).thenReturn(0);
        when(asia.ejercitosParaJugador(jugador)).thenReturn(0);
        when(africa.ejercitosParaJugador(jugador)).thenReturn(0);
        when(oceania.ejercitosParaJugador(jugador)).thenReturn(0);

        continentes.add(americaDelNorte);
        continentes.add(americaDelSur);
        continentes.add(africa);
        continentes.add(europa);
        continentes.add(oceania);
        continentes.add(asia);

        assertEquals(tablero.cantidadEjercitosPorContinente(jugador), 0);

    }

    @Test
    public void test02CantidadEjercitosPorContinenteDevuelve7SiJugadorConquistoSoloAsia() {


        ArrayList<Continente> continentes = new ArrayList<Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes);

        Continente americaDelSur = mock(Continente.class);
        Continente americaDelNorte = mock(Continente.class);
        Continente europa = mock(Continente.class);
        Continente asia = mock(Continente.class);
        Continente africa = mock(Continente.class);
        Continente oceania = mock(Continente.class);

        when(americaDelSur.ejercitosParaJugador(jugador)).thenReturn(0);
        when(americaDelNorte.ejercitosParaJugador(jugador)).thenReturn(0);
        when(europa.ejercitosParaJugador(jugador)).thenReturn(0);
        when(asia.ejercitosParaJugador(jugador)).thenReturn(7);
        when(africa.ejercitosParaJugador(jugador)).thenReturn(0);
        when(oceania.ejercitosParaJugador(jugador)).thenReturn(0);

        continentes.add(americaDelNorte);
        continentes.add(americaDelSur);
        continentes.add(africa);
        continentes.add(europa);
        continentes.add(oceania);
        continentes.add(asia);

        assertEquals(tablero.cantidadEjercitosPorContinente(jugador), 7);

    }

    @Test
    public void test03CantidadEjercitosPorContinenteDevuelve5SiJugadorConquistoOceaniaYAfrica() {


        ArrayList<Continente> continentes = new ArrayList<Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes);

        Continente americaDelSur = mock(Continente.class);
        Continente americaDelNorte = mock(Continente.class);
        Continente europa = mock(Continente.class);
        Continente asia = mock(Continente.class);
        Continente africa = mock(Continente.class);
        Continente oceania = mock(Continente.class);

        when(americaDelSur.ejercitosParaJugador(jugador)).thenReturn(0);
        when(americaDelNorte.ejercitosParaJugador(jugador)).thenReturn(0);
        when(europa.ejercitosParaJugador(jugador)).thenReturn(0);
        when(asia.ejercitosParaJugador(jugador)).thenReturn(0);
        when(africa.ejercitosParaJugador(jugador)).thenReturn(3);
        when(oceania.ejercitosParaJugador(jugador)).thenReturn(2);

        continentes.add(americaDelNorte);
        continentes.add(americaDelSur);
        continentes.add(africa);
        continentes.add(europa);
        continentes.add(oceania);
        continentes.add(asia);

        assertEquals(tablero.cantidadEjercitosPorContinente(jugador), 5);

    }

    @Test
    public void test04CantidadEjercitosPorContinenteDevuelve25SiJugadorConquistoTodosLosContinentes() {


        ArrayList<Continente> continentes = new ArrayList<Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes);

        Continente americaDelSur = mock(Continente.class);
        Continente americaDelNorte = mock(Continente.class);
        Continente europa = mock(Continente.class);
        Continente asia = mock(Continente.class);
        Continente africa = mock(Continente.class);
        Continente oceania = mock(Continente.class);

        when(americaDelSur.ejercitosParaJugador(jugador)).thenReturn(3);
        when(americaDelNorte.ejercitosParaJugador(jugador)).thenReturn(5);
        when(europa.ejercitosParaJugador(jugador)).thenReturn(5);
        when(asia.ejercitosParaJugador(jugador)).thenReturn(7);
        when(africa.ejercitosParaJugador(jugador)).thenReturn(3);
        when(oceania.ejercitosParaJugador(jugador)).thenReturn(2);

        continentes.add(americaDelNorte);
        continentes.add(americaDelSur);
        continentes.add(africa);
        continentes.add(europa);
        continentes.add(oceania);
        continentes.add(asia);

        assertEquals(tablero.cantidadEjercitosPorContinente(jugador), 25);

    }
}
