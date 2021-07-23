package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.controlador.EleccionInicial;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.*;


import java.util.ArrayList;
import java.util.Random;

public class RondaInicial {

    private final Juego juego;
    private final int cantidadDeJugadores;
    private ArrayList<Jugador> listaJugadores;
    private final EleccionInicial eleccion;

    public RondaInicial(Juego juego, EleccionInicial eleccion){
        this.juego = juego;
        this.eleccion = eleccion;
        this.cantidadDeJugadores = this.eleccion.cantidadDeJugadores();
    }

    private void generarListaJugadores(){
        ArrayList<Jugador> listaJugadoresAuxiliar = new ArrayList<>();
        this.listaJugadores = new ArrayList<>();
        for(int i=1; i<= this.cantidadDeJugadores; i++){
            listaJugadoresAuxiliar.add(new Jugador());
        }
        Random rand = new Random();
        int jugadorInicial = rand.nextInt(this.cantidadDeJugadores)+1;
        for(int i=jugadorInicial; i>0; i--){
            this.listaJugadores.add(listaJugadoresAuxiliar.get(i-1));
        }
        for(int i=this.cantidadDeJugadores; i>jugadorInicial; i--){
            this.listaJugadores.add(listaJugadoresAuxiliar.get(i-1));
        }
        this.juego.asignarJugadores(this.listaJugadores);
    }

    public boolean realizarRondaYContinuar(Tablero tablero){
        this.generarListaJugadores();
        int i = 0;
        boolean continuarJuego = true;
        /*while(i< this.cantidadDeJugadores && continuarJuego){
            TurnoInicial turno = new TurnoInicial(this.listaJugadores.get(i), 5, this.eleccion);
            continuarJuego = turno.realizarTurnoYContinuar(tablero);
        }
        for(i = 0; i< this.cantidadDeJugadores;i++){
            TurnoInicial turno = new TurnoInicial(this.listaJugadores.get(i), 3, this.eleccion);
            turno.realizarTurnoYContinuar(tablero);
        }*/
        return continuarJuego;
    }
}
