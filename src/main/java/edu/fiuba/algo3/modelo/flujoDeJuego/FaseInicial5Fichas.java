package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseInicial5Fichas implements Fase {
    public Fase siguienteFase(Ronda ronda){
        if(ronda.siguienteJugador().esNulo()) {
            ronda.reiniciarLista();
            return new FaseInicial3Fichas();
        }
        return new FaseInicial5Fichas();
    }
}
