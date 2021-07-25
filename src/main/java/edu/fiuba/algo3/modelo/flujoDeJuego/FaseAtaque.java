package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseAtaque implements Fase{
    public Fase siguienteFase(Ronda ronda){
            if(ronda.siguienteJugador().esNulo()) {
                    ronda.reiniciarLista();
                    return new FaseColocacion();
            }
            return new FaseAtaque();
    }
}
