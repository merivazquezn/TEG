package edu.fiuba.algo3.modelo;

public class Dado implements Comparable<Dado>{
    static final int DADO_GANO = 1;
    static final int DADO_EMPATO = 0;
    static final int DADO_PERDIO = -1;

    private IRandomizador randomizador;

    private int numeroDado;

    private void generar(){
        this.numeroDado = this.randomizador.generar(1,6);
    }

    public Dado(IRandomizador randomizador){
        this.randomizador = randomizador;
        generar();
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
