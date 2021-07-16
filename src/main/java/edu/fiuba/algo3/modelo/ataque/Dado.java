package edu.fiuba.algo3.modelo.ataque;

public class Dado implements Comparable<Dado>{
    static final int DADO_GANO = 1;
    static final int DADO_EMPATO = 0;
    static final int DADO_PERDIO = -1;
    static final int NUM_MIN = 1;
    static final int NUM_MAX = 6;

    private IRandomizador randomizador;

    private int numeroDado;

    public Dado(IRandomizador randomizador){
        this.randomizador = randomizador;
        this.numeroDado = this.randomizador.generar(NUM_MIN, NUM_MAX);
    }

    public int obtenerValor() {
        return this.numeroDado;
    }

    @Override
    public int compareTo(Dado dado){
        return compararConDado(dado);
    }

    public int compararConDado(Dado dado){
        return Integer.compare(this.numeroDado, dado.obtenerValor());
    }

}
