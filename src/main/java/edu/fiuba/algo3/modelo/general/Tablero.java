package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.ataque.Ataque;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Tablero {
    private Map<String, Continente> continentes;

    public Tablero(Map<String, Continente> continentes) {
        this.continentes = continentes;
    }

    public boolean conquisto(Pais atacante, Pais defensor, int cantidad, ConstructorDeConjuntoDados constructorConjuntoDados) {
        Ataque ataque = new Ataque(constructorConjuntoDados, atacante, defensor, cantidad);
        return ataque.conquisto();
    }

    public int cantidadEjercitosPorContinente(Jugador jugador) {
        int cantidadEjercitos = 0;
        for (Continente actual : this.continentes.values()){
            cantidadEjercitos += actual.ejercitosParaJugador(jugador);
        }

        return cantidadEjercitos;
    }
    public int cantidadPaisesDeJugadorEn(Jugador jugador, String continente){
        return this.continentes.get(continente).cantidadPaisesDeJugador(jugador);
    }

    public boolean continenteOcupadoPorJugador(Jugador jugador, String continente){
        return this.continentes.get(continente).ocupadoPorJugador(jugador);
    }

}
