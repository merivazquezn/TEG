package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.controlador.EleccionColocacion;
import edu.fiuba.algo3.modelo.flujoDeJuego.*;
import edu.fiuba.algo3.modelo.jugador.*;
import edu.fiuba.algo3.modelo.general.*;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class TurnoColocacionTest {

    @Test
    public void test01sePideColocar15EjercitosDeA1YSeLlama15VecesAColocar(){
        EleccionColocacion eleccion = mock(EleccionColocacion.class);
        Pais pais = mock(Pais.class);
        when(eleccion.canjeoDeTarjetas()).thenReturn(false);
        when(eleccion.ejercitosAColocarEnPais()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add(pais);
                lista.add(1);
                return lista;
            }
        });
        Jugador jugador = mock(Jugador.class);
        when(jugador.cantidadPaises()).thenReturn(25);
        TurnoColocacion turno = new TurnoColocacion(jugador, eleccion);
        Tablero tablero = mock(Tablero.class);
        when(tablero.cantidadEjercitosPorContinente(jugador)).thenReturn(3);
        turno.realizarTurnoYContinuar(tablero);
        verify(pais, times(15)).colocarEjercitos(1);
    }
    @Test
    public void test02sePideColocarMasEjercitosDeLosDisponiblesEIgnoraLaPeticion(){
        EleccionColocacion eleccion = mock(EleccionColocacion.class);
        Pais pais = mock(Pais.class);
        when(eleccion.canjeoDeTarjetas()).thenReturn(false);
        when(eleccion.ejercitosAColocarEnPais()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count < 3){
                    lista.add(pais);
                    lista.add(16);
                }
                else{
                    lista.add(pais);
                    lista.add(1);
                }
                count++;
                return lista;
            }
        });
        Jugador jugador = mock(Jugador.class);
        when(jugador.cantidadPaises()).thenReturn(25);
        TurnoColocacion turno = new TurnoColocacion(jugador, eleccion);
        Tablero tablero = mock(Tablero.class);
        when(tablero.cantidadEjercitosPorContinente(jugador)).thenReturn(3);
        turno.realizarTurnoYContinuar(tablero);
        verify(pais, times(15)).colocarEjercitos(1);
    }

    @Test
    public void test03sePideColocarUnaCantidadDeEJercitosUnaSolaVez(){
        EleccionColocacion eleccion = mock(EleccionColocacion.class);
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(4);
        when(eleccion.canjeoDeTarjetas()).thenReturn(false);
        when(eleccion.ejercitosAColocarEnPais()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add(pais);
                lista.add(15);
                return lista;
            }
        });
        Jugador jugador = mock(Jugador.class);
        when(jugador.cantidadPaises()).thenReturn(25);
        TurnoColocacion turno = new TurnoColocacion(jugador, eleccion);
        Tablero tablero = mock(Tablero.class);
        when(tablero.cantidadEjercitosPorContinente(jugador)).thenReturn(3);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(19, pais.getCantidadEjercitos());
    }

    @Test
    public void test04sePideCanjearTarjetasYSeAgreganLosEjercitosDeLaMisma(){
        EleccionColocacion eleccion = mock(EleccionColocacion.class);
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(4);
        Tarjeta tarjeta = mock(Tarjeta.class);
        when(eleccion.canjeoDeTarjetas()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                count++;
                if(count == 20){
                    return true;
                }
                else{
                    return false;
                }
            }
        });
        when(eleccion.ejercitosAColocarEnPais()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add(pais);
                lista.add(1);
                return lista;
            }
        });
        when(eleccion.tarjetaACanjear()).thenReturn(tarjeta);
        Jugador jugador = mock(Jugador.class);
        when(jugador.cantidadPaises()).thenReturn(25);
        TurnoColocacion turno = new TurnoColocacion(jugador, eleccion);
        Tablero tablero = mock(Tablero.class);
        when(tablero.cantidadEjercitosPorContinente(jugador)).thenReturn(8);
        turno.realizarTurnoYContinuar(tablero);
        verify(tarjeta, times(1)).activar();
    }
}
