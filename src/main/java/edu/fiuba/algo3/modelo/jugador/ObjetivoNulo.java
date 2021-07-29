package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;

import java.util.ArrayList;

public class ObjetivoNulo implements Objetivo{

    public void establecerJugadores(ArrayList<Jugador> listaJugadores, int indiceJugador){
    }

    public boolean haGanado(Tablero tablero){
        return false;
    }
}
