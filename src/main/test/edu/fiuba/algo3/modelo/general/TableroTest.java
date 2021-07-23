package edu.fiuba.algo3.modelo.general;

import static org.mockito.Mockito.*;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.Continente;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {


    @Test
    public void test01CantidadEjercitosPorContinenteDevuelve0SiJugadorNoConquistoNingunContinente() {

        HashMap<String, Continente> continentes = new HashMap<String, Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()));

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

        continentes.put("America del Norte",americaDelNorte);
        continentes.put("America del Sur", americaDelSur);
        continentes.put("Africa", africa);
        continentes.put("Europa", europa);
        continentes.put("Oceania", oceania);
        continentes.put("Asia", asia);

        assertEquals(tablero.cantidadEjercitosPorContinente(jugador), 0);

    }

    @Test
    public void test02CantidadEjercitosPorContinenteDevuelve7SiJugadorConquistoSoloAsia() {


        HashMap<String, Continente> continentes = new HashMap<String, Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()));

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

        continentes.put("America del Norte",americaDelNorte);
        continentes.put("America del Sur", americaDelSur);
        continentes.put("Africa", africa);
        continentes.put("Europa", europa);
        continentes.put("Oceania", oceania);
        continentes.put("Asia", asia);

        assertEquals(tablero.cantidadEjercitosPorContinente(jugador), 7);

    }

    @Test
    public void test03CantidadEjercitosPorContinenteDevuelve5SiJugadorConquistoOceaniaYAfrica() {


        HashMap<String, Continente> continentes = new HashMap<String, Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()));

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

        continentes.put("America del Norte",americaDelNorte);
        continentes.put("America del Sur", americaDelSur);
        continentes.put("Africa", africa);
        continentes.put("Europa", europa);
        continentes.put("Oceania", oceania);
        continentes.put("Asia", asia);

        assertEquals(tablero.cantidadEjercitosPorContinente(jugador), 5);

    }

    @Test
    public void test04CantidadEjercitosPorContinenteDevuelve25SiJugadorConquistoTodosLosContinentes() {

        HashMap<String, Continente> continentes = new HashMap<String, Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()));

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

        continentes.put("America del Norte",americaDelNorte);
        continentes.put("America del Sur", americaDelSur);
        continentes.put("Africa", africa);
        continentes.put("Europa", europa);
        continentes.put("Oceania", oceania);
        continentes.put("Asia", asia);

        assertEquals(25, tablero.cantidadEjercitosPorContinente(jugador));

    }

    @Test
    public void test05CantidadDePaisesEnAsiaDebeSer5(){
        HashMap<String, Continente> continentes = new HashMap<String, Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()));

        Continente americaDelSur = mock(Continente.class);
        Continente americaDelNorte = mock(Continente.class);
        Continente europa = mock(Continente.class);
        Continente asia = mock(Continente.class);
        Continente africa = mock(Continente.class);
        Continente oceania = mock(Continente.class);

        when(asia.cantidadPaisesDeJugador(jugador)).thenReturn(5);
        when(europa.cantidadPaisesDeJugador(jugador)).thenReturn(6);
        when(africa.cantidadPaisesDeJugador(jugador)).thenReturn(7);

        continentes.put("America Del Norte", americaDelNorte);
        continentes.put("America Del Sur", americaDelSur);
        continentes.put("Africa", africa);
        continentes.put("Europa", europa);
        continentes.put("Oceania", oceania);
        continentes.put("Asia", asia);

        assertEquals(5, tablero.cantidadPaisesDeJugadorEn(jugador, "Asia"));

    }

    @Test
    public void test06CantidadDePaisesEnEuropaDebeSer9(){
        HashMap<String, Continente> continentes = new HashMap<String, Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()));

        Continente americaDelSur = mock(Continente.class);
        Continente americaDelNorte = mock(Continente.class);
        Continente europa = mock(Continente.class);
        Continente asia = mock(Continente.class);
        Continente africa = mock(Continente.class);
        Continente oceania = mock(Continente.class);

        when(asia.cantidadPaisesDeJugador(jugador)).thenReturn(5);
        when(europa.cantidadPaisesDeJugador(jugador)).thenReturn(9);
        when(africa.cantidadPaisesDeJugador(jugador)).thenReturn(7);

        continentes.put("America Del Norte", americaDelNorte);
        continentes.put("America Del Sur", americaDelSur);
        continentes.put("Africa", africa);
        continentes.put("Europa", europa);
        continentes.put("Oceania", oceania);
        continentes.put("Asia", asia);

        assertEquals(9, tablero.cantidadPaisesDeJugadorEn(jugador, "Europa"));

    }

    @Test
    public void test07EuropaDeberiaEstarOcupadaPorUnJugador(){
        HashMap<String, Continente> continentes = new HashMap<String, Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()));

        Continente americaDelSur = mock(Continente.class);
        Continente americaDelNorte = mock(Continente.class);
        Continente europa = mock(Continente.class);
        Continente asia = mock(Continente.class);
        Continente africa = mock(Continente.class);
        Continente oceania = mock(Continente.class);

        when(europa.ocupadoPorJugador(jugador)).thenReturn(true);

        continentes.put("America Del Norte", americaDelNorte);
        continentes.put("America Del Sur", americaDelSur);
        continentes.put("Africa", africa);
        continentes.put("Europa", europa);
        continentes.put("Oceania", oceania);
        continentes.put("Asia", asia);

        assertTrue(tablero.continenteOcupadoPorJugador(jugador, "Europa"));

    }

    @Test
    public void test07AsiaNoDeberiaEstarOcupadaPorUnJugador(){
        HashMap<String, Continente> continentes = new HashMap<String, Continente>();
        Jugador jugador = new Jugador();

        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()));

        Continente americaDelSur = mock(Continente.class);
        Continente americaDelNorte = mock(Continente.class);
        Continente europa = mock(Continente.class);
        Continente asia = mock(Continente.class);
        Continente africa = mock(Continente.class);
        Continente oceania = mock(Continente.class);

        when(asia.ocupadoPorJugador(jugador)).thenReturn(false);

        continentes.put("America Del Norte", americaDelNorte);
        continentes.put("America Del Sur", americaDelSur);
        continentes.put("Africa", africa);
        continentes.put("Europa", europa);
        continentes.put("Oceania", oceania);
        continentes.put("Asia", asia);

        assertFalse(tablero.continenteOcupadoPorJugador(jugador, "Asia"));

    }

}
