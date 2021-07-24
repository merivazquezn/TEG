package edu.fiuba.algo3.modelo.jugador;

public class CeroCanjes implements CantidadCanjes{

    public int cantidadAColocarPorCanje(){
        return 0;
    }

    public CantidadCanjes siguiente(){
        return new PrimerCanje();
    }
}
