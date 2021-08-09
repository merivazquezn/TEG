package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.Objetivo;
import edu.fiuba.algo3.modelo.jugador.ObjetivoDestruir;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RondaTest {

    @Test
    public void test01LaRondaRecibeUnaJugadaYLaEjecuta(){
        Jugada jugada = mock(JugadaAtacar.class);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador(), listaObjetivos);
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.realizarJugada(jugada);
        verify(jugada, times(1)).ejecutar(tablero, ronda);
    }

    @Test
    public void test02RondaCon3JugadoresRecibeUnaJugadaColocarDondeJugador2ControlaAsiaYLaEjecuta(){
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador(), listaObjetivos);
        Jugador jugador1 = listaJugadores.siguiente();
        Jugador jugador2 = listaJugadores.siguiente();
        Jugador jugador3 = listaJugadores.siguiente();

        Pais pais1 = new Pais("China");
        pais1.colocarEjercitos(1);
        Pais pais2 = new Pais("Vietnam");

        jugador2.asignarPais(pais1);
        jugador2.asignarPais(pais2);
        ArrayList<Pais> listaPaisesAsia = new ArrayList<Pais>();
        listaPaisesAsia.add(pais1);
        listaPaisesAsia.add(pais2);
        pais1.asignarJugador(jugador2);
        pais2.asignarJugador(jugador2);
        HashMap<String, Continente> continentes = new HashMap<>();
        Continente asia = new Continente(listaPaisesAsia, "Asia", 7);

        continentes.put("Asia", asia);
        Tablero tablero = new Tablero(continentes, mock(ConstructorDeConjuntoDados.class), new Mazo(new ArrayList<>(), new Randomizador()));


        JugadaColocar jugada = new JugadaColocar(pais2, 2);
        Ronda rondaColocacion = new Ronda(tablero, listaJugadores);
        rondaColocacion.realizarJugada(jugada);
        assertTrue(tablero.continenteOcupadoPorJugador(jugador2, "Asia"));
        assertEquals(pais2.getCantidadEjercitos(), 3);

    }

    @Test
    public void test03GettersDevuelvenCorrectamente() {
        Jugador.reiniciarClase();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();

        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());

        Randomizador randomizador = mock(Randomizador.class);

        ListaJugadores listaJugadores = new ListaJugadores(3, randomizador, listaObjetivos);
        Ronda ronda = new Ronda(tablero, listaJugadores);

        assertEquals(1, ronda.jugadorActual().getNumero());

        assertEquals("Fase Inicial", ronda.getNombreRonda());

        assertEquals("Ejercitos a colocar: 5", ronda.accionARealizar());

        assertEquals(true, ronda.puedeColocar());

        assertEquals(false, ronda.seProdujoConquista());

        assertEquals(false, ronda.sePuedeReagrupar());

        assertEquals(null, ronda.getConquistador());

        assertEquals(null, ronda.getConquistado());

        assertTrue(ronda.jugadorGanador().esNulo());

        assertFalse(ronda.juegoTerminado());
    }





}
