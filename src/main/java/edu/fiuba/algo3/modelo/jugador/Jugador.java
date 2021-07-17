package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;

import java.util.ArrayList;


public class Jugador {
    private ArrayList<Pais> listaPaises;
    private int numeroJugador;
    private static int numeroDeJugadorSiguiente = 1;

    public Jugador(){
        this.numeroJugador = numeroDeJugadorSiguiente;
        numeroDeJugadorSiguiente++;
        listaPaises = new ArrayList<Pais>();
    }

    public boolean jugadorGano(){
        return false;
    }


    public void asignarPais(Pais unPais){
        this.listaPaises.add(unPais);
    }


    public int cantidadPaises (){
        return listaPaises.size();
    }

}
