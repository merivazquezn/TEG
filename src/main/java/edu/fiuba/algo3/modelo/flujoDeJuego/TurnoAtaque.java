package edu.fiuba.algo3.modelo.flujoDeJuego;
import edu.fiuba.algo3.controlador.EleccionAtaque;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.general.Tablero;
import edu.fiuba.algo3.modelo.jugador.Jugador;

import java.util.ArrayList;

public class TurnoAtaque implements Turno{

    private Jugador atacante;
    private EleccionAtaque eleccion;

    public TurnoAtaque(Jugador atacante, EleccionAtaque eleccion) {
        this.atacante = atacante;
        this.eleccion = eleccion;
    }


    public boolean realizarTurnoYContinuar(Tablero tablero){
        ArrayList<Object> orden = this.eleccion.pedirAtaque();
        while(orden.get(0).toString() != "Terminar"){
            Pais paisAtacante = (Pais) orden.get(0);
            Pais paisDefensor = (Pais) orden.get(1);
            if(tablero.conquisto(paisAtacante, paisDefensor, (int) orden.get(2), new ConstructorDeConjuntoDados())){
                if(this.atacante.jugadorGano())
                    return false;
                int cantidadAMover = this.eleccion.cantidadAMover();
                if(cantidadAMover > 0 && cantidadAMover < paisAtacante.getCantidadEjercitos()) {
                    paisAtacante.transferirEjercitosA(paisDefensor, cantidadAMover);
                }
            }
            orden = this.eleccion.pedirAtaque();
        }
        return true;
    }
}

