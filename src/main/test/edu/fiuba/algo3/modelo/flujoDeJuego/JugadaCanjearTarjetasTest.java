package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.*;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import java.util.HashMap;
public class JugadaCanjearTarjetasTest {

    @Test
    public void test01SeCanjean3CartasDeTipoGloboYSonCanjeables(){
        Jugador unJugador = new Jugador();
        //ListaJugadores listaJugadores = new ListaJugadores();
        Tablero tablero = new Tablero(new HashMap<>(), new ConstructorDeConjuntoDados(new Randomizador()));
        //Mazo mazo = new Mazo();

        //Ronda ronda = new Ronda(tablero, );
        Tarjeta tarjeta1 = new Tarjeta(new Pais("Argentina"), new Signo(1));
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Brasil"), new Signo(1));
        Tarjeta tarjeta3 = new Tarjeta(new Pais("Chile"), new Signo(1));

        //JugadaCanjearTarjetas jugada = new JugadaCanjearTarjetas(tarjeta1, tarjeta2, tarjeta3);
        //jugada.ejecutar(tablero, ronda);
        //assertEquals()
    }
}
