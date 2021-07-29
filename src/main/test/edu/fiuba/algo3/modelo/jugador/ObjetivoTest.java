package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.jugador.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObjetivoTest {

    @Test
    public void test01unJugadorCon30PaisesCumpleElObjetivoGeneral(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(30);
        assertTrue(objetivo.haGanado(tablero));
    }

    @Test
    public void test02unJugadorCon29PaisesNoCumpleElObjetivoGeneral(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(29);
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test03unObjetivoDe3PaisesEnCadaContienteEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        HashMap<String, Integer> listaObjetivos = new HashMap<>();
        listaObjetivos.put("Europa", 3);
        listaObjetivos.put("Asia", 3);
        listaObjetivos.put("Africa", 3);
        listaObjetivos.put("America Del Sur", 3);
        listaObjetivos.put("America Del Norte", 3);
        listaObjetivos.put("Oceania", 3);

        Objetivo objetivo = new ObjetivoCantidadPorContinente(listaObjetivos);
        objetivo.establecerJugadores(jugadores, 0);

        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Europa")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Asia")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Africa")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "America Del Sur")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "America Del Norte")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Oceania")).thenReturn(3);
        assertTrue(objetivo.haGanado(tablero));
    }

    @Test
    public void test04unObjetivoDe3PaisesEnCadaContienteNoEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        HashMap<String, Integer> listaObjetivos = new HashMap<>();
        listaObjetivos.put("Europa", 3);
        listaObjetivos.put("Asia", 3);
        listaObjetivos.put("Africa", 3);
        listaObjetivos.put("America Del Sur", 3);
        listaObjetivos.put("America Del Norte", 3);
        listaObjetivos.put("Oceania", 3);

        Objetivo objetivo = new ObjetivoCantidadPorContinente(listaObjetivos);
        objetivo.establecerJugadores(jugadores, 0);

        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Europa")).thenReturn(4);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Asia")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Africa")).thenReturn(2);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "America Del Sur")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "America Del Norte")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Oceania")).thenReturn(3);
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test05unObjetivoDePaisesVariosEnDistintosContientesEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        HashMap<String, Integer> listaObjetivos = new HashMap<>();
        listaObjetivos.put("Europa", 4);
        listaObjetivos.put("Asia", 3);
        listaObjetivos.put("Africa", 5);
        listaObjetivos.put("America Del Sur", 6);
        listaObjetivos.put("America Del Norte", 1);

        Objetivo objetivo = new ObjetivoCantidadPorContinente(listaObjetivos);
        objetivo.establecerJugadores(jugadores, 0);

        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Europa")).thenReturn(4);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Asia")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Africa")).thenReturn(5);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "America Del Sur")).thenReturn(6);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "America Del Norte")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Oceania")).thenReturn(0);
        assertTrue(objetivo.haGanado(tablero));
    }

    @Test
    public void test06unObjetivoDePaisesVariosEnDistintosContientesNoEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        HashMap<String, Integer> listaObjetivos = new HashMap<>();
        listaObjetivos.put("Europa", 4);
        listaObjetivos.put("Asia", 3);
        listaObjetivos.put("Africa", 5);
        listaObjetivos.put("America Del Sur", 6);
        listaObjetivos.put("America Del Norte", 1);

        Objetivo objetivo = new ObjetivoCantidadPorContinente(listaObjetivos);
        objetivo.establecerJugadores(jugadores, 0);

        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Europa")).thenReturn(4);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Asia")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Africa")).thenReturn(2);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "America Del Sur")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "America Del Norte")).thenReturn(3);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Oceania")).thenReturn(0);
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test07unObjetivoDe2ContinentesConquistadosEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        ArrayList<String> listaObjetivos = new ArrayList<String>();
        listaObjetivos.add("Europa");
        listaObjetivos.add("Asia");
        Objetivo objetivo = new ObjetivoConquistar2Continentes(listaObjetivos);
        objetivo.establecerJugadores(jugadores, 0);
        when(tablero.continenteOcupadoPorJugador(unJugador, "Europa")).thenReturn(true);
        when(tablero.continenteOcupadoPorJugador(unJugador, "Asia")).thenReturn(true);
        assertTrue(objetivo.haGanado(tablero));
    }

    @Test
    public void test08unObjetivoDe2ContinentesConquistadosNoEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        ArrayList<String> listaObjetivos = new ArrayList<String>();
        listaObjetivos.add("Europa");
        listaObjetivos.add("Asia");
        Objetivo objetivo = new ObjetivoConquistar2Continentes(listaObjetivos);
        objetivo.establecerJugadores(jugadores, 0);
        when(tablero.continenteOcupadoPorJugador(unJugador, "Europa")).thenReturn(true);
        when(tablero.continenteOcupadoPorJugador(unJugador, "Asia")).thenReturn(false);
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test09unObjetivoDeContinenteMasPaisesVariosEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        HashMap<String, Integer> listaObjetivos = new HashMap<>();
        listaObjetivos.put("Africa", 4);
        listaObjetivos.put("Asia", 3);
        Objetivo objetivo = new ObjetivoConquistarContinenteYCantidadPaises("Europa", listaObjetivos);
        objetivo.establecerJugadores(jugadores, 0);
        when(tablero.continenteOcupadoPorJugador(unJugador, "Europa")).thenReturn(true);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Africa")).thenReturn(4);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Asia")).thenReturn(3);
        assertTrue(objetivo.haGanado(tablero));
    }

    @Test
    public void test10unObjetivoDeContinenteMasPaisesVariosNoEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        HashMap<String, Integer> listaObjetivos = new HashMap<>();
        listaObjetivos.put("Africa", 3);
        listaObjetivos.put("Asia", 4);
        Objetivo objetivo = new ObjetivoConquistarContinenteYCantidadPaises("Europa", listaObjetivos);
        objetivo.establecerJugadores(jugadores, 0);
        when(tablero.continenteOcupadoPorJugador(unJugador, "Europa")).thenReturn(true);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Africa")).thenReturn(4);
        when(tablero.cantidadPaisesDeJugadorEn(unJugador, "Asia")).thenReturn(3);
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test11unObjetivoDeDestruirAOtroJugadorEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        jugadores.add(otroJugador);
        Objetivo objetivo = new ObjetivoDestruir();
        objetivo.establecerJugadores(jugadores, 0);
        when(otroJugador.jugadorQueLoDerroto()).thenReturn(unJugador);
        assertTrue(objetivo.haGanado(tablero));
    }

    @Test
    public void test12unObjetivoDeDestruirAOtroJugadorNoEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        Jugador otroJugadorMas = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        jugadores.add(otroJugador);
        jugadores.add(otroJugadorMas);
        Objetivo objetivo = new ObjetivoDestruir();
        objetivo.establecerJugadores(jugadores, 1);
        when(unJugador.jugadorQueLoDerroto()).thenReturn(otroJugadorMas);
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test12unObjetivoNuloNuncaEsCumplido(){
        Tablero tablero = mock(Tablero.class);
        Objetivo objetivo = new ObjetivoNulo();
        assertFalse(objetivo.haGanado(tablero));
    }

}
