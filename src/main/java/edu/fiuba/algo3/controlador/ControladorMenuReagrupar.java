package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.*;
import edu.fiuba.algo3.modelo.general.Pais;

public class ControladorMenuReagrupar {
    public static void realizarJugada(Ronda ronda, Pais origen, Pais destino, int cantidad) {
        Jugada jugada = new JugadaTransferir(origen, destino, cantidad);
        ronda.realizarJugada(jugada);

        ronda.notifyObservers();
        origen.notifyObservers();
        destino.notifyObservers();
    }
}
