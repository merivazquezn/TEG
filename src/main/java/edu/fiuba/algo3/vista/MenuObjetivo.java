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

public class MenuObjetivo extends VistaMenu {
    private Label nombreObjetivo;
    private Label descripcionObjetivo;
    private ImageView interfazObjetivo;

    public MenuObjetivo(Ronda ronda) throws IOException {
        super(ronda, 60, 530);
        FileInputStream inputImagenInterfaz = new FileInputStream("./src/imagenes/vistaDesplegable.png");
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.interfazObjetivo = new ImageView(imagenInterfaz);
        this.nombreObjetivo = new Label("");
        this.nombreObjetivo.setStyle("-fx-text-fill: #f2f2e9; -fx-font-weight: bold;");
        this.descripcionObjetivo = new Label("");
        this.descripcionObjetivo.setStyle("-fx-text-fill: #f2f2e9;");
        this.nombreObjetivo.setTranslateY(-100);
        this.getChildren().add(this.interfazObjetivo);
        this.getChildren().add(this.nombreObjetivo);
        this.getChildren().add(this.descripcionObjetivo);
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

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX && mx <= this.puntoX+100 && my >= this.puntoY && my <= this.puntoY+200);
    }

}
