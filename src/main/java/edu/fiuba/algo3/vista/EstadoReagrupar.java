package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuReagrupar;
import edu.fiuba.algo3.modelo.flujoDeJuego.Fase;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class EstadoReagrupar {

    private Ronda ronda;
    private FaseEstadoReagrupacion faseReagrupacion;

    public EstadoReagrupar(Ronda ronda){
        this.ronda = ronda;
        this.faseReagrupacion = new FaseRecibeOrigen();
    }

    public void agregarPais(Pais unPais, int cantidad){
        this.faseReagrupacion = this.faseReagrupacion.agregarPais(this.ronda, unPais, cantidad);
    }

    public boolean visibilizaDestino(int numeroJugadorActual, Pais unPais){
        return this.faseReagrupacion.visibilizarDestino(numeroJugadorActual, unPais);
    }

    public boolean visibilizaOrigen(int numeroJugadorActual, Pais unPais){
        this.resetear();
        return (unPais.getCantidadEjercitos() > 1 && numeroJugadorActual == unPais.getJugador().getNumero());
    }

    public void resetear(){
        this.faseReagrupacion = new FaseRecibeOrigen();
    }

    public FaseEstadoReagrupacion faseActual(){
        return this.faseReagrupacion;
    }

}
