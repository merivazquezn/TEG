package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseColocacion implements Fase {

    public Fase siguienteFase(Ronda ronda){

        if(!ronda.puedeAvanzar()) {
            throw new NoSePuedeAvanzarFaseException();
        }

        if(ronda.estaAlFinalDeLaLista()) {
            ronda.reiniciarLista();
            return new FaseAtaque();
        }
        ronda.siguienteJugador();
        ronda.actualizarCantidadAColocar();
        return new FaseColocacion();
    }


    public String getNombreRonda(){
        return "Ronda Colocación";
    }

    public String accionARealizar(int cantidadAColocar){
        return "Ejercitos a colocar: " + cantidadAColocar;
    }
}
