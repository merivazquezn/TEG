package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.*;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaisTest {

    @Test
    public void test01paisColocaTresEjercitosYTieneTresEjercitos() {
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        assertEquals(unPais.getCantidadEjercitos(), 3);
    }

    @Test
    public void test02paisColocaCinchoEjercitosYTiene5Ejercitos() {
        Pais unPais = new Pais("Brasil");
        unPais.colocarEjercitos(4);
        assertEquals(unPais.getCantidadEjercitos(), 5);
    }

    @Test
    public void test03paisCon5EjercitosElimina2YTiene3(){
        Pais unPais = new Pais("Chile");
        unPais.colocarEjercitos(4);
        unPais.eliminarEjercitos(2);
        assertEquals(unPais.getCantidadEjercitos(), 3);
    }

    @Test
    public void test04paisCon3EjercitosElimina3YTiene0Ejercitos(){
        Pais unPais = new Pais("Chile");
        unPais.colocarEjercitos(2);
        unPais.eliminarEjercitos(3);
        assertEquals(unPais.getCantidadEjercitos(), 0);
    }

    @Test
    public void test05paisCon1EjercitoElimina2ElevaException() {
        Pais unPais = new Pais("Argentina");
        assertThrows(CantidadInvalidaDeEjercitosException.class,
                ()->{
                    unPais.eliminarEjercitos(2);
                });
    }

    @Test
    public void test06paisCon2EjercitosElimina1NoElevaExcepcion() {
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1);
        boolean error = false;
        try {
            unPais.eliminarEjercitos(1);
        } catch (CantidadInvalidaDeEjercitosException e) {
            error = true;
        }
        assertFalse(error);
    }

    @Test
    public void test07paisCon2EjercitosTransfiere1AOtroConUnEjercito() {
        Jugador unJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.asignarJugador(unJugador);
        unPais.colocarEjercitos(1);
        Pais otroPais = new Pais("Chile");
        otroPais.asignarJugador(unJugador);
        unPais.agregarLimitrofe(otroPais);
        otroPais.agregarLimitrofe(unPais);
        unPais.transferirEjercitosA(otroPais, 1);
        assertEquals(1, unPais.getCantidadEjercitos());
        assertEquals(2, otroPais.getCantidadEjercitos());
    }

    @Test
    public void test08paisCon5EjercitosTransfiere4AOtroSinEjercitosConquistado() {
        Jugador unJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(4);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.asignarJugador(unJugador);
        unPais.agregarLimitrofe(otroPais);
        otroPais.agregarLimitrofe(unPais);
        unPais.transferirEjercitosA(otroPais, 4);
        assertEquals(1, unPais.getCantidadEjercitos());
        assertEquals(5, otroPais.getCantidadEjercitos());
    }

    @Test
    public void test09paisCon5EjercitosQuiereTransferir5AOtroSinEjercitosConquistadoYLevantaUnaExcepcion() {
        Jugador unJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.asignarJugador(unJugador);
        unPais.colocarEjercitos(4);
        Pais otroPais = new Pais("Chile");
        otroPais.asignarJugador(unJugador);
        unPais.agregarLimitrofe(otroPais);
        otroPais.agregarLimitrofe(unPais);
        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> {
            unPais.transferirEjercitosA(otroPais, 5);
        });
    }

    @Test
    public void test10paisCon3EjercitosQuiereTransferir5AOtroSinEjercitosConquistadoYLevantaUnaExcepcion() {
        Jugador unJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.asignarJugador(unJugador);
        unPais.colocarEjercitos(2);
        Pais otroPais = new Pais("Chile");
        otroPais.asignarJugador(unJugador);
        unPais.agregarLimitrofe(otroPais);
        otroPais.agregarLimitrofe(unPais);
        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> {
            unPais.transferirEjercitosA(otroPais, 5);
        });
    }

    @Test
    public void test11paisCon3EjercitosEsConquistadoYPasaASerDeOtroJugador() {
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        otroPais.asignarJugador(otroJugador);
        unPais.eliminarEjercitos(3);
        unPais.serConquistadoPor(otroPais.getJugador());
        verify(unJugador, times(1)).perdioPaisAnte(unPais, otroJugador);
    }

    @Test
    public void test12paisCon3EjercitosQuiereMover1AOtroPaisDeOtroJugadorYLanzaExcepcion() {
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        otroPais.asignarJugador(otroJugador);
        assertThrows(MovimientoDeEjercitosInvalidoException.class, () -> {
            unPais.transferirEjercitosA(otroPais, 1);
        });
    }

    @Test
    public void test13paisCon3EjercitosQuiereMover1AOtroPaisNoLimitrofeYLanzaExcepcion() {
        Jugador unJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Rusia");
        otroPais.colocarEjercitos(2);
        otroPais.asignarJugador(unJugador);
        assertThrows(MovimientoDeEjercitosInvalidoException.class, () -> {
            unPais.transferirEjercitosA(otroPais, 1);
        });
    }

}
