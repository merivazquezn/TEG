package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuAtaque;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class EstadoReagrupar {

    private Ronda ronda;
    private Pais dador = null;
    private Pais receptor = null;
    private boolean recibeDador = false;

    public EstadoReagrupar(Ronda ronda){
        this.ronda = ronda;
    }

    public void agregarPais(int jugadorActual, Pais unPais){
        if(unPais.getJugador().getNumero() == jugadorActual && unPais.getCantidadEjercitos() > 1){
            this.receptor = null;
            this.dador = unPais;
            this.recibeDefensor = true;
        }
        else{
            if(unPais.sonLimitrofes(this.atacante)){
                this.defensor = unPais;
                ControladorMenuAtaque.realizarJugada(this.ronda, this.atacante, this.defensor);
                this.resetear();
            }
        }
    }

    public boolean visibilizaDefensor(int numeroJugadorActual, Pais unPais){
        if(this.recibeDefensor)
            return (this.atacante.sonLimitrofes(unPais) && numeroJugadorActual != unPais.getJugador().getNumero());
        return false;
    }

    public boolean visibilizaAtacante(int numeroJugadorActual, Pais unPais){
        return (unPais.getCantidadEjercitos() > 1 && numeroJugadorActual == unPais.getJugador().getNumero());
    }

    public void resetear() {
        this.defensor = null;
        this.atacante = null;
        this.recibeDefensor = false;
    }

}
