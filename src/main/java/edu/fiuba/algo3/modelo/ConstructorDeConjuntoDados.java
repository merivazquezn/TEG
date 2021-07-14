package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class ConstructorDeConjuntoDados {

    private IRandomizador randomizador;


    public ConstructorDeConjuntoDados (){
        this.randomizador = new Randomizador();
    }


    public ArrayList<ConjuntoDados> obtenerConjuntosDados (int cantidadAtacante, int cantidadDefensor){
        ConjuntoDados atacante = new ConjuntoDados (cantidadAtacante, randomizador);
        ConjuntoDados defensor = new ConjuntoDados(cantidadDefensor, randomizador);
        ArrayList<ConjuntoDados> conjuntos = new ArrayList<ConjuntoDados>();
        conjuntos.add(atacante);
        conjuntos.add(defensor);
        return conjuntos;
    }
}
