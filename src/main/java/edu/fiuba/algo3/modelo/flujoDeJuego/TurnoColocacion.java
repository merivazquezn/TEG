package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.controlador.EleccionAtaque;
import edu.fiuba.algo3.controlador.EleccionColocacion;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.*;

import java.util.ArrayList;

public class TurnoColocacion implements Turno{
/*
    private Jugador jugador;
    private EleccionColocacion eleccion;

    public TurnoColocacion(Jugador unJugador, EleccionColocacion eleccion){
        this.jugador = unJugador;
        this.eleccion = eleccion;
    }

    public boolean realizarTurnoYContinuar(Tablero tablero){
        int cantidadAColocar = this.jugador.cantidadPaises();
        cantidadAColocar /= 2;
        cantidadAColocar += tablero.cantidadEjercitosPorContinente(this.jugador);
        while(cantidadAColocar > 0){
            if(eleccion.canjeoDeTarjetas()){
                Tarjeta tarjetaACanjear = eleccion.tarjetaACanjear();
                tarjetaACanjear.activar();
            }
            else {
                ArrayList<Object> listaElecciones = this.eleccion.ejercitosAColocarEnPais();
                Pais paisAColocar = (Pais) listaElecciones.get(0);
                int cantidadPedida = (int) listaElecciones.get(1);
                if (cantidadPedida <= cantidadAColocar) {
                    paisAColocar.colocarEjercitos(cantidadPedida);
                    cantidadAColocar -= cantidadPedida;
                }
            }
        }
        return true;
    }
    */
}
