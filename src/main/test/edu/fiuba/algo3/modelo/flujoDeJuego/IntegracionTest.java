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
            String ruta = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            ArrayList<HashMap> listaParser = Parser.parsearPaisesParaTablero(ruta);
            continentes = listaParser.get(1);
            Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
            ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
            Ronda ronda = new Ronda(tablero, listaJugadores);
            ronda.terminar();
            ronda.terminar();
            Fase faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof FaseInicial3Fichas);
        }
        catch(IOException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test02seFinalizaLaRondaInicial3FichasYLaRondaPasaAFaseAtaque(){
        HashMap<String, Continente> continentes;
        try {
            String ruta = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            ArrayList<HashMap> listaParser = Parser.parsearPaisesParaTablero(ruta);
            continentes = listaParser.get(1);
            Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
            ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
            Ronda ronda = new Ronda(tablero, listaJugadores);
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            Fase faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof FaseAtaque);
        }
        catch(IOException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test03seFinalizaLaPrimeraRondaAtaquePorCadaUnaSePasaAReagruparFinal(){
        HashMap<String, Continente> continentes;
        try {
            String ruta = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            ArrayList<HashMap> listaParser = Parser.parsearPaisesParaTablero(ruta);
            continentes = listaParser.get(1);
            Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
            ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
            Ronda ronda = new Ronda(tablero, listaJugadores);
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
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
        catch(IOException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test04seFinalizaLaPrimeraRondaAtaquePasaAFaseColocacion(){
        HashMap<String, Continente> continentes;
        try {
            String ruta = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            ArrayList<HashMap> listaParser = Parser.parsearPaisesParaTablero(ruta);
            continentes = listaParser.get(1);
            Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
            ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
            Ronda ronda = new Ronda(tablero, listaJugadores);
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            Fase faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof FaseColocacion);
            ronda.terminar();
            faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof FaseColocacion);
        }
        catch(IOException e){
            fail(e.getMessage());
        }
    }


    @Test
    public void test05seFinalizaLaPrimeraRondaColocacionPasaAFaseAtaque(){
        HashMap<String, Continente> continentes;
        try {
            String ruta = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            ArrayList<HashMap> listaParser = Parser.parsearPaisesParaTablero(ruta);
            continentes = listaParser.get(1);
            Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
            ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador(), listaObjetivos);
            Ronda ronda = new Ronda(tablero, listaJugadores);
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            Fase faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof FaseAtaque);
        }
        catch(IOException e){
            fail(e.getMessage());
        }
    }


    @Test
    public void test06enFaseAtaqueSeConquistaRondaPasaAFaseReagrupePorConquista(){
        HashMap<String, Continente> continentes;
        try {
            String ruta = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            ArrayList<HashMap> listaParser = Parser.parsearPaisesParaTablero(ruta);
            continentes = listaParser.get(1);
            Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
            ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            listaObjetivos.add(new ObjetivoDestruir());
            ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador(), listaObjetivos);
            Ronda ronda = new Ronda(tablero, listaJugadores);
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
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
        catch(IOException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test07enFaseAtaqueSeCumpleUnObjetivoYElJuegoTermina(){
        HashMap<String, Continente> continentes;
        try {
            String ruta = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            ArrayList<HashMap> listaParser = Parser.parsearPaisesParaTablero(ruta);
            continentes = listaParser.get(1);
            Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
            ListaJugadores listaJugadores = mock(ListaJugadores.class);
            Jugador unJugador = new Jugador(new ObjetivoDestruir());
            Jugador otroJugador = new Jugador(new ObjetivoDestruir());
            when(listaJugadores.siguiente()).thenAnswer(new Answer() {
                private int contador = -1;

                public Object answer(InvocationOnMock invocation) {
                    contador++;
                    if(contador == 0 || contador == 3){
                        return unJugador;
                    }
                    else if(contador == 1 || contador == 4){
                        return otroJugador;
                    }
                    else{
                        return new JugadorNulo();
                    }
                }
            });
            when(listaJugadores.hayGanador(tablero)).thenReturn(true);
            Ronda ronda = new Ronda(tablero, listaJugadores);
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            ronda.terminar();
            Fase faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof FaseAtaque);
            ronda.seProdujoConquista();
            faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof JuegoTerminado);

            ronda.terminar();
            faseActual = ronda.obtenerFaseActual();
            assertTrue(faseActual instanceof JuegoTerminado);
        }
        catch(IOException e){
            fail(e.getMessage());
        }
    }

}
