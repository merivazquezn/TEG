package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.Jugada;
import edu.fiuba.algo3.modelo.flujoDeJuego.JugadaAtacar;
import edu.fiuba.algo3.modelo.flujoDeJuego.JugadaColocar;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class ControladorMenuAtaque {

    public static void realizarJugada(Ronda ronda, Pais atacante, Pais defensor){
        Jugada jugada = new JugadaAtacar(atacante, defensor);
        ronda.realizarJugada(jugada);
        ronda.notifyObservers();
        atacante.notifyObservers();
        defensor.notifyObservers();
    }
}
