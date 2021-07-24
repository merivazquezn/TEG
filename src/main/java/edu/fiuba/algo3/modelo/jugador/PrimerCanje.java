package edu.fiuba.algo3.modelo.jugador;

public class PrimerCanje implements CantidadCanjes{
    final int cantidadAColocar = 4;
    public int cantidadAColocarPorCanje(){
        return this.cantidadAColocar;
    }
    public CantidadCanjes siguiente(){
        return new SegundoCanje();
    }
}
