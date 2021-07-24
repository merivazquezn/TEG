package edu.fiuba.algo3.modelo.jugador;

public class MultipleCanje implements CantidadCanjes{

    private int cantCanje;

    private final int factor = 5;

    public MultipleCanje(int cantCanje){
        this.cantCanje = cantCanje;
    }

    public int cantidadAColocarPorCanje(){
        return (this.cantCanje-1) * factor;
    }

    public CantidadCanjes siguiente(){
        return new MultipleCanje(this.cantCanje+1);
    }

}
