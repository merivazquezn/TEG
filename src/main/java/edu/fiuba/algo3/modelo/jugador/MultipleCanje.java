package edu.fiuba.algo3.modelo.jugador;

public class MultipleCanje implements CantidadCanjes{

    private int cantCanje;

    public MultipleCanje(int cantCanje){
        this.cantCanje = cantCanje;

    }
    public int cantidadAColocarPorCanje(){
        return (this.cantCanje-1)*5;
    }

    public CantidadCanjes siguiente(){
        return new MultipleCanje(cantCanje+1);
    }
}
