package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.vista.AsignadorDeColores;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ObjetivoTest {

    @Test
    public void test00unJugadorCon40PaisesJugandoDeA2CumpleElObjetivoGeneral() {
        ObjetivoGeneral.resetearObjetivo();
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ObjetivoGeneral.cambiarCantidadPaisesParaDosJugadores();
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(40);
        assertTrue(objetivo.haGanado(tablero));
    }

    @Test
    public void test0p5unJugadorCon39PaisesJugandoDeA2NoCumpleElObjetivoGeneral() {
        ObjetivoGeneral.resetearObjetivo();
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ObjetivoGeneral.cambiarCantidadPaisesParaDosJugadores();
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(39);
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test01unJugadorCon30PaisesCumpleElObjetivoGeneral(){
        ObjetivoGeneral.resetearObjetivo();
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ObjetivoGeneral.resetearObjetivo();
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(30);
        assertTrue(objetivo.haGanado(tablero));
    }

    @Test
    public void test02unJugadorCon29PaisesNoCumpleElObjetivoGeneral(){
        ObjetivoGeneral.resetearObjetivo();
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        Objetivo objetivo = new ObjetivoGeneral(unJugador);
        when(unJugador.cantidadPaises()).thenReturn(29);
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test03unObjetivoDe3PaisesEnCadaContienteEsCumplido(){
        ObjetivoGeneral.resetearObjetivo();
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
        ObjetivoGeneral.resetearObjetivo();
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
        ObjetivoGeneral.resetearObjetivo();
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
        ObjetivoGeneral.resetearObjetivo();
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
        ObjetivoGeneral.resetearObjetivo();
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        ArrayList<String> listaObjetivos = new ArrayList<>();
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
        ObjetivoGeneral.resetearObjetivo();
        Tablero tablero = mock(Tablero.class);
        Jugador unJugador = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(unJugador);
        ArrayList<String> listaObjetivos = new ArrayList<>();
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
        ObjetivoGeneral.resetearObjetivo();
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
        ObjetivoGeneral.resetearObjetivo();
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
        ObjetivoGeneral.resetearObjetivo();
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
        ObjetivoGeneral.resetearObjetivo();
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
        ObjetivoGeneral.resetearObjetivo();
        Tablero tablero = mock(Tablero.class);
        Objetivo objetivo = new ObjetivoNulo();
        assertFalse(objetivo.haGanado(tablero));
    }

    @Test
    public void test13ObjetivoCantidadPorContinenteDevuelveDescripcionCorrectamente() {
        ObjetivoGeneral.resetearObjetivo();
        HashMap<String, Integer> hashObjetivo = new HashMap<>();

        hashObjetivo.put("America del Norte", 5);
        hashObjetivo.put("Europa", 2);

        Objetivo objetivo = new ObjetivoCantidadPorContinente(hashObjetivo);

        String stringEsperado1 = "Se deberá conquistar:\n" +
                "5 paises en America del Norte.\n" +
                "2 paises en Europa.\n";


        String stringEsperado2 = "Se deberá conquistar:\n" +
                "2 paises en Europa.\n" +
                "5 paises en America del Norte.\n";

        String stringObjetivo = objetivo.descripcionObjetivo();

        if (!stringObjetivo.equals(stringEsperado1) && !stringObjetivo.equals(stringEsperado2)) {
            String stringFail = "\n\"" + stringObjetivo + "\n\" debería ser igual a:\n\n\""
                    + stringEsperado1 + "\n\" o \n\n\"" + stringEsperado2 + "\".";

            fail(stringFail);
        }

        assertTrue(true);
    }

    @Test
    public void test14ObjetivoDestruirDevuelveDescripcionCorrectamente() {
        ObjetivoGeneral.resetearObjetivo();
        Objetivo objetivoNulo = new ObjetivoNulo();
        Objetivo objetivo = new ObjetivoDestruir();

        Jugador.reiniciarClase();

        Jugador jugador = new Jugador(objetivoNulo);
        Jugador jugadorADestruir = new Jugador(objetivoNulo);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        jugadores.add(jugadorADestruir);

        objetivo.establecerJugadores(jugadores, 0);

        String stringEsperado = "Se deberá destruir a:\n" +
                AsignadorDeColores.jugadorActualSegunElNumero(jugadorADestruir.getNumero()) + "\n";

        String stringObjetivo = objetivo.descripcionObjetivo();

        assertEquals(stringEsperado, stringObjetivo);
    }


    @Test
    public void test15ObjetivoCOnquistarContinenteYCantidadPaisesDevuelveDescripcionCorrectamente() {
        ObjetivoGeneral.resetearObjetivo();
        String continente = "Africa";
        HashMap<String, Integer> hashObjetivo = new HashMap<>();
        hashObjetivo.put("Oceania", 3);

        Objetivo objetivo = new ObjetivoConquistarContinenteYCantidadPaises(continente, hashObjetivo);

        String stringEsperado = "Se deberá conquistar:\n" +
                "Africa.\n" +
                "Se deberá conquistar:\n" +
                "3 paises en Oceania.\n";

        assertEquals(stringEsperado, objetivo.descripcionObjetivo());
    }

    @Test
    public void test16ObjetivoConquistar2ContinentesDevuelveDescripcionCorrectamente() {
        ObjetivoGeneral.resetearObjetivo();
        ArrayList<String> listaContinente = new ArrayList<>();
        listaContinente.add("Asia");
        listaContinente.add("Europa");

        Objetivo objetivo = new ObjetivoConquistar2Continentes(listaContinente);

        String stringEsperado = "Se deberá conquistar:\n" +
                "Asia.\n" +
                "Europa.\n";

        assertEquals(stringEsperado, objetivo.descripcionObjetivo());
    }

    @Test
    public void test17ObjetivoGeneralDevuelveDescripcionCorrectamente() {
        ObjetivoGeneral.resetearObjetivo();
        Jugador.reiniciarClase();

        Jugador jugador = new Jugador(new ObjetivoNulo());
        Objetivo objetivo = new ObjetivoGeneral(jugador);

        String stringEsperado = "Conquistar 30 paises\n";

        assertEquals(stringEsperado, objetivo.descripcionObjetivo());
    }

    @Test
    public void test18ObjetivoNuloSiempreDevuelveNoGano(){
        ObjetivoNulo objetivoNulo = new ObjetivoNulo();
        Tablero tablero = mock(Tablero.class);
        assertFalse(objetivoNulo.haGanado(tablero));
    }

    @Test
    public void test19ObjetivoNuloDevuelveLosStringEsperados(){
        ObjetivoNulo objetivoNulo = new ObjetivoNulo();
        assertEquals("Objetivo nulo", objetivoNulo.nombreObjetivo());
        assertEquals("Sin objetivo", objetivoNulo.descripcionObjetivo());
    }

}
