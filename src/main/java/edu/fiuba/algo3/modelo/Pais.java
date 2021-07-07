package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Pais {

    private String nombre;
    private ArrayList<Ficha> fichas;

    public Pais(String nombrePais) {
        this.nombre = nombrePais;
        this.fichas = new ArrayList<Ficha>();
    }

    public void colocarFichas(int cantidadFichas) {
        for(int i = 0; i < cantidadFichas; i++) {
            fichas.add(new Ficha());
        }
    }

    public int getCantidadFichas() {
        return fichas.size();
    }

}
