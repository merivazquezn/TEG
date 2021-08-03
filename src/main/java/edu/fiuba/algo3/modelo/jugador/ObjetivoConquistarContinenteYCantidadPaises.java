package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;

import edu.fiuba.algo3.modelo.general.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ObjetivoConquistarContinenteYCantidadPaises implements Objetivo{
    private ObjetivoConquistar2Continentes objetivo1;
    private ObjetivoCantidadPorContinente objetivo2;


    public ObjetivoConquistarContinenteYCantidadPaises(String continente, HashMap<String, Integer> listaObjetivos2){
        ArrayList<String> listaObjetivo1 = new ArrayList<>();
        listaObjetivo1.add(continente);
        listaObjetivo1.add(continente);
        this.objetivo1 = new ObjetivoConquistar2Continentes(listaObjetivo1);
        this.objetivo2 = new ObjetivoCantidadPorContinente(listaObjetivos2);
    }

    public void establecerJugadores(ArrayList<Jugador> listaJugadores, int indiceJugador){
        this.objetivo1.establecerJugadores(listaJugadores, indiceJugador);
        this.objetivo2.establecerJugadores(listaJugadores, indiceJugador);
    }


    public boolean haGanado(Tablero tablero){
        return (this.objetivo1.haGanado(tablero) && this.objetivo2.haGanado(tablero));
    }

    public String nombreObjetivo(){
        return "Conquistar una cierta cantidad en algunos\n continentes y 1 continente entero";
    }

    public String descripcionObjetivo(){
        String descripcion = this.objetivo1.descripcionObjetivo();
        descripcion += this.objetivo2.descripcionObjetivo();
        return descripcion;
    }
}
