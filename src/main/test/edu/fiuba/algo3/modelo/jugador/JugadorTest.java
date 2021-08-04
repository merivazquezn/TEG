package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.general.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    private ConjuntoTarjetas conjuntoTarjetas;
    private ArrayList<Tarjeta> tarjetas;

    private void setUp() {
        Tarjeta tarjeta1 = new Tarjeta(new Pais("Argentina"), new Signo(1));
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Chile"), new Signo(2));
        Tarjeta tarjeta3 = new Tarjeta(new Pais("Uruguay"), new Signo(0));

        this.tarjetas = new ArrayList<Tarjeta>();
        this.tarjetas.add(tarjeta1);
        this.tarjetas.add(tarjeta2);
        this.tarjetas.add(tarjeta3);

        this.conjuntoTarjetas = new ConjuntoTarjetas(tarjeta1, tarjeta2, tarjeta3, new Mazo(new ArrayList<Tarjeta>(), new Randomizador()));
    }

    private void agregarTarjetasAJugador(Jugador jugador, ArrayList<Tarjeta> tarjetas, int cantVeces) {
        for (int i = 0; i< cantVeces; i++) {

            for (Tarjeta tarjeta: tarjetas) {

                jugador.agregarTarjeta(tarjeta);
            }
        }
    }



    @Test
    public void test01elJugadorEsDerrotadoYDevuelveAlJugadorQueLoDerroto(){
        setUp();
        Jugador unJugador = new Jugador(new ObjetivoDestruir());
        Jugador otroJugador = new Jugador(new ObjetivoDestruir());
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
        setUp();
        Jugador jugador = new Jugador(new ObjetivoDestruir());
        assertEquals(jugador.cantidadAColocarPorCanje(), 0);
    }
    @Test
    public void test03JugadorSinCanjesRealizaCanjeYPuedeColocar4Ejercitos(){
        setUp();
        Jugador jugador = new Jugador(new ObjetivoDestruir());

        agregarTarjetasAJugador(jugador, this.tarjetas, 1);

        jugador.realizarCanje(this.conjuntoTarjetas);
        assertEquals(jugador.cantidadAColocarPorCanje(), 4);
    }
    @Test
    public void test04JugadorCon1CanjeRealizaOtroCanjeYPuedeColocar7EjercitosEnElSegundo(){
        setUp();
        Jugador jugador = new Jugador(new ObjetivoDestruir());


        agregarTarjetasAJugador(jugador, this.tarjetas, 2);

        jugador.realizarCanje(this.conjuntoTarjetas);
        jugador.realizarCanje(this.conjuntoTarjetas);
        assertEquals(7, jugador.cantidadAColocarPorCanje());
    }
    @Test
    public void test05JugadorCon2CanjesRealizaOtroCanjeYPuedeColocar10Ejercitos(){
        setUp();
        Jugador jugador = new Jugador(new ObjetivoDestruir());

        agregarTarjetasAJugador(jugador, this.tarjetas, 3);
        jugador.realizarCanje(this.conjuntoTarjetas);
        jugador.realizarCanje(this.conjuntoTarjetas);
        jugador.realizarCanje(this.conjuntoTarjetas);
        assertEquals(jugador.cantidadAColocarPorCanje(), 10);
    }
    @Test
    public void test06JugadorCon3RealizaOtroCanjeYPuedeColocar15Ejercitos(){
        setUp();
        Jugador jugador = new Jugador(new ObjetivoDestruir());

        agregarTarjetasAJugador(jugador, this.tarjetas, 4);
        jugador.realizarCanje(this.conjuntoTarjetas);
        jugador.realizarCanje(this.conjuntoTarjetas);
        jugador.realizarCanje(this.conjuntoTarjetas);
        jugador.realizarCanje(this.conjuntoTarjetas);
        assertEquals(jugador.cantidadAColocarPorCanje(), 15);
    }
    @Test
    public void test07JugadorRealiza7CanjesYPuedeColocar25Ejercitos(){
        setUp();
        Jugador jugador = new Jugador(new ObjetivoDestruir());

        agregarTarjetasAJugador(jugador, this.tarjetas, 7);
        for (int i = 0; i < 7; i++){
            jugador.realizarCanje(this.conjuntoTarjetas);
        }
        assertEquals(jugador.cantidadAColocarPorCanje(), 30);
    }

    @Test
    public void test08DespuesDeConquistaLaTarjetaSeAsignaAlJugadorConquistador(){
        Mazo mazo = mock(Mazo.class);
        Jugador jugador = new Jugador(new ObjetivoNulo());
        Tarjeta tarjeta = new Tarjeta(new Pais("Arg"), new Signo(1));
        when(mazo.entregarTarjeta()).thenReturn(tarjeta);
        jugador.conquisto();
        jugador.habilitarTarjetaPorConquista(mazo);
        assertEquals(tarjeta.getJugador(), jugador);
    }
}
