package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RepartidorDePaises {

    /*
     * Se reparten los paises de forma equitativa, si la division no es entera, lo que resta se le da al ultimo jugador.
     */

    HashMap<String, Pais> paises;
    ListaJugadores jugadores;

    public RepartidorDePaises (HashMap<String, Pais> paises, ListaJugadores jugadores){
        this.paises = paises;
        this.jugadores = jugadores;
    }

    private ArrayList<Pais> listaDePaisesDesordenada(){
        ArrayList<Pais> listaPaisesDesordenados = new ArrayList<Pais>(this.paises.values());
        Collections.shuffle(listaPaisesDesordenados);

        return listaPaisesDesordenados;
    }


    private int cantidadPaisesPorJugador(){
        return (this.paises.size()/this.jugadores.size());
    }

    public void repartirPaisesPorJugadores(){
        ArrayList<Pais> listaPaisesDesordenados = listaDePaisesDesordenada();
        int aRepartir = cantidadPaisesPorJugador();

        Jugador jugadorActual = this.jugadores.siguiente();
        Pais paisActual = new Pais("");
        int contador = 0;

        for (int i = 0; i < listaPaisesDesordenados.size(); i++){
            listaPaisesDesordenados.get(i).asignarJugador(jugadorActual);
            contador++;

            if(contador == aRepartir){
                contador = 0;
                if((listaPaisesDesordenados.size() - i) > aRepartir)
                    jugadorActual = this.jugadores.siguiente();
            }
        }
        this.jugadores.reiniciar();
    }
}
