package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuAtaque;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class FaseRecibeAtacante implements FaseEstadoAtaque{

    private Ronda ronda;

    public FaseRecibeAtacante(Ronda ronda){
        this.ronda = ronda;
    }

    public FaseEstadoAtaque agregarPais(int jugadorActual, Pais unPais){
        if(paisDelJugadorConEjercitosSuficientes(jugadorActual, unPais)){
            return new FaseRecibeDefensor(this.ronda, unPais);
        }
        return this;
    }

    private boolean paisDelJugadorConEjercitosSuficientes(int jugadorActual, Pais unPais) {
        return unPais.getJugador().getNumero() == jugadorActual && unPais.getCantidadEjercitos() > 1;
    }

    public boolean visibilizaDefensor(int jugadorActual, Pais unPais){
        return false;
    }

}
