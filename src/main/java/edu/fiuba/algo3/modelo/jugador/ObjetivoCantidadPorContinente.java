package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;

import java.util.ArrayList;
import java.util.Map;

public class ObjetivoCantidadPorContinente implements Objetivo{

    private Jugador jugador;
    private Map<String, Integer> objetivo;

    public ObjetivoCantidadPorContinente(Jugador unJugador, Map<String, Integer> objetivo) {
        this.jugador = unJugador;
        this.objetivo = objetivo;
    }

    public boolean haGanado(Tablero tablero, ArrayList<Jugador> listaJugadores){
        boolean cumpleObjetivo = true;
        for (Map.Entry<String, Integer> entry : this.objetivo.entrySet()) {
            String nombreContinente = entry.getKey();
            Integer cantidad = entry.getValue();
            if(tablero.cantidadPaisesDeJugadorEn(this.jugador, nombreContinente) < cantidad)
                cumpleObjetivo = false;
        }
        return cumpleObjetivo;
    }
}
