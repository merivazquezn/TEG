package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseInicial5Fichas implements Fase {
    public Fase siguienteFase(Ronda ronda){
        if(ronda.estaAlFinalDeLaLista()) {
            ronda.reiniciarLista();
            ronda.establecerCantidadAColocar(3);
            return new FaseInicial3Fichas();
        }
        ronda.establecerCantidadAColocar(5);
        return new FaseInicial5Fichas();
    }

    public String getNombreRonda(){
        return "Fase Inicial";
    }

    public String accionARealizar(int cantidadAColocar){
        return "Ejercitos a colocar: "+cantidadAColocar;
    }
}
