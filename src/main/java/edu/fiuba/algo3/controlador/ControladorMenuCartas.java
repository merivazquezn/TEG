package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.*;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tarjeta;

public class ControladorMenuCartas {

    public static void canjearTarjetas(Ronda ronda, Tarjeta primera, Tarjeta segunda, Tarjeta tercera){
        Jugada jugada = new JugadaCanjearTarjetas(primera, segunda, tercera);
        ronda.realizarJugada(jugada);
        ronda.notifyObservers();
    }

    public static void activarTarjeta(Ronda ronda, Tarjeta unaTarjeta){
        Jugada jugada = new JugadaActivarTarjetas(unaTarjeta);
        ronda.realizarJugada(jugada);
        ronda.notifyObservers();
        Pais pais = unaTarjeta.getPais();
        pais.notifyObservers();
    }

}
