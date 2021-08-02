package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.Jugada;
import edu.fiuba.algo3.modelo.flujoDeJuego.JugadaAtacar;
import edu.fiuba.algo3.modelo.flujoDeJuego.JugadaColocar;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class ControladorMenuColocacion {
    public static void realizarJugada(Ronda ronda, Pais unPais, int cantidadAColocar){

        Jugada jugada = new JugadaColocar(unPais, cantidadAColocar);
        ronda.realizarJugada(jugada);
        ronda.notifyObservers();
        unPais.notifyObservers();
    }
}
