package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.general.Pais;

public interface FaseEstadoAtaque {
    FaseEstadoAtaque agregarPais(int jugadorActual, Pais pais);
    boolean visibilizaDefensor(int jugadorActual, Pais pais);
}
