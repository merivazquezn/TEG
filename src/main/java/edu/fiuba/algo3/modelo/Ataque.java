package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Ataque {
    private Pais atacante;
    private Pais defensor;
    private int cantEjercitoAtacantes;
    private int cantEjercitoDefensor;

    private ConjuntoDados conjuntoAtacante;
    private ConjuntoDados conjuntoDefensor;

    public Ataque(Pais paisAtacante, Pais paisDefensor, int cantEjercitosAtacantes){
        this.atacante = paisAtacante;
        this.defensor = paisDefensor;

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

    public boolean ejecutar(ConjuntoDados conjuntoAtacante, ConjuntoDados conjuntoDefensor){
        this.conjuntoAtacante = conjuntoAtacante;
        this.conjuntoAtacante.generar(this.cantEjercitoAtacantes, new Randomizador());
        this.conjuntoDefensor = conjuntoDefensor;
        this.conjuntoDefensor.generar(this.cantEjercitoDefensor, new Randomizador());

        ArrayList<Integer> resultados = conjuntoAtacante.compararCon(conjuntoDefensor);

        this.cantEjercitoDefensor = this.defensor.eliminarEjercitos(resultados.get(1));
        this.cantEjercitoAtacantes = this.atacante.eliminarEjercitos(resultados.get(0));

        // devuelve true si conquisto el pais
        return (cantEjercitoDefensor <= 0);
    }
}