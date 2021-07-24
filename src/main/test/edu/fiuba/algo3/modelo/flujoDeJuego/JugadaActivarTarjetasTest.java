package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.general.Signo;
import edu.fiuba.algo3.modelo.general.Tarjeta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JugadaActivarTarjetasTest {

    @Test
    public void test01unaJugadaActivaUnaTarjetaYAlPaisSeLeSuman2Ejercitos(){
        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        unPais.asignarJugador(unJugador);
        Tarjeta tarjeta = new Tarjeta(unPais, new Signo(2));
        tarjeta.asignarJugador(unJugador);
        Jugada jugada = new JugadaActivarTarjetas(tarjeta);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()));
        Ronda ronda = new Ronda(tablero, new ListaJugadores(2, new Randomizador()));
        jugada.ejecutar(tablero, ronda);
        assertEquals(4, unPais.getCantidadEjercitos());
    }

    @Test
    public void test02unaJugadaIntentaActivarUnaTarjetaDeUnPaisQueNoLePerteneceAlJugadorYNoSeLeSuman(){
        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        Tarjeta tarjeta = new Tarjeta(unPais, new Signo(2));
        tarjeta.asignarJugador(unJugador);
        Jugada jugada = new JugadaActivarTarjetas(tarjeta);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()));
        Ronda ronda = new Ronda(tablero, new ListaJugadores(2, new Randomizador()));
        jugada.ejecutar(tablero, ronda);
        assertEquals(2, unPais.getCantidadEjercitos());
    }

}
