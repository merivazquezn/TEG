package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuColocacion;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Integer.parseInt;

public class MenuColocacion extends StackPane implements Observer {
    private double puntoX;
    private double puntoY;
    private Label etiquetaMenuColocacion;
    private Button botonMenuColocacion;
    private TextField inputCantidad;
    private ImageView imagenDesdeAbajo;
    private ImageView imagenDesdeIzquierda;
    private ImageView imagenDesdeArriba;
    private int maximaCantidadAColocar;
    private Ronda ronda;
    private int jugadorActual;
    private Pais paisActual;

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

    public MenuColocacion(Ronda ronda) throws IOException {
        establecerImagenes();
        this.ronda = ronda;
        this.jugadorActual = ronda.jugadorActual().getNumero();
        this.maximaCantidadAColocar = this.ronda.cantidadAColocar();
        this.etiquetaMenuColocacion = new Label("");
        this.etiquetaMenuColocacion.setStyle("-fx-font: 22 arial;");
        this.etiquetaMenuColocacion.setTranslateY(-35);
        this.botonMenuColocacion = new Button("Colocar ejercitos");
        this.botonMenuColocacion.setTranslateY(20);
        this.botonMenuColocacion.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            ControladorMenuColocacion.realizarJugada(this.ronda, this.paisActual, parseInt(this.inputCantidad.getText()));
            this.setVisible(false);
            e.consume();
        });
        this.inputCantidad = new TextField();
        this.inputCantidad.setTranslateY(-10);
        this.inputCantidad.setPrefWidth(50);
        this.inputCantidad.setMaxWidth(50);
        this.inputCantidad.setAlignment(Pos.CENTER);
        this.inputCantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputCantidad.setText("0");
            }
            newValue = inputCantidad.getText();
            if(parseInt(newValue) < 0 || parseInt(newValue) > maximaCantidadAColocar){
                inputCantidad.setText("0");
            }
        });
        this.getChildren().add(this.etiquetaMenuColocacion);
        this.getChildren().add(this.botonMenuColocacion);
        this.getChildren().add(this.inputCantidad);
        this.setVisible(false);
    }

    private void verificarJugadorActual(Pais unPais){
        this.inputCantidad.setVisible(true);
        this.botonMenuColocacion.setVisible(true);
        if(jugadorActual != unPais.getJugador().getNumero()){
            this.inputCantidad.setVisible(false);
            this.botonMenuColocacion.setVisible(false);
        }
    }

    public void aparecerMenu(MouseEvent evento, Pais unPais){
        String nombrePais = unPais.getNombre();
        this.paisActual = unPais;
        this.setVisible(true);
        this.inputCantidad.setText("0");
        this.aparecer(evento.getSceneX(), evento.getSceneY());
        this.etiquetaMenuColocacion.setText(nombrePais);
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
        verificarJugadorActual(unPais);
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
        this.maximaCantidadAColocar = this.ronda.cantidadAColocar();
        this.jugadorActual = this.ronda.jugadorActual().getNumero();
    }


}
