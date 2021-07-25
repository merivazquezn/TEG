package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;

import edu.fiuba.algo3.modelo.general.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ObjetivoConquistarContinenteYCantidadPaises implements Objetivo{
    private Jugador jugador;
    private ObjetivoConquistar2Continentes objetivo1;
    private ObjetivoCantidadPorContinente objetivo2;


    public ObjetivoConquistarContinenteYCantidadPaises(String continente, HashMap<String, Integer> listaObjetivos2){
        this.jugador = new JugadorNulo();
        ArrayList<String> listaObjetivo1 = new ArrayList<>();
        listaObjetivo1.add(continente);
        listaObjetivo1.add(continente);
        this.objetivo1 = new ObjetivoConquistar2Continentes(listaObjetivo1);
        objetivo1.setJugador(this.jugador);
        this.objetivo2 = new ObjetivoCantidadPorContinente(listaObjetivos2);
        objetivo2.setJugador(this.jugador);
    }

    public void setJugador(Jugador jugador) {
        objetivo1.setJugador(jugador);
        objetivo2.setJugador(jugador);
    }


    public boolean haGanado(Tablero tablero){
        return (this.objetivo1.haGanado(tablero) && this.objetivo2.haGanado(tablero));
    }
}
