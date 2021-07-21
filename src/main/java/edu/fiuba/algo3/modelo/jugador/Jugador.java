package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;

import java.util.ArrayList;


public class Jugador {
    private ArrayList<Pais> listaPaises;
    private int numeroJugador;
    private static int numeroDeJugadorSiguiente = 1;
    Jugador jugadorQueLoDerroto;

    public Jugador(){
        this.numeroJugador = numeroDeJugadorSiguiente;
        numeroDeJugadorSiguiente++;
        listaPaises = new ArrayList<Pais>();
        this.asignarJugadorQueDerroto();
    }

    protected void asignarJugadorQueDerroto(){
        this.jugadorQueLoDerroto = new JugadorNulo();
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

    public Jugador jugadorQueLoDerroto(){
        return this.jugadorQueLoDerroto;
    }

    public void perdioPaisAnte(Pais pais, Jugador jugador){
        int i = 0;
        boolean borrado = false;
        while( borrado == false){
            if(this.listaPaises.get(i).equals(pais)){
                borrado = true;
                this.listaPaises.remove(i);
            }
            i++;
        }
        if(this.listaPaises.size() == 0)
            this.jugadorQueLoDerroto = jugador;
    }

}
