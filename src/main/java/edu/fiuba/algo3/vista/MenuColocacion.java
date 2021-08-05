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

public class MenuColocacion extends VistaMenuDesplegable implements Observer {
    private Label etiquetaMenuColocacion;
    private Button botonMenuColocacion;
    private TextField inputCantidad;
    private int maximaCantidadAColocar;
    private int jugadorActual;
    private Pais paisActual;

    public MenuColocacion(Ronda ronda) throws IOException {
        super(ronda, 0, 0);
        this.jugadorActual = ronda.jugadorActual().getNumero();
        this.maximaCantidadAColocar = this.ronda.cantidadAColocar();
        this.etiquetaMenuColocacion = new Label("");
        this.etiquetaMenuColocacion.setStyle("-fx-font: 22 arial;");
        this.etiquetaMenuColocacion.setTranslateY(-35);
        this.botonMenuColocacion = new Button("Colocar ejercitos");
        this.botonMenuColocacion.setTranslateY(20);
        this.botonMenuColocacion.setStyle("-fx-font-size: 10; -fx-background-color: #f2f2e9;");
        this.botonMenuColocacion.setMaxWidth(90);
        this.botonMenuColocacion.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            ControladorMenuColocacion.realizarJugada(this.ronda, this.paisActual, parseInt(this.inputCantidad.getText()));
            this.setVisible(false);
            e.consume();
        });
        this.inputCantidad = new TextField();
        this.inputCantidad.setTranslateY(-10);
        this.inputCantidad.setPrefWidth(50);
        this.inputCantidad.setMaxWidth(50);
        this.inputCantidad.setMaxHeight(8);
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
        this.inputCantidad.setStyle("-fx-background-color: #f2f2e9;");
        this.inputCantidad.setMaxWidth(35);
        this.getChildren().add(this.etiquetaMenuColocacion);
        this.getChildren().add(this.botonMenuColocacion);
        this.getChildren().add(this.inputCantidad);
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

        this.etiquetaMenuColocacion.setStyle("-fx-text-fill: #f2f2e9; -fx-font-size: 16; -fx-font-weight: bold;");
        verificarJugadorActual(unPais);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.maximaCantidadAColocar = this.ronda.cantidadAColocar();
        this.jugadorActual = this.ronda.jugadorActual().getNumero();
    }


}
