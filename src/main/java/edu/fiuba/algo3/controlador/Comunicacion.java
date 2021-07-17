package edu.fiuba.algo3.controlador;

public class Comunicacion {
    EleccionAtaque ataque;
    EleccionColocacion colocacion;

    public Comunicacion (){

        this.ataque = new EleccionAtaque();
        this.colocacion = new EleccionColocacion();
    }

    public EleccionAtaque getEleccionAtaque(){
        return this.ataque;
    }

    public EleccionColocacion getEleccionColocacion() { return this.colocacion; }


}
