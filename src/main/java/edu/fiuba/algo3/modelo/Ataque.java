package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Ataque {
    private Pais atacante;
    private Pais defensor;
    private int cantEjercitoAtacantes;
    private int cantEjercitoDefensor;

    private IConjuntoDados conjuntoAtacante;
    private IConjuntoDados conjuntoDefensor;

    public Ataque(Pais paisAtacante, Pais paisDefensor, int cantEjercitosAtacantes){
        this.atacante = paisAtacante;
        this.defensor = paisDefensor;

        cantidadEjercitosAtacantesValido(cantEjercitosAtacantes);
        this.cantEjercitoAtacantes = cantEjercitosAtacantes;
        this.cantEjercitoDefensor = obtenerEjercitosDefensor();
    }


    private void cantidadEjercitosAtacantesValido(int cantEjercitos) {

        if (this.atacante.getCantidadFichas()-1 < cantEjercitos)
            throw new CantidadInvalidaDeEjercitosParaAtaqueExeption();

        if (cantEjercitos < 1 || cantEjercitos > 3)
            throw new CantidadInvalidaDeEjercitosParaAtaqueExeption();

    }

    private int obtenerEjercitosDefensor(){
        int cantEjercitos = this.defensor.getCantidadFichas();
        if (cantEjercitos > 3) {
            return 3;
        }

        return cantEjercitos;
    }

    public boolean ejecutar(IConjuntoDados conjuntoAtacante, IConjuntoDados conjuntoDefensor){
        this.conjuntoAtacante = conjuntoAtacante;
        this.conjuntoAtacante.generar(this.cantEjercitoAtacantes, new Randomizador());
        this.conjuntoDefensor = conjuntoDefensor;
        this.conjuntoDefensor.generar(this.cantEjercitoDefensor, new Randomizador());

        ArrayList<Integer> resultados = conjuntoAtacante.compararCon(conjuntoDefensor);

        this.cantEjercitoDefensor = this.defensor.eliminarFichas(resultados.get(1));
        this.cantEjercitoAtacantes = this.atacante.eliminarFichas(resultados.get(0));

        // devuelve true si conquisto el pais
        return (cantEjercitoDefensor <= 0);
    }
}



