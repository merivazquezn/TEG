package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Pais;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void test01elJugadorEsDerrotadoYDevuelveAlJugadorQueLoDerroto(){
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = mock(Pais.class);
        Pais otroPais = mock(Pais.class);
        unJugador.asignarPais(unPais);
        otroJugador.asignarPais(otroPais);
        assertNotEquals(otroJugador, unJugador.jugadorQueLoDerroto());
        unJugador.perdioPaisAnte(unPais, otroJugador);
        otroJugador.asignarPais(unPais);
        assertEquals(otroJugador, unJugador.jugadorQueLoDerroto());
    }
    @Test
    public void test02JugadorSinCanjesPuedeColocar0Ejercitos(){
        Jugador jugador = new Jugador();
        assertEquals(jugador.cantidadAColocarPorCanje(), 0);
    }
    @Test
    public void test03JugadorSinCanjesRealizaCanjeYPuedeColocar4Ejercitos(){
        Jugador jugador = new Jugador();
        jugador.realizarCanje();
        assertEquals(jugador.cantidadAColocarPorCanje(), 4);
    }
    @Test
    public void test04JugadorCon1CanjeRealizaOtroCanjeYPuedeColocar7EjercitosEnElSegundo(){
        Jugador jugador = new Jugador();
        jugador.realizarCanje();
        jugador.realizarCanje();
        assertEquals(7, jugador.cantidadAColocarPorCanje());
    }
    @Test
    public void test05JugadorCon2CanjesRealizaOtroCanjeYPuedeColocar10Ejercitos(){
        Jugador jugador = new Jugador();
        jugador.realizarCanje();
        jugador.realizarCanje();
        jugador.realizarCanje();
        assertEquals(jugador.cantidadAColocarPorCanje(), 10);
    }
    @Test
    public void test06JugadorCon3RealizaOtroCanjeYPuedeColocar15Ejercitos(){
        Jugador jugador = new Jugador();
        jugador.realizarCanje();
        jugador.realizarCanje();
        jugador.realizarCanje();
        jugador.realizarCanje();
        assertEquals(jugador.cantidadAColocarPorCanje(), 15);
    }
    @Test
    public void test07JugadorRealiza7CanjesYPuedeColocar25Ejercitos(){
        Jugador jugador = new Jugador();
        for (int i = 0; i < 7; i++){
            jugador.realizarCanje();
        }
        assertEquals(jugador.cantidadAColocarPorCanje(), 30);
    }
}
