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
        unPais.colocarEjercitos(3);
        assertEquals(unPais.getCantidadEjercitos(), 3);
    }

    @Test
    public void test02paisColocaCinchoEjercitosYTiene5Ejercitos() {
        Pais unPais = new Pais("Brasil");
        unPais.colocarEjercitos(5);
        assertEquals(unPais.getCantidadEjercitos(), 5);
    }


    @Test
    public void test03paisCon5EjercitosElimina2YTiene3(){
        Pais unPais = new Pais("Chile");
        unPais.colocarEjercitos(5);
        unPais.eliminarEjercitos(2);
        assertEquals(unPais.getCantidadEjercitos(), 3);
    }

    @Test
    public void test04paisCon3EjercitosElimina3YTiene0Ejercitos(){
        Pais unPais = new Pais("Chile");
        unPais.colocarEjercitos(3);
        unPais.eliminarEjercitos(3);
        assertEquals(unPais.getCantidadEjercitos(), 0);
    }

    @Test
    public void test05paisCon1EjercitoElimina2ElevaException() {
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1);
        assertThrows(CantidadInvalidaDeEjercitosException.class,
                ()->{
                    unPais.eliminarEjercitos(2);
                });
    }

    @Test
    public void test06paisCon2EjercitosElimina1NoElevaExcepcion() {
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        boolean error = false;
        try {
            unPais.eliminarEjercitos(1);
        } catch (CantidadInvalidaDeEjercitosException e) {
            error = true;
        }
        assertFalse(error);
    }

    @Test
    public void test07paisCon2EjercitosTransfiere1AOtroSinEjercitosConquistado() {
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        Pais otroPais = new Pais("Chile");
        unPais.transferirEjercitosA(otroPais, 1);
        assertEquals(1, unPais.getCantidadEjercitos());
        assertEquals(1, otroPais.getCantidadEjercitos());
    }

    @Test
    public void test08paisCon5EjercitosTransfiere4AOtroSinEjercitosConquistado() {
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        unPais.transferirEjercitosA(otroPais, 4);
        assertEquals(1, unPais.getCantidadEjercitos());
        assertEquals(4, otroPais.getCantidadEjercitos());
    }

    @Test
    public void test09paisCon5EjercitosQuiereTransferir5AOtroSinEjercitosConquistadoYLevantaUnaExcepcion() {
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> {
            unPais.transferirEjercitosA(otroPais, 5);
        });
    }

    @Test
    public void test10paisCon3EjercitosQuiereTransferir5AOtroSinEjercitosConquistadoYLevantaUnaExcepcion() {
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(3);
        Pais otroPais = new Pais("Chile");
        assertThrows(CantidadInvalidaDeEjercitosException.class, () -> {
            unPais.transferirEjercitosA(otroPais, 5);
        });
    }

    @Test
    public void test11paisCon3EjercitosEsConquistadoYPasaASerDeOtroJugador() {
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(3);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(3);
        otroPais.asignarJugador(otroJugador);
        unPais.eliminarEjercitos(3);
        unPais.serConquistadoPor(unPais.getJugador());
        verify(unJugador, times(1)).perdioPaisAnte(unPais, otroJugador);
    }

    @Test
    public void test12paisCon3EjercitosQuiereMover1AOtroPaisDeOtroJugadorYLanzaExcepcion() {
        Jugador unJugador = mock(Jugador.class);
        Jugador otroJugador = mock(Jugador.class);
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(3);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(3);
        otroPais.asignarJugador(otroJugador);
        assertThrows(MovimientoDeEjercitosInvalidoException.class, () -> {
            unPais.transferirEjercitosA(otroPais, 1);
        });
    }

}
