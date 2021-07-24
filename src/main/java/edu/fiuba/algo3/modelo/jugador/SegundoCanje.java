package edu.fiuba.algo3.modelo.jugador;

public class SegundoCanje implements CantidadCanjes{
    private final int cantidadAColocar = 7;
    public int cantidadAColocarPorCanje(){
        return this.cantidadAColocar;
    }

    public CantidadCanjes siguiente(){
        return new MultipleCanje(3);
    }
}
