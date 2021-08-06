package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.vista.MenuAtaque;
import edu.fiuba.algo3.vista.MenuColocacion;
import edu.fiuba.algo3.vista.MenuReagrupar;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;

public class ControladorEjercito {

    private static ControladorEjercito instancia;
    private Ronda ronda;

    public ControladorEjercito(Ronda ronda){
        this.ronda = ronda;
    }

    public static void crearInstancia(Ronda ronda) throws IOException {
        if(instancia == null){
            instancia = new ControladorEjercito(ronda);
        }
    }

    public static ControladorEjercito obtenerInstancia(){
        return instancia;
    }

    public void tocarPais(MouseEvent e, Pais unPais){
        if(this.ronda.puedeColocar()) {
            MenuColocacion menuColocacion = MenuColocacion.obtenerInstancia();
            menuColocacion.aparecerMenu(e,unPais);
        }
        else if(this.ronda.sePuedeReagrupar()){
            MenuReagrupar menuReagrupar = MenuReagrupar.obtenerInstancia();
            menuReagrupar.aparecerMenu(e,unPais);
        }
        else{
            MenuAtaque menuAtaque = MenuAtaque.obtenerInstancia();
            menuAtaque.aparecerMenu(e,unPais);
        }
    }

}
