package edu.fiuba.algo3.modelo;

import java.util.LinkedList;

public class Pais {

    private String nombre;
    private LinkedList<Ficha> fichas;

    public Pais(String nombrePais) {
        this.nombre = nombrePais;
        this.fichas = new LinkedList<Ficha>();
    }

    public void colocarFichas(int cantidadFichas) {
        for(int i = 0; i < cantidadFichas; i++) {
            fichas.push(new Ficha());
        }
    }

    public int getCantidadFichas() {
        return fichas.size();
    }

    public void eliminarFichas(int cantidadFichas) {
        for(int i = 0; i < cantidadFichas; i++) {
            fichas.pop();
        }
    }
}
