package edu.fiuba.algo3.modelo.jugador;

public class PrimerCanje implements CantidadCanjes{

    public int cantidadAColocarPorCanje(){
        return 4;
    }
    public CantidadCanjes siguiente(){
        return new SegundoCanje();
    }
}
