package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.controlador.Comunicacion;
import edu.fiuba.algo3.controlador.EleccionColocacion;
import edu.fiuba.algo3.modelo.flujoDeJuego.*;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RondaColocacionTest {
/*
    Jugador jugadorUno;
    Jugador jugadorDos;
    ArrayList<Jugador> jugadores;
    Tablero tablero;
    Comunicacion comunicacion;
    EleccionColocacion eleccion;

    public void setUp(){
        jugadores = new ArrayList<Jugador>();
        jugadorUno = mock(Jugador.class);
        jugadorDos = mock(Jugador.class);
        jugadores.add(jugadorUno);
        jugadores.add(jugadorDos);
        tablero = mock(Tablero.class);
        comunicacion = mock(Comunicacion.class);
        eleccion = mock(EleccionColocacion.class);
        when(eleccion.canjeoDeTarjetas()).thenReturn(false);
        when(tablero.cantidadEjercitosPorContinente(jugadorUno)).thenReturn(0);
        when(jugadorUno.cantidadPaises()).thenReturn(10);
        when(jugadorDos.cantidadPaises()).thenReturn(10);
    }

    @Test
    public void test01seCreaUnaRondaDeColocacion2JugadoresColocan(){
        setUp();
        Pais unPais = mock(Pais.class);
        Pais otroPais = mock(Pais.class);

        Comunicacion comunicacion = mock(Comunicacion.class);
        EleccionColocacion eleccion = mock(EleccionColocacion.class);
        when(comunicacion.getEleccionColocacion()).thenReturn(eleccion);
        when(tablero.cantidadEjercitosPorContinente(jugadorDos)).thenReturn(0);
        when(eleccion.ejercitosAColocarEnPais()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count < 5){
                    lista.add(unPais);
                }
                else{
                    lista.add(otroPais);
                }
                count++;
                lista.add(1);
                return lista;
            }
        });
        RondaColocacion ronda = new RondaColocacion(jugadores, comunicacion);
        ronda.realizarRondaYContinuar(tablero);
        verify(unPais, times(5)).colocarEjercitos(1);
        verify(otroPais, times(5)).colocarEjercitos(1);
    }

    @Test
    public void test02seCreaUnaRondaDeColocacion3JugadoresColocanElSegundoDominaAsia(){
        setUp();
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Chile");
        Pais otroPaisMas = new Pais("Brasil");
        unPais.colocarEjercitos(1);
        otroPais.colocarEjercitos(2);
        otroPaisMas.colocarEjercitos(3);
        Jugador jugadorTres = mock(Jugador.class);
        jugadores.add(jugadorTres);
        when(comunicacion.getEleccionColocacion()).thenReturn(eleccion);
        when(jugadorTres.cantidadPaises()).thenReturn(10);
        when(tablero.cantidadEjercitosPorContinente(jugadorDos)).thenReturn(7);
        when(tablero.cantidadEjercitosPorContinente(jugadorTres)).thenReturn(0);
        when(eleccion.ejercitosAColocarEnPais()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count < 5){
                    lista.add(unPais);
                    lista.add(1);
                }
                else if(count < 6){
                    lista.add(otroPais);
                    lista.add(12);
                }
                else{
                    lista.add(otroPaisMas);
                    lista.add(1);
                }
                count++;
                return lista;
            }
        });
        RondaColocacion ronda = new RondaColocacion(jugadores, comunicacion);
        ronda.realizarRondaYContinuar(tablero);
        assertEquals(6, unPais.getCantidadEjercitos());
        assertEquals(14, otroPais.getCantidadEjercitos());
        assertEquals(8, otroPaisMas.getCantidadEjercitos());
    }
*/
}
