package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;
import java.util.ArrayList;

public class ObjetivoConquistar2Continentes implements Objetivo{

    private Jugador jugador;
    private ArrayList<String> listaObjetivos;

    public ObjetivoConquistar2Continentes(Jugador unJugador, ArrayList<String> listaObjetivos){
        this.jugador = unJugador;
        this.listaObjetivos = listaObjetivos;
    }

    public boolean haGanado(Tablero tablero, ArrayList<Jugador> listaJugadores){
        return (tablero.continenteOcupadoPorJugador(jugador, listaObjetivos.get(0)) && tablero.continenteOcupadoPorJugador(jugador, listaObjetivos.get(1)));
    }
}
