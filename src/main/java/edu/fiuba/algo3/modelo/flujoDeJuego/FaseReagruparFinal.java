package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseReagruparFinal implements Fase{



    public Fase siguienteFase(Ronda ronda) {

        //TODO: habria que atrapar el error NoQuedanTarjetasException en el controlador; para mostrar un cartel de error;
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
