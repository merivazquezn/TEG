package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;
import java.util.ArrayList;

public class ObjetivoConquistar2Continentes implements Objetivo{

    private Jugador jugador;
    private ArrayList<String> listaObjetivos;

    public ObjetivoConquistar2Continentes(ArrayList<String> listaObjetivos){
        this.jugador = new JugadorNulo();
        this.listaObjetivos = listaObjetivos;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean haGanado(Tablero tablero, ArrayList<Jugador> listaJugadores){
        return (tablero.continenteOcupadoPorJugador(jugador, listaObjetivos.get(0)) && tablero.continenteOcupadoPorJugador(jugador, listaObjetivos.get(1)));
    }
}
