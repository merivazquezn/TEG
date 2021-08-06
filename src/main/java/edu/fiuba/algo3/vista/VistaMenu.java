package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public abstract class VistaMenu extends StackPane {
    protected double puntoX;
    protected double puntoY;
    protected Ronda ronda;

    public VistaMenu(Ronda ronda, double puntoX, double puntoY){
        this.ronda = ronda;
        this.puntoX = puntoX;
        this.puntoY = puntoY;
        relocate(this.puntoX, this.puntoY);
        this.setVisible(false);
    }

    public void ocultarMenu(MouseEvent evento){
        if(!this.adentro(evento.getSceneX(), evento.getSceneY())){
            this.setVisible(false);
        }
    }

    public abstract boolean adentro(double mx, double my);

}
