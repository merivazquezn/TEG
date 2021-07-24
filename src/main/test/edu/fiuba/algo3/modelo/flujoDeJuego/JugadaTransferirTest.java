package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.ListaJugadores;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JugadaTransferirTest {

    @Test
    public void test01unJugadorTransfiereEjercitosEntrePaisesYLasCantidadesFinalesSonLasEsperadas(){
        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Bolivia");
        unPais.colocarEjercitos(2);
        otroPais.colocarEjercitos(1);
        unPais.asignarJugador(unJugador);
        otroPais.asignarJugador(unJugador);
        unPais.agregarLimitrofe(otroPais);
        otroPais.agregarLimitrofe(unPais);
        Jugada jugada = new JugadaTransferir(unPais, otroPais, 1);
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()));
        Ronda ronda = new Ronda(tablero, new ListaJugadores(new ArrayList<>(), new Randomizador()));
        jugada.ejecutar(tablero, ronda);
        assertEquals(1, unPais.getCantidadEjercitos());
        assertEquals(2, otroPais.getCantidadEjercitos());
    }

}
