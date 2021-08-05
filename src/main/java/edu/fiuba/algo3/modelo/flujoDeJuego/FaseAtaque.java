package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseAtaque implements Fase{
    public Fase siguienteFase(Ronda ronda){
            ronda.puedeReagrupar();
            return new FaseReagruparFinal();
    }


    public String getNombreRonda(){
        return "Ronda de Ataque";
    }

    public String accionARealizar(int cantidadAColocar){
        return "Seleccione un país para atacar";
    }
}
