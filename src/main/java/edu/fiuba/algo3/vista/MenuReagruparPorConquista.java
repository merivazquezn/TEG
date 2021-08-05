package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorMenuReagrupar;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
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

public class MenuReagruparPorConquista extends VistaMenu implements Observer {

    private ImageView interfaz;
    private Button botonAceptar;
    private TextField inputCantidad;
    private Label etiquetaInterfaz;
    private Label etiquetaEjercitosDisponibles;
    private int cantidadMaximaReagrupacion;
    private Pais paisOrigen;
    private Pais paisDestino;

    public MenuReagruparPorConquista(Ronda ronda) throws IOException {
        super(ronda, 500, 300);
        FileInputStream inputInterfazReagrupar = new FileInputStream("./src/imagenes/interfazReagrupacion.png");
        Image imagenInterfazReagrupar = new Image(inputInterfazReagrupar);
        this.interfaz = new ImageView(imagenInterfazReagrupar);
        this.botonAceptar = new Button("Confirmar");
        this.botonAceptar.setStyle("-fx-font-size: 10; -fx-background-color: #f2f2e9;");
        this.botonAceptar.setMaxWidth(120);
        this.botonAceptar.setTranslateY(80);
        this.botonAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            ControladorMenuReagrupar.realizarJugada(this.ronda, this.paisOrigen, this.paisDestino, parseInt(this.inputCantidad.getText()));
            this.ronda.terminar();
            this.ronda.notifyObservers();
            this.paisDestino.notifyObservers();
            this.paisOrigen.notifyObservers();
            this.setVisible(false);
            e.consume();
        });
        this.inputCantidad = new TextField("0");
        this.inputCantidad.setStyle("-fx-background-color: #f2f2e9;");
        this.inputCantidad.setMaxWidth(70);
        this.inputCantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputCantidad.setText("0");
            }
            newValue = inputCantidad.getText();
            if(parseInt(newValue) < 0 || parseInt(newValue) > cantidadMaximaReagrupacion){
                inputCantidad.setText("0");
            }
        });
        this.etiquetaInterfaz = new Label("Ingrese la cantidad a transferir al pais conquistado");
        this.etiquetaInterfaz.setTranslateY(-80);
        this.etiquetaInterfaz.setStyle("-fx-text-fill: #f2f2e9;");
        this.etiquetaEjercitosDisponibles = new Label("Ejercitos Disponibles para Reagrupar: ");
        this.etiquetaEjercitosDisponibles.setStyle("-fx-text-fill: #f2f2e9;");
        this.etiquetaEjercitosDisponibles.setTranslateY(-60);
        this.getChildren().add(this.interfaz);
        this.getChildren().add(this.botonAceptar);
        this.getChildren().add(this.inputCantidad);
        this.getChildren().add(this.etiquetaInterfaz);
        this.getChildren().add(this.etiquetaEjercitosDisponibles);
    }

    public void update(Observable o, Object arg) {
        if(ronda.seProdujoConquista()) {
            this.setVisible(true);
            this.paisOrigen = this.ronda.getConquistador();
            this.paisDestino = this.ronda.getConquistado();
            this.cantidadMaximaReagrupacion = this.paisOrigen.getCantidadEjercitos() - 1;
            this.inputCantidad.setText("0");
            this.etiquetaEjercitosDisponibles.setText("Ejercitos Disponibles para Reagrupar: " + this.cantidadMaximaReagrupacion);
        }
    }

    public boolean adentro(double mx, double my){
        return true;
    }
}
