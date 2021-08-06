package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuAtaque;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class EstadoAtaque {

    private Ronda ronda;
    private FaseEstadoAtaque faseAtaque;

    public EstadoAtaque(Ronda ronda){
        this.ronda = ronda;
        this.faseAtaque = new FaseRecibeAtacante(this.ronda);
    }

    public void agregarPais(int jugadorActual, Pais unPais){
        this.faseAtaque = this.faseAtaque.agregarPais(jugadorActual, unPais);
    }

    public boolean visibilizaDefensor(int numeroJugadorActual, Pais unPais){
        return this.faseAtaque.visibilizaDefensor(numeroJugadorActual, unPais);
    }

    public boolean visibilizaAtacante(int numeroJugadorActual, Pais unPais){
        return (unPais.getCantidadEjercitos() > 1 && numeroJugadorActual == unPais.getJugador().getNumero());
    }

    public void resetear() {
        this.faseAtaque = new FaseRecibeAtacante(this.ronda);
    }

}
