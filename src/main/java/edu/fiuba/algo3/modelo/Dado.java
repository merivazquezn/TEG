package edu.fiuba.algo3.modelo;

import java.util.Random;

public class Dado implements Comparable<Dado>{
    static final int DADO_GANO = 1;
    static final int DADO_EMPATO = 0;
    static final int DADO_PERDIO = -1;

    private int numeroDado;

    private void generar(){
        Random rand = new Random();
        this.numeroDado = rand.nextInt(5)+1;
    }

    public Dado(){
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
