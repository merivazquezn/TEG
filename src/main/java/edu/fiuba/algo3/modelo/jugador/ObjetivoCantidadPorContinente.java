package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjetivoCantidadPorContinente implements Objetivo{

    private Jugador jugador;
    private HashMap<String, Integer> objetivo;

    public ObjetivoCantidadPorContinente(HashMap<String, Integer> objetivo) {
        this.jugador = new JugadorNulo();
        this.objetivo = objetivo;
    }

    public void establecerJugadores(ArrayList<Jugador> listaJugadores, int indiceJugador){
        this.jugador = listaJugadores.get(indiceJugador);
    }

    public boolean haGanado(Tablero tablero){
        boolean cumpleObjetivo = true;
        for (HashMap.Entry<String, Integer> entry : this.objetivo.entrySet()) {
            String nombreContinente = entry.getKey();
            Integer cantidad = entry.getValue();
            if(tablero.cantidadPaisesDeJugadorEn(this.jugador, nombreContinente) < cantidad)
                cumpleObjetivo = false;
        }
        return cumpleObjetivo;
    }

    public String nombreObjetivo(){
        return "Conquistar una cierta cantidad\n en cada continente";
    }


    //----
    public String descripcionObjetivo(){
        String descripcion = "Se deberá conquistar:\n";
        for (HashMap.Entry<String, Integer> entry : this.objetivo.entrySet()) {
            String continente = entry.getKey();
            Integer cantidad = entry.getValue();
            descripcion += cantidad + " paises en " + continente + ".\n";
        }
        return descripcion;
    }

}
