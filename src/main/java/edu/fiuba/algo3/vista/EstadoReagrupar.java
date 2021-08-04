package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuReagrupar;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class EstadoReagrupar {

    private Ronda ronda;
    private Pais origen = null;
    private Pais destino = null;
    private int cantidad = 0;
    private boolean recibeDestino = false;

    public EstadoReagrupar(Ronda ronda){
        this.ronda = ronda;
    }


    public void agregarPais(Pais unPais, int cantidad){

        if(!recibeDestino) {
            if (unPais.getCantidadEjercitos() > 1){
                this.destino = null;
                this.origen = unPais;
                this.recibeDestino = true;
                this.cantidad = cantidad;
            }
        }
        else{
            if(unPais.sonLimitrofes(this.origen)){
                this.destino = unPais;
                ControladorMenuReagrupar.realizarJugada(this.ronda, this.origen, this.destino, this.cantidad);
                this.resetear();
            }
        }
    }

    public boolean visibilizaDestino(int numeroJugadorActual, Pais unPais){
        if(this.recibeDestino)
            return (this.origen.sonLimitrofes(unPais) && numeroJugadorActual == unPais.getJugador().getNumero());
        return false;
    }

    public boolean visibilizaOrigen(int numeroJugadorActual, Pais unPais){
        return (unPais.getCantidadEjercitos() > 1 && numeroJugadorActual == unPais.getJugador().getNumero());
    }

    public boolean paisOrigenIngresado() {
        return this.recibeDestino;
    }

    public void resetear() {
        this.recibeDestino = false;
        this.cantidad = 0;
    }

}
