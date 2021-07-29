package edu.fiuba.algo3.modelo.general;


import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.JugadorNoTieneElPaisException;
import edu.fiuba.algo3.modelo.general.Signo;
import edu.fiuba.algo3.modelo.general.Tarjeta;

import edu.fiuba.algo3.modelo.jugador.ObjetivoDestruir;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TarjetaTest {

    @Test
    public void test01tarjetaSeActivaEnPaisQueTieneElJugadorYPasaDe3EjercitosA5() {

        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(3);

        Tarjeta tarjeta = new Tarjeta(pais, new Signo(0));

        Jugador jugador = new Jugador(new ObjetivoDestruir());
        pais.asignarJugador(jugador);
        tarjeta.asignarJugador(jugador);
        tarjeta.activar();
        assertEquals(pais.getCantidadEjercitos(), 5);
    }

    @Test
    public void test02tarjetaSeActivaEnPaisQueNoTieneElJugadorElevaExcepcion() {
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(3);
        Tarjeta tarjeta = new Tarjeta(pais, new Signo(0));
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());
        pais.asignarJugador(otroJugador);
        tarjeta.asignarJugador(unJugador);
        assertThrows(JugadorNoTieneElPaisException.class, tarjeta::activar);
    }

    @Test
    public void test03tarjetaSinJugadorSeActivaElevaExcepcion() {
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(3);
        Tarjeta tarjeta = new Tarjeta(pais, new Signo(0));
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        pais.asignarJugador(unJugador);
        assertThrows(JugadorNoTieneElPaisException.class, tarjeta::activar);
    }

    @Test
    public void test04tarjetaSeActivaEnPaisQueTieneElJugadorYPasaDe7EjercitosA9() {

        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(7);
        Tarjeta tarjeta = new Tarjeta(pais, new Signo(0));
        Jugador jugador = new Jugador(new ObjetivoDestruir());
        pais.asignarJugador(jugador);
        tarjeta.asignarJugador(jugador);
        tarjeta.activar();
        assertEquals(pais.getCantidadEjercitos(), 9);
    }

    @Test
    public void test05tarjetaSeActivaEnPaisQueTieneElJugadorYPasaDe1EjercitosA3() {

        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(1);
        Tarjeta tarjeta = new Tarjeta(pais, new Signo(0));
        Jugador jugador = new Jugador(new ObjetivoDestruir());
        pais.asignarJugador(jugador);
        tarjeta.asignarJugador(jugador);
        tarjeta.activar();
        assertEquals(pais.getCantidadEjercitos(), 3);
    }

    @Test
    public void test06tarjetaSeActivaEnPaisQueNoTieneElJugadorElevaExcepcionYSigueTeniendo3Ejercitos() {
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(3);
        Tarjeta tarjeta = new Tarjeta(pais, new Signo(0));
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());
        pais.asignarJugador(otroJugador);
        tarjeta.asignarJugador(unJugador);
        assertThrows(JugadorNoTieneElPaisException.class, tarjeta::activar);
        assertEquals(pais.getCantidadEjercitos(), 3);
    }

    @Test
    public void test07tarjetaSeActivaEnPaisQueNoTieneElJugadorElevaExcepcionYSigueTeniendo1Ejercito() {
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(1);
        Tarjeta tarjeta = new Tarjeta(pais, new Signo(0));
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());
        pais.asignarJugador(otroJugador);
        tarjeta.asignarJugador(unJugador);
        assertThrows(JugadorNoTieneElPaisException.class, tarjeta::activar);
        assertEquals(pais.getCantidadEjercitos(), 1);
    }

    @Test
    public void test08DevuelveSignoCorrectamente() {
        Signo signo0 = new Signo(0);
        Signo signo2 = new Signo(2);


        Tarjeta tarjeta1 = new Tarjeta(new Pais("Argentina"), signo0);

        Tarjeta tarjeta2 = new Tarjeta(new Pais("Argentina"), signo2);

        assertEquals(tarjeta1.obtenerSigno(), signo0);
        assertEquals(tarjeta2.obtenerSigno(), signo2);
    }

    @Test
    public void test09DosTarjetasDistintasDevuelveFalseAlPreguntarSiSonDelMismoSigno() {

        Signo unSigno = new Signo(0);
        Signo otroSigno = new Signo(1);
        Signo otroSignoMas = new Signo(2);

        Tarjeta unaTarjeta = new Tarjeta(new Pais("Argentina"), unSigno);
        Tarjeta otraTarjeta = new Tarjeta(new Pais("Brasil"), otroSigno);
        Tarjeta otraTarjetaMas = new Tarjeta(new Pais("Chile"), otroSignoMas);

        assertFalse(unaTarjeta.mismoSignoQue(otraTarjeta));
        assertFalse(otraTarjeta.mismoSignoQue(unaTarjeta));
        assertFalse(unaTarjeta.mismoSignoQue(otraTarjetaMas));
    }


    @Test
    public void test10DosTarjetasConMismoSignoDevuelveTrueAlPreguntarSiSonMismoSigno() {
        Signo signo = new Signo(0);
        Signo signosegundo = new Signo(0);

        Tarjeta tarjeta1 = new Tarjeta(new Pais("Argentina"), signo);
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Chile"), signo);
        Tarjeta tarjeta3 = new Tarjeta(new Pais("Uruguay"), signosegundo);

        assertTrue(tarjeta1.mismoSignoQue(tarjeta2));
        assertTrue(tarjeta1.mismoSignoQue(tarjeta3));
    }

}
