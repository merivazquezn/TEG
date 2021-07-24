package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.*;

import java.util.ArrayList;


public class Jugador {
    private ArrayList<Pais> listaPaises;
    private int numeroJugador;
    private static int numeroDeJugadorSiguiente = 1;
    Jugador jugadorQueLoDerroto;
    private CantidadCanjes cantidadCanjes;

    static public void reiniciarClase(){
        Jugador.numeroDeJugadorSiguiente = 1;
    }

    public Jugador(){
        listaPaises = new ArrayList<Pais>();
        this.asignarAtributosDeJugadorValido();
        this.cantidadCanjes = new CeroCanjes();
    }

    protected void asignarAtributosDeJugadorValido(){
        this.numeroJugador = numeroDeJugadorSiguiente;
        numeroDeJugadorSiguiente++;
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
        while( borrado == false && i < this.listaPaises.size()){
            if(this.listaPaises.get(i).equals(pais)){
                borrado = true;
                this.listaPaises.remove(i);
            }
            i++;
        }
        if(this.listaPaises.size() == 0)
            this.jugadorQueLoDerroto = jugador;
    }

    public int getNumero() {
        return this.numeroJugador;
    }

}
