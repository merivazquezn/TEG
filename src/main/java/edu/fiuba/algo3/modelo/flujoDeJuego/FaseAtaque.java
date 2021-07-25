package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseAtaque implements Fase{
    public Fase siguienteFase(Ronda ronda){
            return new FaseReagruparFinal();
    }
}
