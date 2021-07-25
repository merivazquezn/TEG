package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RondaTest {

    @Test
    public void test01LaRondaRecibeUnaJugadaYLaEjecuta(){
        Jugada jugada = mock(JugadaAtacar.class);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.realizarJugada(jugada);
        verify(jugada, times(1)).ejecutar(tablero, ronda);
    }

    @Test
    public void test02RondaCon3JugadoresRecibeUnaJugadaColocarDondeJugador2ControlaAsiaYLaEjecuta(){
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador());
        Jugador jugador1 = listaJugadores.siguiente();
        Jugador jugador2 = listaJugadores.siguiente();
        Jugador jugador3 = listaJugadores.siguiente();

        Pais pais1 = new Pais("china");
        pais1.colocarEjercitos(2);
        Pais pais2 = new Pais("vietnam");
        pais2.colocarEjercitos(1);

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
        Tablero tablero = new Tablero(continentes, mock(ConstructorDeConjuntoDados.class));


        JugadaColocar jugada = new JugadaColocar(pais2, 2);
        Ronda rondaColocacion = new Ronda(tablero, listaJugadores);
        rondaColocacion.realizarJugada(jugada);
        assertTrue(tablero.continenteOcupadoPorJugador(jugador2, "Asia"));
        assertEquals(pais2.getCantidadEjercitos(), 3);

    }
}
