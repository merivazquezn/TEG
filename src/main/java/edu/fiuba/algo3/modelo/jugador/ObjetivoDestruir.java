package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;
import java.util.ArrayList;

public class ObjetivoDestruir implements Objetivo {
    public Jugador jugador;
    public Jugador otroJugador;

    public ObjetivoDestruir (){
        this.jugador = new JugadorNulo();
        this.otroJugador = new JugadorNulo();
    }

    public void setJugadorAtacante(Jugador jugador) {

        this.jugador = jugador;
    }

    public void setJugadorObjetivo(Jugador jugadorObjetivo) {

        this.otroJugador = jugadorObjetivo;
    }


    public boolean haGanado(Tablero tablero){
        if(this.jugador.esNulo() || this.otroJugador.esNulo())
            return false;
        return (this.jugador.equals(this.otroJugador.jugadorQueLoDerroto()));
    }

}
