package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseInicial3Fichas implements Fase {
    public Fase siguienteFase(Ronda ronda){
        if(ronda.siguienteJugador().esNulo()) {
            ronda.reiniciarLista();
            return new FaseAtaque();
        }
        return new FaseInicial3Fichas();
    }
}
