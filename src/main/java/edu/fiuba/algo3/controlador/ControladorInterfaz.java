package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;

public class ControladorInterfaz {

    public static void finalizarTurno(Ronda ronda){
        ronda.terminar();
        ronda.notifyObservers();
    }

}
