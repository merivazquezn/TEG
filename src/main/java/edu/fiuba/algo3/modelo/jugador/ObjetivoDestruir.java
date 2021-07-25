package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;
import java.util.ArrayList;

public class ObjetivoDestruir implements Objetivo {
    public Jugador jugador;
    public Jugador otroJugador;

    public ObjetivoDestruir (){
    }

    public void setJugadorAtacante(Jugador jugador) {

        this.jugador = jugador;
    }

    public void setJugadorObjetivo(Jugador jugadorObjetivo) {

        this.otroJugador = jugadorObjetivo;
    }


    public boolean haGanado(Tablero tablero, ArrayList<Jugador> listaJugadores){
        return (this.jugador.equals(this.otroJugador.jugadorQueLoDerroto()));
    }

}
