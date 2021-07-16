package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class RondaAtaque implements Ronda{
    private ArrayList <Jugador> jugadores;
    private EleccionAtaque eleccionAtaque;

    public RondaAtaque(ArrayList <Jugador> jugadores, Comunicacion comunicacion){
        this.jugadores = jugadores;
        this.eleccionAtaque = comunicacion.getEleccionAtaque();
    }

    public boolean realizarRondaYContinuar(Tablero tablero){
        int i = 0;
        boolean continuarJuego = true;
        while(i < this.jugadores.size() && continuarJuego){
            TurnoAtaque turnoAtaque = new TurnoAtaque(this.jugadores.get(i), this.eleccionAtaque);
            continuarJuego = turnoAtaque.realizarTurnoYContinuar(tablero);
            if(continuarJuego){
                //TurnoAtaque turnoAtaque = new TurnoAtaque(this.jugadores.get(i), this.eleccionAtaque);
            }
            i++;
        }
        return continuarJuego;
    }

}
