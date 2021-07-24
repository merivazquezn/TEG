package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import edu.fiuba.algo3.modelo.jugador.JugadorNulo;

import java.util.ArrayList;
import java.util.Random;

public class ListaJugadores {

    ArrayList<Jugador> listaJugadores;
    int indiceActual;

    public ListaJugadores(ArrayList<Jugador> arrayJugadores, IRandomizador randomizador){
        this.listaJugadores = arrayJugadores;
        this.indiceActual = 0;
        if(this.listaJugadores.size() > 1)
            mezclar(randomizador);
    }

    public Jugador siguiente(){
        if(indiceActual > listaJugadores.size())
            return new JugadorNulo();
        Jugador jugadorActual = listaJugadores.get(indiceActual);
        indiceActual++;
        return jugadorActual;
    }

    public int size(){
        return listaJugadores.size();
    }

    public void reiniciar(){
        this.indiceActual = 0;
    }

    private void mezclar(IRandomizador randomizador){
        int jugadorInicial = randomizador.generar(0,this.listaJugadores.size()-1);
        ArrayList<Jugador> listaJugadoresAuxiliar = new ArrayList<>();
        for(int i=jugadorInicial; i>=0; i--){
            listaJugadoresAuxiliar.add(this.listaJugadores.get(i));
        }
        for(int i=this.listaJugadores.size()-1; i>jugadorInicial; i--){
            listaJugadoresAuxiliar.add(this.listaJugadores.get(i));
        }
        this.listaJugadores = listaJugadoresAuxiliar;
    }

}
