package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.modelo.general.ConjuntoTarjetas;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.general.Tarjeta;

public class JugadaCanjearTarjetas implements Jugada{

    private Tarjeta tarjeta1;
    private Tarjeta tarjeta2;
    private Tarjeta tarjeta3;

    public JugadaCanjearTarjetas(Tarjeta tarjeta1, Tarjeta tarjeta2, Tarjeta tarjeta3){
        this.tarjeta1 = tarjeta1;
        this.tarjeta2 = tarjeta2;
        this.tarjeta3 = tarjeta3;
    }

    public void ejecutar(Tablero tablero, Ronda ronda) {

        ConjuntoTarjetas conjuntoTarjetas = new ConjuntoTarjetas(tarjeta1, tarjeta2, tarjeta3, tablero.obtenerMazo());

        if (conjuntoTarjetas.sePudoCanjear())
            ronda.seRealizoCanje(conjuntoTarjetas);
    }


}
