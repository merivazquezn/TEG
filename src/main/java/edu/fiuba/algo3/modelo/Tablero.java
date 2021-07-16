package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Tablero {
    // TODO: Tableto tiene paises?¡?¡?¡
    //private ArrayList<Pais> paises;
    private ArrayList<Continente> continentes;

    public Tablero(ArrayList<Continente> continentes) {
        //this.paises = paises;
        this.continentes = continentes;
    }

    public boolean conquisto(Pais atacante, Pais defensor, int cantidad) {
        ConstructorDeConjuntoDados constructorConjuntoDados = new ConstructorDeConjuntoDados();
        Ataque ataque = new Ataque(constructorConjuntoDados, atacante, defensor, cantidad);
        return ataque.conquisto();
    }

    //TODO: Refactorizar por for each
    public int cantidadEjercitosPorContinente(Jugador jugador) {
        int cantidadEjercitos = 0;

        for(int i = 0; i < continentes.size(); i++) {
            cantidadEjercitos += continentes.get(i).ejercitosParaJugador(jugador);
        }

        return cantidadEjercitos;
    }
}
