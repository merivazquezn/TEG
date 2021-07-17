package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;

import java.util.ArrayList;


public class Jugador {
    ArrayList<Pais> listaPaises;

    public Jugador(){
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
