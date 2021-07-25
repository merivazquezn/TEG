package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.ArrayList;

public class JugadorNulo extends Jugador {

    public JugadorNulo(){
        super(new ObjetivoNulo());
    }

    @Override
    protected void asignarAtributosDeJugadorValido(){
        this.jugadorQueLoDerroto = null;
    }

    @Override
    public boolean esNulo() {
        return true;
    }

}
