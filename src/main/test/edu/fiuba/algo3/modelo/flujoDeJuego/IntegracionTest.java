package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Parser;
import edu.fiuba.algo3.infraestructura.Randomizador;

import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.jugador.*;
import edu.fiuba.algo3.modelo.general.*;


import org.junit.jupiter.api.Test;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class IntegracionTest {

    @Test
    public void test01seFinalizaLaRondaInicial5FichasYLaRondaPasaAFaseInicial3Fichas(){
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutaTarjetas = "./src/data/cartas.csv";

        Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
        HashMap<String, Continente> continentes = parser.getContinentes();
        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseInicial3Fichas);
    }

    @Test
    public void test02seFinalizaLaRondaInicial3FichasYLaRondaPasaAFaseAtaque(){
        HashMap<String, Continente> continentes;
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutaTarjetas = "./src/data/cartas.csv";

        Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
        continentes = parser.getContinentes();
        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
        Ronda ronda = new Ronda(tablero, listaJugadores);

        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();

        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseAtaque);
    }

    @Test
    public void test03seFinalizaLaPrimeraRondaAtaquePorCadaUnaSePasaAReagruparFinal(){
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutaTarjetas = "./src/data/cartas.csv";

        Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
        HashMap<String, Continente> continentes = parser.getContinentes();
        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.terminar();
        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseReagruparFinal);
        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseAtaque);
        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseReagruparFinal);
    }

    @Test
    public void test04seFinalizaLaPrimeraRondaAtaquePasaAFaseColocacion(){
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutaTarjetas = "./src/data/cartas.csv";

        Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
        HashMap<String, Continente> continentes = parser.getContinentes();
        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.terminar();
        ronda.terminar();
        ronda.terminar();
        ronda.terminar();
        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseColocacion);
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseColocacion);
    }


    @Test
    public void test05seFinalizaLaPrimeraRondaColocacionPasaAFaseAtaque(){
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutaTarjetas = "./src/data/cartas.csv";

        Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
        HashMap<String, Continente> continentes = parser.getContinentes();
        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.terminar();
        ronda.terminar();
        ronda.terminar();
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseAtaque);
    }


    @Test
    public void test06enFaseAtaqueSeConquistaRondaPasaAFaseReagrupePorConquista(){
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutaTarjetas = "./src/data/cartas.csv";

        Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
        HashMap<String, Continente> continentes = parser.getContinentes();
        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador(), listaObjetivos);
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.terminar();
        ronda.terminar();
        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseAtaque);
        ronda.producirConquista();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseReagruparPorConquista);
        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseAtaque);
        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseReagruparFinal);
        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseAtaque);
        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseReagruparFinal);
        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseColocacion);
    }

    @Test
    public void test07enFaseAtaqueSeCumpleUnObjetivoYElJuegoTermina() {
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutaTarjetas = "./src/data/cartas.csv";

        Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
        HashMap<String, Continente> continentes = parser.getContinentes();
        Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        ListaJugadores listaJugadores = mock(ListaJugadores.class);
        when(listaJugadores.estaAlFinalDeLaLista()).thenAnswer(new Answer() {
            private int contador = -1;

            public Object answer(InvocationOnMock invocation) {
                contador++;
                if (contador == 1 || contador == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        when(listaJugadores.hayGanador(tablero)).thenReturn(true);
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        ronda.establecerCantidadAColocar(0);
        ronda.terminar();
        Fase faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof FaseAtaque);
        ronda.producirConquista();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof JuegoTerminado);

        ronda.terminar();
        faseActual = ronda.obtenerFaseActual();
        assertTrue(faseActual instanceof JuegoTerminado);
    }
}
