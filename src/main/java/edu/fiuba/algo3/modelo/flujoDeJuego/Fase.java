package edu.fiuba.algo3.modelo.flujoDeJuego;

public interface Fase {
    Fase siguienteFase(Ronda ronda);
    String getNombreRonda();
    String accionARealizar(int cantidadAColocar);
}
