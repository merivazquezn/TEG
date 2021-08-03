package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuColocacion;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
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

public class MenuReagrupar extends StackPane implements Observer {
    private double puntoX;
    private double puntoY;
    private Label etiquetaMenuColocacion;
    private Button botonMenuColocacion;
    private Button botonCancelar;

    private TextField inputCantidad;

    private ImageView imagenDesdeAbajo;
    private ImageView imagenDesdeIzquierda;
    private ImageView imagenDesdeArriba;

    private Ronda ronda;
    private int jugadorActual;
    private Pais paisActual;

    private EstadoReagrupar estadoReagrupar;

    private void establecerImagenes() throws IOException {
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

    public MenuReagrupar(Ronda ronda) throws IOException {
        establecerImagenes();
        this.ronda = ronda;
        this.jugadorActual = ronda.jugadorActual().getNumero();
        this.etiquetaMenuColocacion = new Label("");
        this.etiquetaMenuColocacion.setStyle("-fx-font: 22 arial;");
        this.etiquetaMenuColocacion.setTranslateY(-35);
        this.botonMenuColocacion = new Button("Mover ejercitos");
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

    @Override
    public void update(Observable o, Object arg) {

    }
}
