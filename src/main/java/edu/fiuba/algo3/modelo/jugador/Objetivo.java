package edu.fiuba.algo3.modelo.jugador;
import edu.fiuba.algo3.modelo.general.*;
import java.util.ArrayList;

public interface Objetivo {
    boolean haGanado(Tablero tablero, ArrayList<Jugador> listaJugadores);
}
