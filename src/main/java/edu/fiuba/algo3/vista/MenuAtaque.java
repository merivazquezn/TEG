package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.general.Pais;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;

public class MenuAtaque extends StackPane {
    private double puntoX;
    private double puntoY;
    private Label etiquetaMenuAtaque;
    private Button botonMenuAtaque;
    private ImageView imagenDesdeAbajo;
    private ImageView imagenDesdeIzquierda;
    private ImageView imagenDesdeArriba;

    private void establecerImagenes() throws IOException{
        FileInputStream inputMenuAtaqueAbajo = new FileInputStream("./src/imagenes/desplegableAbajo.png");
        FileInputStream inputMenuAtaqueIzquierda = new FileInputStream("./src/imagenes/desplegableIzquierda.png");
        FileInputStream inputMenuAtaqueArriba = new FileInputStream("./src/imagenes/desplegableArriba.png");
        Image imagenMenuAtaqueAbajo = new Image(inputMenuAtaqueAbajo);
        Image imagenMenuAtaqueIzquierda = new Image(inputMenuAtaqueIzquierda);
        Image imagenMenuAtaqueArriba = new Image(inputMenuAtaqueArriba);
        this.imagenDesdeAbajo = new ImageView(imagenMenuAtaqueAbajo);
        this.imagenDesdeIzquierda = new ImageView(imagenMenuAtaqueIzquierda);
        this.imagenDesdeArriba = new ImageView(imagenMenuAtaqueArriba);
        this.getChildren().add(this.imagenDesdeAbajo);
        this.getChildren().add(this.imagenDesdeIzquierda);
        this.getChildren().add(this.imagenDesdeArriba);
        this.imagenDesdeAbajo.setVisible(false);
        this.imagenDesdeArriba.setVisible(false);
        this.imagenDesdeIzquierda.setVisible(false);
    }

    public MenuAtaque() throws IOException {
        establecerImagenes();
        this.etiquetaMenuAtaque = new Label("");
        this.etiquetaMenuAtaque.setStyle("-fx-font: 22 arial;");
        this.etiquetaMenuAtaque.setTranslateY(-10);
        this.botonMenuAtaque = new Button("Realizar ataque");
        this.botonMenuAtaque.setTranslateY(20);
        this.getChildren().add(this.etiquetaMenuAtaque);
        this.getChildren().add(this.botonMenuAtaque);
        this.setVisible(false);
    }

    public void aparecerMenu(MouseEvent evento, Pais unPais){
        String nombrePais = unPais.getNombre();
        this.setVisible(true);
        this.aparecer(evento.getSceneX(), evento.getSceneY());
        this.etiquetaMenuAtaque.setText(nombrePais);
        this.imagenDesdeAbajo.setVisible(false);
        this.imagenDesdeArriba.setVisible(false);
        this.imagenDesdeIzquierda.setVisible(false);
        if(evento.getSceneY() < 100){
            this.imagenDesdeArriba.setVisible(true);
        }
        else if(evento.getSceneX() < 100){
            this.imagenDesdeIzquierda.setVisible(true);
        }
        else{
            this.imagenDesdeAbajo.setVisible(true);
        }
    }

    public void ocultarMenu(MouseEvent evento){
        if(this.isVisible()){
            if(!this.adentro(evento.getSceneX(), evento.getSceneY())){
                this.setVisible(false);
            }
        }
    }

    public void aparecer( double mx, double my){
        if(my < 100){
            relocate(mx-100, my+10);
        }
        else if(mx < 100){
            relocate(mx, my-50);
        }
        else{
            relocate(mx-100, my-110);
        }
        this.puntoX = mx;
        this.puntoY = my;
    }

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX-100 && mx <= this.puntoX+100 && my >= this.puntoY-100 && my <= this.puntoY);
    }
}
