package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.NoSePuedeAvanzarFaseException;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;

public class ControladorInterfaz {

    public static void finalizarTurno(Ronda ronda){
        try {
            ronda.terminar();
        }
        catch(NoSePuedeAvanzarFaseException e){
            System.out.println("Se intentó avanzar y no se podia.");
        }
        ronda.notifyObservers();
    }

}
