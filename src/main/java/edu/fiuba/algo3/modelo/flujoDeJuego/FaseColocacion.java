package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseColocacion implements Fase {
    public Fase siguienteFase(Ronda ronda){
        if(ronda.estaAlFinalDeLaLista()) {
            ronda.reiniciarLista();
            return new FaseAtaque();
        }
        ronda.actualizarCantidadAColocar();
        return new FaseColocacion();
    }
}
