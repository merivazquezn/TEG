package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.JugadorNulo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FaseTest {

    @Test
    public void test01faseInicial5FichasSeDevuelveASiMismaSiNoLlegoAlFinal(){
        Fase fase = new FaseInicial5Fichas();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseInicial5Fichas);
    }

    @Test
    public void test02FaseInicial3FichasSeDevuelveASiMismaSiNoLlegoAlFinal() {
        Fase fase = new FaseInicial3Fichas();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseInicial3Fichas);
    }

    @Test
    public void test03FaseInicial3FichasDevuelveFaseAtaqueSiEstaAlFinalDeLaListaJugadores() {
        Fase fase = new FaseInicial3Fichas();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador());
        listaJugadores.siguiente();
        listaJugadores.siguiente();
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseAtaque);
    }

    @Test
    public void test04FaseReagruparPorConquistaDevuelveFaseAtaqueSiNoEstaAlFinal() {
        Fase fase = new FaseReagruparPorConquista();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(4, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseAtaque);
    }

    @Test
    public void test05FaseReagruparPorConquistaDevuelveFaseAtaqueSiEstaAlFinal() {
        Fase fase = new FaseReagruparPorConquista();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador());
        listaJugadores.siguiente();
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseAtaque);
    }

    @Test
    public void test06FaseJuegoTerminadoSeDevuelveASiMisma() {
        Fase fase = new JuegoTerminado();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(6, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof JuegoTerminado);
    }

    @Test
    public void test07FaseReagruparFinalDevuelveFaseAtaqueSiNoEstaAlFinalDeLaLista(){
        Fase fase = new FaseReagruparFinal();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseAtaque);
    }

    @Test
    public void test08FaseColocacionSeDevuelveASiMismaSiNoEstaAlFinalDeLaLista() {
        Fase fase = new FaseColocacion();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(6, new Randomizador());
        listaJugadores.siguiente();
        listaJugadores.siguiente();
        listaJugadores.siguiente();
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseColocacion);
    }

    @Test
    public void test09FaseColocacionDevuelveFaseAtaqueSiEstaAlFinalDeLaLista() {
        Fase fase = new FaseColocacion();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador());
        listaJugadores.siguiente();
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseAtaque);
    }

    @Test
    public void test10FaseReagruparFinalDevuelveFaseColocacionSiEstaAlFinalDeLaLista(){
        Fase fase = new FaseReagruparFinal();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador());
        listaJugadores.siguiente();
        listaJugadores.siguiente();
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseColocacion);
    }

    @Test
    public void test11faseInicial5FichasDevuelveFaseInicial3FichasSiLlegoAlFinalDeLaLista(){
        Fase fase = new FaseInicial5Fichas();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador());
        listaJugadores.siguiente();
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase siguienteFase = fase.siguienteFase(ronda);
        assertTrue(siguienteFase instanceof FaseInicial3Fichas);
    }

    @Test
    public void test12faseInicial5FichasReiniciaLaListaSiLlegoAlFinalDeLaLista(){
        Fase fase = new FaseInicial5Fichas();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = mock(ListaJugadores.class);
        when(listaJugadores.siguiente()).thenReturn(new JugadorNulo());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        fase.siguienteFase(ronda);
        verify(listaJugadores, times(1)).reiniciar();
    }

    /*@Test
    public void test13laRondaCambiaSuEstadoAFaseReagruparPorConquistaCuandoSeProduceConquista(){
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.seProdujoConquista();
        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseReagruparPorConquista);
    }

    @Test
    public void test14unaRondaSeCreaConFaseInicial5Fichas(){
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseInicial5Fichas);
    }*/
}
