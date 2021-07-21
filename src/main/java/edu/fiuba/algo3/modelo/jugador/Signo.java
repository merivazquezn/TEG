package edu.fiuba.algo3.modelo.jugador;


public class Signo {

    //0 para Barco; 1 para Globo; 2 para Cañon
    private final int idMax = 2;
    private final int idMin = -1;
    private int identificador;


    public Signo(int id) {
        if(id > this.idMax || id < this.idMin) {
            throw new IdentificadorInvalidoException();
        }
        this.identificador = id;
    }

    public int getIdentificador( ) {
        return this.identificador;
    }

    public boolean compararIdentificador(Signo otroSigno) {
        return this.identificador == otroSigno.getIdentificador();
    }

    public boolean mismoSignoQue(Signo otroSigno) {
        if(otroSigno.compararIdentificador(this)) {
            return true;
        };

        return this.identificador == otroSigno.getIdentificador();
    }


}
