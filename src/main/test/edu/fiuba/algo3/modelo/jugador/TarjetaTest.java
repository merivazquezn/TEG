package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.JugadorNoTieneElPaisException;
import edu.fiuba.algo3.modelo.jugador.Tarjeta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TarjetaTest {

    @Test
    public void test01tarjetaSeActivaEnPaisQueTieneElJugadorYPasaDe3EjercitosA5() {

        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(3);
        Tarjeta tarjeta = new Tarjeta(pais);
        Jugador jugador = new Jugador();
        pais.asignarJugador(jugador);
        tarjeta.asignarJugador(jugador);
        tarjeta.activar();
        assertEquals(pais.getCantidadEjercitos(), 5);
    }

    @Test
    public void test02tarjetaSeActivaEnPaisQueNoTieneElJugadorElevaExcepcion() {
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(3);
        Tarjeta tarjeta = new Tarjeta(pais);
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        pais.asignarJugador(otroJugador);
        tarjeta.asignarJugador(unJugador);
        assertThrows(JugadorNoTieneElPaisException.class, tarjeta::activar);
    }

    @Test
    public void test03tarjetaSinJugadorSeActivaElevaExcepcion() {
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(3);
        Tarjeta tarjeta = new Tarjeta(pais);
        Jugador unJugador = new Jugador();
        pais.asignarJugador(unJugador);
        assertThrows(JugadorNoTieneElPaisException.class, tarjeta::activar);
    }

    @Test
    public void test04tarjetaSeActivaEnPaisQueTieneElJugadorYPasaDe7EjercitosA9() {

        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(7);
        Tarjeta tarjeta = new Tarjeta(pais);
        Jugador jugador = new Jugador();
        pais.asignarJugador(jugador);
        tarjeta.asignarJugador(jugador);
        tarjeta.activar();
        assertEquals(pais.getCantidadEjercitos(), 9);
    }

    @Test
    public void test05tarjetaSeActivaEnPaisQueTieneElJugadorYPasaDe1EjercitosA3() {

        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(1);
        Tarjeta tarjeta = new Tarjeta(pais);
        Jugador jugador = new Jugador();
        pais.asignarJugador(jugador);
        tarjeta.asignarJugador(jugador);
        tarjeta.activar();
        assertEquals(pais.getCantidadEjercitos(), 3);
    }

    @Test
    public void test06tarjetaSeActivaEnPaisQueNoTieneElJugadorElevaExcepcionYSigueTeniendo3Ejercitos() {
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(3);
        Tarjeta tarjeta = new Tarjeta(pais);
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        pais.asignarJugador(otroJugador);
        tarjeta.asignarJugador(unJugador);
        assertThrows(JugadorNoTieneElPaisException.class, tarjeta::activar);
        assertEquals(pais.getCantidadEjercitos(), 3);
    }

    @Test
    public void test07tarjetaSeActivaEnPaisQueNoTieneElJugadorElevaExcepcionYSigueTeniendo1Ejercito() {
        Pais pais = new Pais("Argentina");
        pais.colocarEjercitos(1);
        Tarjeta tarjeta = new Tarjeta(pais);
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        pais.asignarJugador(otroJugador);
        tarjeta.asignarJugador(unJugador);
        assertThrows(JugadorNoTieneElPaisException.class, tarjeta::activar);
        assertEquals(pais.getCantidadEjercitos(), 1);
    }

}
