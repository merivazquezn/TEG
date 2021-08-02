package edu.fiuba.algo3.modelo.flujoDeJuego;

public class JuegoTerminado implements Fase{
    public Fase siguienteFase(Ronda ronda){
        return new JuegoTerminado();
    }

    public String getNombreRonda() {
        return "Juego Terminado";
    }

    public String accionARealizar(int cantidadAColocar){
        return "Juego Terminado";
    }
}
