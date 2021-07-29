package edu.fiuba.algo3.vista;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MenuAtaque extends StackPane {
    private double puntoX;
    private double puntoY;
    private Label etiquetaMenuAtaque;
    private Button botonMenuAtaque;

    public MenuAtaque(){
        this.etiquetaMenuAtaque = new Label("");
        this.etiquetaMenuAtaque.setStyle("-fx-font: 22 arial;");
        this.etiquetaMenuAtaque.setTranslateY(-10);
        this.botonMenuAtaque = new Button("Realizar ataque");
        this.botonMenuAtaque.setTranslateY(20);
        this.getChildren().add(this.etiquetaMenuAtaque);
        this.getChildren().add(this.botonMenuAtaque);
    }

    public void aparecer( double mx, double my){
        relocate(mx-100, my-110);
        this.puntoX = mx;
        this.puntoY = my;
    }

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX-100 && mx <= this.puntoX+100 && my >= this.puntoY-100 && my <= this.puntoY);
    }

    public void setNombrePais(String nombrePais){
        this.etiquetaMenuAtaque.setText(nombrePais);
    }
}
