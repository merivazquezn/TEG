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

    public void establecerJugadores(ArrayList<Jugador> listaJugadores, int indiceJugador){
        this.jugador = listaJugadores.get(indiceJugador);
        if(indiceJugador > 0)
            this.otroJugador = listaJugadores.get(indiceJugador-1);
        else
            this.otroJugador = listaJugadores.get(indiceJugador+1);
    }

    public boolean haGanado(Tablero tablero){
        if(this.jugador.esNulo() || this.otroJugador.esNulo())
            return false;
        return (this.jugador.equals(this.otroJugador.jugadorQueLoDerroto()));
    }

}
