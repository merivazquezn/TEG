package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.controlador.EleccionInicial;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.ArrayList;


public class TurnoInicial implements Turno{

    private Jugador jugador;
    private EleccionInicial eleccion;
    private int cantidadAColocar;

    public TurnoInicial(Jugador unJugador, int cantidadAColocar, EleccionInicial eleccion){
        this.jugador = unJugador;
        this.eleccion = eleccion;
        this.cantidadAColocar = cantidadAColocar;
    }
    public boolean realizarTurnoYContinuar(Tablero tablero){
        //VERIFICAR OBJETIVO AQUI
        while(this.cantidadAColocar > 0){
            ArrayList<Object> accion = this.eleccion.colocacionDeEjercitos();
            if((boolean) accion.get(0)){
                Pais paisAColocar = (Pais) accion.get(1);
                int cantidad = (int) accion.get(2);
                if(cantidad <= this.cantidadAColocar) {
                    this.cantidadAColocar -= cantidad;
                    paisAColocar.colocarEjercitos(cantidad);
                }
            }
            else {
                //VER OBJETIVO
            }
        }
        return true;
    }
}


