package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseReagruparPorConquista implements Fase{
    public Fase siguienteFase(Ronda ronda){
        return new FaseAtaque();
    }

    public String getNombreRonda() {
        return "Fase Reagrupar";
    }

    public String accionARealizar(int cantidadAColocar){
        return "Realice reagrupes entre países limítrofes.";
    }
}
