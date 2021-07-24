package edu.fiuba.algo3.infraestructura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import edu.fiuba.algo3.modelo.general.*;

public class Randomizador implements IRandomizador {
    public int generar(int low, int high){
        Random rand = new Random();
        return rand.nextInt(high)+low;
    }

    public void shuffle(ArrayList<Tarjeta> lista) {
        Collections.shuffle(lista);
    }


}
