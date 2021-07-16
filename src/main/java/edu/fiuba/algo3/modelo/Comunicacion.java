package edu.fiuba.algo3.modelo;

import java.lang.reflect.Array;

public class Comunicacion {
    EleccionAtaque ataque;

    public Comunicacion (){
        this.ataque = new EleccionAtaque();
    }

    public EleccionAtaque getEleccionAtaque(){
        return this.ataque;
    }
}
