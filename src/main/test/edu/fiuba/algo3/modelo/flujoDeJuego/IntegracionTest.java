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
        HashMap<String, Continente> continentes;
        try {
            String rutaPaises = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            String rutaObjetivos = "./src/main/java/edu/fiuba/algo3/infraestructura/objetivos.csv";
            String rutaTarjetas = "./src/main/java/edu/fiuba/algo3/infraestructura/cartas.csv";

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
            Fase faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof FaseInicial3Fichas);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void test02seFinalizaLaRondaInicial3FichasYLaRondaPasaAFaseAtaque() throws IOException{
        HashMap<String, Continente> continentes;
        String rutaPaises = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
        String rutaObjetivos = "./src/main/java/edu/fiuba/algo3/infraestructura/objetivos.csv";
        String rutaTarjetas = "./src/main/java/edu/fiuba/algo3/infraestructura/cartas.csv";

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
        HashMap<String, Continente> continentes;
        try {
            final String rutaPaises = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            final String rutaObjetivos = "./src/main/java/edu/fiuba/algo3/infraestructura/objetivos.csv";
            final String rutaTarjetas = "./src/main/java/edu/fiuba/algo3/infraestructura/cartas.csv";

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
        catch(RuntimeException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test04seFinalizaLaPrimeraRondaAtaquePasaAFaseColocacion(){
        HashMap<String, Continente> continentes;
        try {
            final String rutaPaises = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            final String rutaObjetivos = "./src/main/java/edu/fiuba/algo3/infraestructura/objetivos.csv";
            final String rutaTarjetas = "./src/main/java/edu/fiuba/algo3/infraestructura/cartas.csv";

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
        catch(RuntimeException e){
            fail(e.getMessage());
        }
    }


    @Test
    public void test05seFinalizaLaPrimeraRondaColocacionPasaAFaseAtaque(){
        HashMap<String, Continente> continentes;
        try {
            final String rutaPaises = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            final String rutaObjetivos = "./src/main/java/edu/fiuba/algo3/infraestructura/objetivos.csv";
            final String rutaTarjetas = "./src/main/java/edu/fiuba/algo3/infraestructura/cartas.csv";

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
        catch(RuntimeException e){
            fail(e.getMessage());
        }
    }


    @Test
    public void test06enFaseAtaqueSeConquistaRondaPasaAFaseReagrupePorConquista(){
        HashMap<String, Continente> continentes;
        try {
            final String rutaPaises = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            final String rutaObjetivos = "./src/main/java/edu/fiuba/algo3/infraestructura/objetivos.csv";
            final String rutaTarjetas = "./src/main/java/edu/fiuba/algo3/infraestructura/cartas.csv";

            Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
            continentes = parser.getContinentes();
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
            ronda.seProdujoConquista();
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
        catch(RuntimeException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test07enFaseAtaqueSeCumpleUnObjetivoYElJuegoTermina() {
        HashMap<String, Continente> continentes;
        try {
            String rutaPaises = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            String rutaObjetivos = "./src/main/java/edu/fiuba/algo3/infraestructura/objetivos.csv";
            String rutaTarjetas = "./src/main/java/edu/fiuba/algo3/infraestructura/cartas.csv";

            Parser parser = new Parser(rutaPaises, rutaObjetivos, rutaTarjetas);
            continentes = parser.getContinentes();
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
            ronda.seProdujoConquista();
            faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof JuegoTerminado);

            ronda.terminar();
            faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof JuegoTerminado);
        } catch (RuntimeException e) {
            fail(e.getMessage());
        }
    }
}
