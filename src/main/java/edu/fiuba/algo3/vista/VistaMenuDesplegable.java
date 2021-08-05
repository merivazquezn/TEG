package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class VistaMenuDesplegable extends VistaMenu{

    protected ImageView imagenDesdeAbajo;
    protected ImageView imagenDesdeIzquierda;
    protected ImageView imagenDesdeArriba;

    protected void establecerImagenes() throws IOException {
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

    public VistaMenuDesplegable(Ronda ronda, double puntoX, double puntoY) throws IOException{
        super(ronda, puntoX, puntoY);
        establecerImagenes();
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
        this.imagenDesdeAbajo.setVisible(false);
        this.imagenDesdeArriba.setVisible(false);
        this.imagenDesdeIzquierda.setVisible(false);
        if(my < 100){
            this.imagenDesdeArriba.setVisible(true);
        }
        else if(mx < 100){
            this.imagenDesdeIzquierda.setVisible(true);
        }
        else {
            this.imagenDesdeAbajo.setVisible(true);
        }
    }

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX-100 && mx <= this.puntoX+100 && my >= this.puntoY-100 && my <= this.puntoY);
    }

}
