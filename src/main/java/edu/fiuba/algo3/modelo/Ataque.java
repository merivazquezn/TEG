package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Ataque {
    private Pais atacante;
    private Pais defensor;
    private int cantEjercitoAtacantes;
    private int cantEjercitoDefensor;

    private ConjuntoDados conjuntoAtacante;
    private ConjuntoDados conjuntoDefensor;

    private ConstructorDeConjuntoDados constructor;

    public Ataque(ConstructorDeConjuntoDados constructorConjuntoDados, Pais paisAtacante, Pais paisDefensor, int cantEjercitosAtacantes){
        this.atacante = paisAtacante;
        this.defensor = paisDefensor;

        this.constructor = constructorConjuntoDados;

        cantidadEjercitosAtacantesValido(cantEjercitosAtacantes);
        this.cantEjercitoAtacantes = cantEjercitosAtacantes;
        this.cantEjercitoDefensor = obtenerEjercitosDefensor();
    }


    private void cantidadEjercitosAtacantesValido(int cantEjercitos) {

        if (this.atacante.getCantidadEjercitos()-1 < cantEjercitos)
            throw new CantidadInvalidaDeEjercitosParaAtaqueExeption();

        if (cantEjercitos < 1 || cantEjercitos > 3)
            throw new CantidadInvalidaDeEjercitosParaAtaqueExeption();

    }

    private int obtenerEjercitosDefensor(){
        int cantEjercitos = this.defensor.getCantidadEjercitos();
        if (cantEjercitos > 3) {
            return 3;
        }

        return cantEjercitos;
    }

    public boolean conquisto(){
        ArrayList<ConjuntoDados> conjuntos = this.constructor.obtenerConjuntosDados(this.cantEjercitoAtacantes, this.cantEjercitoDefensor);
        this.conjuntoAtacante = conjuntos.get(0);
        this.conjuntoDefensor = conjuntos.get(1);

        ArrayList<Integer> resultados = conjuntoAtacante.ejercitosPerdidos(conjuntoDefensor);

        this.cantEjercitoAtacantes = this.atacante.eliminarEjercitos(resultados.get(0));
        this.cantEjercitoDefensor = this.defensor.eliminarEjercitos(resultados.get(1));

        return (this.cantEjercitoDefensor <= 0);
    }
}