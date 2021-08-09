package edu.fiuba.algo3.modelo.jugador;
import edu.fiuba.algo3.modelo.general.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjetivoGeneral implements Objetivo{
    private Jugador jugador;
    private static int cantidadPaisesParaGanar = 30;


    public ObjetivoGeneral(Jugador jugador){
        this.jugador = jugador;
    }


    public boolean haGanado(Tablero tablero){
        return(this.jugador.cantidadPaises() >= this.cantidadPaisesParaGanar);
    }

    public static void cambiarCantidadPaisesParaDosJugadores() {
        ObjetivoGeneral.cantidadPaisesParaGanar = 40;
    }

    public void establecerJugadores(ArrayList<Jugador> listaJugadores, int indiceJugador){
        this.jugador = listaJugadores.get(indiceJugador);
    }

    public String nombreObjetivo(){
        return "Objetivo General";
    }

    public String descripcionObjetivo(){
        return "Conquistar " + cantidadPaisesParaGanar + " paises\n";
    }

}
