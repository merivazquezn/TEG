package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public interface FaseEstadoReagrupacion {
    FaseEstadoReagrupacion agregarPais(Ronda ronda, Pais unPais, int cantidad);
    boolean visibilizarDestino(int numeroJugadorActual, Pais unPais);
}
