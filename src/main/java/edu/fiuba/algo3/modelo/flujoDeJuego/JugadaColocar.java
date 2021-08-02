package edu.fiuba.algo3.modelo.flujoDeJuego;
import edu.fiuba.algo3.modelo.general.*;

public class JugadaColocar implements Jugada{

    private Pais pais;
    private int cantidad;

    public JugadaColocar(Pais unPais, int unaCantidad){
        this.pais = unPais;
        this.cantidad = unaCantidad;
    }

    public void ejecutar(Tablero tablero, Ronda ronda){
        if(this.cantidad <= ronda.cantidadAColocar()) {
            this.pais.colocarEjercitos(this.cantidad);
            ronda.seColocaronEjercitos(this.cantidad);
        }
    }


}
