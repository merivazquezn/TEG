package edu.fiuba.algo3.infraestructura;

import java.util.Random;

public class Randomizador implements IRandomizador {
    public int generar(int low, int high){
        Random rand = new Random();
        return rand.nextInt(high)+low;
    }
}
