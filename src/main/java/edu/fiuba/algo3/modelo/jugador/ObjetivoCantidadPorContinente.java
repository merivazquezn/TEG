package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjetivoCantidadPorContinente implements Objetivo{

    private Jugador jugador;
    private HashMap<String, Integer> objetivo;

    public ObjetivoCantidadPorContinente(Jugador unJugador, HashMap<String, Integer> objetivo) {
        this.jugador = unJugador;
        this.objetivo = objetivo;
    }

    public boolean haGanado(Tablero tablero, ArrayList<Jugador> listaJugadores){
        boolean cumpleObjetivo = true;
        for (HashMap.Entry<String, Integer> entry : this.objetivo.entrySet()) {
            String nombreContinente = entry.getKey();
            Integer cantidad = entry.getValue();
            if(tablero.cantidadPaisesDeJugadorEn(this.jugador, nombreContinente) < cantidad)
                cumpleObjetivo = false;
        }
        return cumpleObjetivo;
    }
}
