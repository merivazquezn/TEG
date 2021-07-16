package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Pais;

public class Tarjeta {


    private final Pais pais;
    private Jugador jugador;

    public Tarjeta(Pais pais) {
        this.pais = pais;
        this.jugador = new JugadorNulo();
    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;
    }


    public void activar() {
        if(!this.pais.esDeJugador(this.jugador)) {
            throw new JugadorNoTieneElPaisException();
        }
        pais.colocarEjercitos(2);
    }
}
