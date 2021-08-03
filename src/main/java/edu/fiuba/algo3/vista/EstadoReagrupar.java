package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class EstadoReagrupar {

    private Ronda ronda;
    private Pais origen = null;
    private Pais destino = null;
    private boolean recibeReceptor = false;
    private boolean recibeDestino = false;

    public EstadoReagrupar(Ronda ronda){
        this.ronda = ronda;
    }

    public void agregarPais(Pais unPais){
        if(!recibeReceptor && unPais.getCantidadEjercitos() > 1){
            this.destino = null;
            this.origen = unPais;
            this.recibeDestino = true;
        }
        else{
            if(unPais.sonLimitrofes(this.origen)){
                this.destino = unPais;
                //TODO: ControladorMenuReagrupar.realizarJugada(this.ronda, this.atacante, this.defensor);
                this.resetear();
            }
        }
    }

    public boolean visibilizaReceptor(int numeroJugadorActual, Pais unPais){
        if(this.recibeReceptor)
            return (this.origen.sonLimitrofes(unPais) && numeroJugadorActual == unPais.getJugador().getNumero());
        return false;
    }

    public boolean visibilizaDador(int numeroJugadorActual, Pais unPais){
        return (unPais.getCantidadEjercitos() > 1 && numeroJugadorActual == unPais.getJugador().getNumero());
    }

    public boolean paisOrigenIngresado() {
        return recibeDestino;
    }

    public void resetear() {
        this.destino = null;
        this.origen = null;
        this.recibeReceptor = false;
    }

}
