package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.JugadorNoTieneElPaisException;
import edu.fiuba.algo3.modelo.jugador.Tarjeta;

public class JugadaActivarTarjetas implements Jugada{

    private Tarjeta tarjeta;

    public JugadaActivarTarjetas(Tarjeta tarjeta){
        this.tarjeta = tarjeta;
    }

    public void ejecutar(Tablero tablero, Ronda ronda) {
        try {
            this.tarjeta.activar();
        }
        catch(JugadorNoTieneElPaisException e){
            System.out.println("El jugador intentó activar una tarjeta de un pais que no posee.");
        }
    }
}
