package edu.fiuba.algo3.modelo.jugador;

public class SegundoCanje implements CantidadCanjes{
    public int cantidadAColocarPorCanje(){
        return 7;
    }

    public CantidadCanjes siguiente(){
        return new TercerCanje();
    }
}
