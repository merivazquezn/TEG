package edu.fiuba.algo3.modelo.jugador;
import edu.fiuba.algo3.modelo.general.*;
import java.util.ArrayList;

public class ObjetivoGeneral implements Objetivo{
    private Jugador jugador;

    public ObjetivoGeneral(Jugador jugador){
        this.jugador = jugador;
    }
    public boolean haGanado(Tablero tablero, ArrayList<Jugador> listaJugadores){
        return(this.jugador.cantidadPaises() >= 30);
    }
}
