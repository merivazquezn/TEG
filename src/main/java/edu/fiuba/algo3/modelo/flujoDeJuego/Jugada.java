package edu.fiuba.algo3.modelo.flujoDeJuego;
import edu.fiuba.algo3.modelo.general.Tablero;

public interface Jugada {

    void ejecutar(Tablero tablero, Ronda ronda);
}
