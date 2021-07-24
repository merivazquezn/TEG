package edu.fiuba.algo3.modelo.jugador;

public class TercerCanje implements CantidadCanjes{
    public int cantidadAColocarPorCanje(){
        return 10;
    }

    public CantidadCanjes siguiente(){
        return new MultipleCanje(4);
    }
}
