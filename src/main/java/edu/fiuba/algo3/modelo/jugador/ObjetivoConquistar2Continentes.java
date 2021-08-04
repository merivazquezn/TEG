package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;
import java.util.ArrayList;
import java.util.HashMap;

import edu.fiuba.algo3.modelo.general.*;

public class ObjetivoConquistar2Continentes implements Objetivo{

    private Jugador jugador;
    private ArrayList<String> listaObjetivos;

    public ObjetivoConquistar2Continentes(ArrayList<String> listaObjetivos){
        this.jugador = new JugadorNulo();
        this.listaObjetivos = listaObjetivos;
    }

    public void establecerJugadores(ArrayList<Jugador> listaJugadores, int indiceJugador){
        this.jugador = listaJugadores.get(indiceJugador);
    }

    public boolean haGanado(Tablero tablero){
        return (tablero.continenteOcupadoPorJugador(jugador, listaObjetivos.get(0)) && tablero.continenteOcupadoPorJugador(jugador, listaObjetivos.get(1)));
    }

    public String nombreObjetivo(){
        return "Conquistar 2 continentes";
    }

    //----
    public String descripcionObjetivo(){
        String descripcion = "Se deberá conquistar:\n";

        String continente0 = this.listaObjetivos.get(0);
        String continente1 = this.listaObjetivos.get(1);

        descripcion += continente0 + ".\n";

        if (!continente0.equals(continente1))
            descripcion += continente1 + ".\n";

        return descripcion;
    }
}
