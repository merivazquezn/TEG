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

public class MenuColocacion extends StackPane {
    private double puntoX;
    private double puntoY;
    private Label etiquetaMenuAtaque;
    private Button botonMenuAtaque;

    public MenuColocacion() throws IOException {
        FileInputStream inputMenuAtaque = new FileInputStream("./src/imagenes/desplegableAbajo.png");
        Image imagenMenuAtaque = new Image(inputMenuAtaque);
        ImageView vistaImagenMenuAtaque = new ImageView(imagenMenuAtaque);
        this.getChildren().add(vistaImagenMenuAtaque);
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
    }

    public void ocultarMenu(MouseEvent evento){
        if(this.isVisible()){
            if(!this.adentro(evento.getSceneX(), evento.getSceneY())){
                this.setVisible(false);
            }
        }
    }

    public void aparecer( double mx, double my){
        relocate(mx-100, my-110);
        this.puntoX = mx;
        this.puntoY = my;
    }

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX-100 && mx <= this.puntoX+100 && my >= this.puntoY-100 && my <= this.puntoY);
    }
}
