package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuColocacion;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Integer.parseInt;

public class MenuAtaque extends StackPane implements Observer {
    private double puntoX;
    private double puntoY;
    private Label etiquetaMenuAtaque;
    private Button botonMenuAtaque;
    private Button botonCancelar;
    private ImageView imagenDesdeAbajo;
    private ImageView imagenDesdeIzquierda;
    private ImageView imagenDesdeArriba;

    private EstadoAtaque estadoActual;

    private Pais paisActual;
    private Ronda ronda;
    private int jugadorActual;

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

    public MenuAtaque(Ronda ronda) throws IOException {
        establecerImagenes();
        this.ronda = ronda;
        this.estadoActual = new EstadoAtaque(ronda);
        this.etiquetaMenuAtaque = new Label("");
        this.etiquetaMenuAtaque.setStyle("-fx-font: 22 arial;");
        this.etiquetaMenuAtaque.setTranslateY(-40);
        this.botonMenuAtaque = new Button("Realizar ataque");
        this.botonMenuAtaque.setTranslateY(0);
        this.botonMenuAtaque.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            estadoActual.agregarPais(this.jugadorActual, this.paisActual);
            this.setVisible(false);
            e.consume();
        });
        this.botonCancelar = new Button("Cancelar ataque");
        this.botonCancelar.setTranslateY(20);
        this.botonCancelar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            estadoActual.resetear();
            this.setVisible(false);
            e.consume();
        });
        this.getChildren().add(this.etiquetaMenuAtaque);
        this.getChildren().add(this.botonMenuAtaque);
        this.getChildren().add(this.botonCancelar);
        this.setVisible(false);
    }

    public void establecerBotonesVisibles(){
        this.botonCancelar.setVisible(false);
        this.botonMenuAtaque.setVisible(false);
        if(this.jugadorActual == this.paisActual.getJugador().getNumero()){
            this.botonMenuAtaque.setVisible(true);
            this.botonMenuAtaque.setText("Realizar ataque desde");
        }
        else if(this.estadoActual.recibeDefensor()){
            this.botonMenuAtaque.setVisible(true);
            this.botonMenuAtaque.setText("Confirmar ataque");
            this.botonCancelar.setVisible(true);
        }
    }

    public void aparecerMenu(MouseEvent evento, Pais unPais){
        String nombrePais = unPais.getNombre();
        this.setVisible(true);
        this.aparecer(evento.getSceneX(), evento.getSceneY());
        this.paisActual = unPais;
        this.etiquetaMenuAtaque.setText(nombrePais);
        this.imagenDesdeAbajo.setVisible(false);
        this.imagenDesdeArriba.setVisible(false);
        this.imagenDesdeIzquierda.setVisible(false);
        establecerBotonesVisibles();
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

    @Override
    public void update(Observable o, Object arg) {
        this.jugadorActual = this.ronda.jugadorActual().getNumero();
    }
}
