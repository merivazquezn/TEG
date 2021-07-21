package edu.fiuba.algo3.modelo.jugador;

public class SignoComodin extends Signo {

    public SignoComodin() {
        super(-1);
    }

    @Override
    public boolean compararIdentificador(Signo otroSigno) {
        return true;
    }

    @Override
    public boolean mismoSignoQue(Signo otroSigno) {
        return true;
    }
}
