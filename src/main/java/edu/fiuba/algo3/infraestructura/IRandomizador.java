package edu.fiuba.algo3.infraestructura;

import java.util.ArrayList;
import edu.fiuba.algo3.modelo.general.*;

public interface IRandomizador {
    int generar(int low, int high);
    void shuffle(ArrayList<Tarjeta> lista);
}
