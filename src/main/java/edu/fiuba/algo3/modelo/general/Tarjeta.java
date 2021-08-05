package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.JugadorNoTieneElPaisException;
import edu.fiuba.algo3.modelo.jugador.JugadorNulo;

public class Tarjeta {


    private final Pais pais;
    private Jugador jugador;
    private Signo signo;
    private boolean fueActivada;


    public Tarjeta(Pais pais, Signo signo) {
        this.pais = pais;
        this.jugador = new JugadorNulo();
        this.signo = signo;
        this.fueActivada = false;
    }

    public void asignarJugador(Jugador jugador) {
        this.jugador = jugador;
    }


    public void activar() {
        if(!this.pais.esDeJugador(this.jugador)) {
            throw new JugadorNoTieneElPaisException();
        }
        if(!this.fueActivada)
            pais.colocarEjercitos(2);
        this.fueActivada = true;
    }

    public boolean mismoSignoQue(Tarjeta tarjeta) {
        return this.signo.mismoSignoQue(tarjeta.obtenerSigno());
    }

    public Signo obtenerSigno() {
        return this.signo;
    }

    public Pais getPais() {
        return this.pais;
    }

    public Jugador getJugador() { return this.jugador; }

    public void desactivar(){
        this.fueActivada = false;
    }
    public boolean fueActivada(){
        return this.fueActivada;
    }
}


