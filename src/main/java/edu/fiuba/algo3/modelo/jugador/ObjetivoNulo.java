package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjetivoNulo implements Objetivo{

    public void establecerJugadores(ArrayList<Jugador> listaJugadores, int indiceJugador){
    }

    public boolean haGanado(Tablero tablero){
        return false;
    }

    public String nombreObjetivo(){
        return "Objetivo nulo";
    }

    public String descripcionObjetivo(){
        return "Sin objetivo";
    }
}
