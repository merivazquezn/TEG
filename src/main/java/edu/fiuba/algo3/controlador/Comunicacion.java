package edu.fiuba.algo3.controlador;

public class Comunicacion {
    EleccionAtaque ataque;

    public Comunicacion (){
        this.ataque = new EleccionAtaque();
    }

    public EleccionAtaque getEleccionAtaque(){
        return this.ataque;
    }
}
