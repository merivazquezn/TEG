package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuAtaque;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class FaseRecibeDefensor implements FaseEstadoAtaque{

    private Pais atacante;
    private Ronda ronda;

    public FaseRecibeDefensor(Ronda ronda, Pais atacante){
        this.ronda = ronda;
        this.atacante = atacante;
    }

    public FaseEstadoAtaque agregarPais(int jugadorActual, Pais unPais){
        if(paisDelJugadorConEjercitosSuficientes(jugadorActual, unPais)){
            return new FaseRecibeDefensor(this.ronda, unPais);
        }
        else{
            if(unPais.sonLimitrofes(this.atacante)){
                ControladorMenuAtaque.realizarJugada(this.ronda, this.atacante, unPais);
                return new FaseRecibeAtacante(this.ronda);
            }
        }
        return this;
    }

    private boolean paisDelJugadorConEjercitosSuficientes(int jugadorActual, Pais unPais) {
        return unPais.getJugador().getNumero() == jugadorActual && unPais.getCantidadEjercitos() > 1;
    }

    public boolean visibilizaDefensor(int jugadorActual, Pais unPais) {
        return (this.atacante.sonLimitrofes(unPais) && jugadorActual != unPais.getJugador().getNumero());
    }
}
