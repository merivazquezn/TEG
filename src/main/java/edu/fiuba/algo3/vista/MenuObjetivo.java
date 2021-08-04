package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;

public class MenuObjetivo extends StackPane {

    private double puntoX;
    private double puntoY;
    private Ronda ronda;
    private Label nombreObjetivo;
    private Label descripcionObjetivo;
    private ImageView interfazObjetivo;

    public MenuObjetivo(Ronda ronda) throws IOException {
        FileInputStream inputImagenInterfaz = new FileInputStream("./src/imagenes/vistaDesplegable.png");
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.interfazObjetivo = new ImageView(imagenInterfaz);
        this.ronda = ronda;
        this.nombreObjetivo = new Label("");
        this.descripcionObjetivo = new Label("");
        this.nombreObjetivo.setTranslateY(-100);
        this.setVisible(false);
        this.getChildren().add(this.interfazObjetivo);
        this.getChildren().add(this.nombreObjetivo);
        this.getChildren().add(this.descripcionObjetivo);
        this.puntoX = 60;
        this.puntoY = 530;
        this.relocate(this.puntoX, this.puntoY);
    }

    public void aparecerMenu(MouseEvent evento){
        if(this.isVisible()){
            ocultarMenu(evento);
        }else{
            Jugador jugadorActual = this.ronda.jugadorActual();
            this.setVisible(true);
            this.nombreObjetivo.setText(jugadorActual.nombreObjetivo());
            this.descripcionObjetivo.setText(jugadorActual.descripcionObjetivo());
        }
    }

    public void ocultarMenu(MouseEvent evento){
        if(!this.adentro(evento.getSceneX(), evento.getSceneY())){
            this.setVisible(false);
        }
    }

    private boolean adentro(double mx, double my){
        return(mx >= this.puntoX && mx <= this.puntoX+100 && my >= this.puntoY && my <= this.puntoY+200);
    }

}
