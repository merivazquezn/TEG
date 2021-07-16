package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<Pais> paises;
    private ArrayList<Continente> continentes;

    public Tablero(ArrayList<Pais> paises, ArrayList<Continente> continentes) {
        this.paises = paises;
        this.continentes = continentes;
    }

    public boolean conquisto(Pais atacante, Pais defensor, int cantidad) {
        ConstructorDeConjuntoDados constructorConjuntoDados = new ConstructorDeConjuntoDados();


        Ataque ataque = new Ataque(constructorConjuntoDados, atacante, defensor, cantidad);
        boolean pudoConquistar = ataque.conquisto();

        return pudoConquistar;
    }
}
