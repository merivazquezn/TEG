package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseReagruparFinal implements Fase{



    public Fase siguienteFase(Ronda ronda) {

        ronda.habilitarTarjetaPorConquista();

        if(ronda.estaAlFinalDeLaLista()) {
            ronda.reiniciarLista();
            ronda.actualizarCantidadAColocar();
            return new FaseColocacion();
        }
        ronda.siguienteJugador();
        return new FaseAtaque();
    }

    public String getNombreRonda(){
        return "Fase Reagrupación";
    }

    public String accionARealizar(int cantidadAColocar){
        return "Realice reagrupes entre paises limitrofes";
    }
}
