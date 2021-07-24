package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class RondaTest {

    @Test
    public void test01LaRondaRecibeUnaJugadaYLaEjecuta(){
        Jugada jugada = mock(JugadaAtacar.class);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()));
        ListaJugadores listaJugadores = new ListaJugadores(3, new Randomizador());
        Ronda ronda = new Ronda(tablero, listaJugadores);
        ronda.realizarJugada(jugada);
        verify(jugada, times(1)).ejecutar(tablero, ronda);
    }

}
