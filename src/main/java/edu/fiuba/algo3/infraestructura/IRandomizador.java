package edu.fiuba.algo3.infraestructura;

import java.util.ArrayList;
import java.util.Collections;

import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Objetivo;

public interface IRandomizador {
    int generar(int low, int high);
    void shuffle(ArrayList<Tarjeta> lista);
    void mezclarObjetivos(ArrayList<Objetivo> lista);

}
