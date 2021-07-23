package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tablero;

public class JugadaTransferir implements Jugada{

    private Pais origen;
    private Pais destino;
    private int cantidad;

    public JugadaTransferir(Pais origen, Pais destino, int cantidad){
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }

    public void ejecutar(Tablero tablero, Ronda ronda) {
        this.origen.transferirEjercitosA(this.destino, this.cantidad);
    }
}
