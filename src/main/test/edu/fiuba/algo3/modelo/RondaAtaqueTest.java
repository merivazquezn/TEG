package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class RondaAtaqueTest {

    @Test
    public void test01seCreaUnaRondaDeAtaqueNoSeConquistaYEstaDevuelveContinuar(){
        Tablero tablero = mock(Tablero.class);
        Comunicacion comunicacion = mock(Comunicacion.class);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        Jugador uno = new Jugador();
        Jugador dos = new Jugador();
        jugadores.add(uno);
        jugadores.add(dos);
        when(comunicacion.getEleccionAtaque()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return eleccion;
            }
        });
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add("Terminar");
                return lista;
            }
        });
        RondaAtaque ronda = new RondaAtaque(jugadores, comunicacion);
        assertTrue(ronda.realizarRondaYContinuar(tablero));
    }

    @Test
    public void test02seCreaUnaRondaDeAtaqueSeConquistaUnJugadorGanoYEstaDevuelveNoContinuar(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        Tablero tablero = mock(Tablero.class);
        Comunicacion comunicacion = mock(Comunicacion.class);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        Jugador uno = mock(Jugador.class);
        Jugador dos = mock(Jugador.class);
        jugadores.add(uno);
        jugadores.add(dos);
        when(comunicacion.getEleccionAtaque()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return eleccion;
            }
        });
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(3);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        when(tablero.conquisto(unPais, otroPais, 3)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return true;
            }
        });
        when(uno.jugadorGano()).thenReturn(true);
        RondaAtaque ronda = new RondaAtaque(jugadores, comunicacion);
        assertFalse(ronda.realizarRondaYContinuar(tablero));
    }

}
