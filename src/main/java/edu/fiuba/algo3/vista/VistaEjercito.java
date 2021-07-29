package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.general.Pais;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VistaEjercito implements Observer {
    private Circle circuloEjercito;
    private Label cantidadEjercito;

    public VistaEjercito(Pais unPais, MenuAtaque menuAtaque){
        this.circuloEjercito = new Circle();
        this.cantidadEjercito = new Label("1");
        this.cantidadEjercito.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            menuAtaque.aparecerMenu(e, unPais);
            e.consume();
        });
        this.circuloEjercito.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            menuAtaque.aparecerMenu(e, unPais);
            e.consume();
        });
        this.circuloEjercito.setRadius(10.0f);
        this.circuloEjercito.setFill(Color.YELLOW);
    }

    public void setCenterX(double cX){
        this.circuloEjercito.setCenterX(cX);
        this.cantidadEjercito.setTranslateX(cX-4);
    }

    public void setCenterY(double cY){
        this.circuloEjercito.setCenterY(cY);
        this.cantidadEjercito.setTranslateY(cY-6);
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Integer> informacion = (ArrayList<Integer>) arg;
        int nuevoColor = informacion.get(0);
        int cantidadEjercitos = informacion.get(1);
    }
}
