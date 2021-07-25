package edu.fiuba.algo3.modelo.flujoDeJuego;

public class JuegoTerminado implements Fase{
    public Fase siguienteFase(Ronda ronda){
        return new JuegoTerminado();
    }
}
