package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public interface IConjuntoDados {
    void generar(int cantidadEjercitos, IRandomizador randomizador);
    ArrayList<Integer> compararCon(IConjuntoDados conjuntoDefensor);
    ArrayList<Dado> obtenerDados();
}
