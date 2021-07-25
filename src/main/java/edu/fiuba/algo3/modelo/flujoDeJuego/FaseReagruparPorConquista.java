package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseReagruparPorConquista implements Fase{
    public Fase siguienteFase(Ronda ronda){
        return new FaseAtaque();
    }
}
