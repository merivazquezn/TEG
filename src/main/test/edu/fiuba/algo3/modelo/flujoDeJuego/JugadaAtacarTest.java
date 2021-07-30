package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConjuntoDados;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.Objetivo;
import edu.fiuba.algo3.modelo.jugador.ObjetivoDestruir;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JugadaAtacarTest {

    @Test
    public void test01unJugadorAtacaYConquistaPorLoQueLaSiguienteRondaEsDeReagrupe(){
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.asignarJugador(otroJugador);
        unPais.agregarLimitrofe(otroPais);
        otroPais.agregarLimitrofe(unPais);
        Jugada jugadaAtacar = new JugadaAtacar(unPais, otroPais, 1);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.ejercitosPerdidos(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList();
                lista.add(0);
                lista.add(1);
                return lista;
            }
        });
        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        ArrayList<ConjuntoDados> lista = new ArrayList<>();
        lista.add(atacante);
        lista.add(defensor);
        when(constructor.obtenerConjuntosDados(1,1)).thenReturn(lista);
        Tablero tablero = new Tablero(new HashMap<>(), constructor, new Mazo(new ArrayList<>(), new Randomizador()));
        Ronda ronda = mock(Ronda.class);
        jugadaAtacar.ejecutar(tablero, ronda);
        verify(ronda, times(1)).seProdujoConquista();
    }

    @Test
    public void test02unJugadorAtacaYConquistaPorLoQueElPaisPasaASerSuyo(){
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.asignarJugador(otroJugador);
        unPais.agregarLimitrofe(otroPais);
        otroPais.agregarLimitrofe(unPais);
        Jugada jugadaAtacar = new JugadaAtacar(unPais, otroPais, 1);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.ejercitosPerdidos(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList();
                lista.add(0);
                lista.add(1);
                return lista;
            }
        });
        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        ArrayList<ConjuntoDados> lista = new ArrayList<>();
        lista.add(atacante);
        lista.add(defensor);
        when(constructor.obtenerConjuntosDados(1,1)).thenReturn(lista);
        Tablero tablero = new Tablero(new HashMap<>(), constructor, new Mazo(new ArrayList<>(), new Randomizador()));

        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());

        Ronda ronda = new Ronda(tablero, new ListaJugadores(2, new Randomizador(), listaObjetivos));

        jugadaAtacar.ejecutar(tablero, ronda);
        assertTrue(otroPais.getJugador().equals(unJugador));
    }

    @Test
    public void test03unJugadorAtacaYNoConquistaPorLoQueElPaisNoPasaASerSuyo(){
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(1);
        otroPais.asignarJugador(otroJugador);
        unPais.agregarLimitrofe(otroPais);
        otroPais.agregarLimitrofe(unPais);
        Jugada jugadaAtacar = new JugadaAtacar(unPais, otroPais, 1);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.ejercitosPerdidos(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList();
                lista.add(0);
                lista.add(1);
                return lista;
            }
        });
        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        ArrayList<ConjuntoDados> lista = new ArrayList<>();
        lista.add(atacante);
        lista.add(defensor);
        when(constructor.obtenerConjuntosDados(1,2)).thenReturn(lista);
        Tablero tablero = new Tablero(new HashMap<>(), constructor, new Mazo(new ArrayList<>(), new Randomizador()));
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        Ronda ronda = new Ronda(tablero, new ListaJugadores(2, new Randomizador(), listaObjetivos));
        jugadaAtacar.ejecutar(tablero, ronda);
        assertTrue(otroPais.getJugador().equals(otroJugador));
    }

}
