package edu.fiuba.algo3.modelo.flujoDeJuego;

public class FaseInicial3Fichas implements Fase {
    public Fase siguienteFase(Ronda ronda){

        if(!ronda.puedeAvanzar()) {
            throw new NoSePuedeAvanzarFaseException();
        }


        if(ronda.estaAlFinalDeLaLista()) {
            ronda.reiniciarLista();
            return new FaseAtaque();
        }
        ronda.establecerCantidadAColocar(3);
        ronda.siguienteJugador();
        return new FaseInicial3Fichas();
    }

    public String getNombreRonda(){
        return "Fase Inicial";
    }

    public String accionARealizar(int cantidadAColocar){
        return "Ejercitos a colocar: "+cantidadAColocar;
    }
}
