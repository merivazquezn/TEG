package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;

public class FaseRecibeOrigen implements FaseEstadoReagrupacion{

    public FaseEstadoReagrupacion agregarPais(Ronda ronda, Pais unPais, int cantidad) {
        if (unPais.getCantidadEjercitos() > 1){
            return new FaseRecibeDestino(unPais, cantidad);
        }
        return this;
    }

    public boolean visibilizarDestino(int numeroJugadorActual, Pais unPais) {
        return false;
    }
}
