package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;

public class ObjetivoNulo implements Objetivo{

    public ObjetivoNulo(){

    }

    public boolean haGanado(Tablero tablero){
        return false;
    }
}
