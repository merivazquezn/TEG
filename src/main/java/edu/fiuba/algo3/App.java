package edu.fiuba.algo3;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application {

    public void realizarJuego(Stage stage, int cantidadJugadores){
        FileInputStream inputImagenFondo = null;
        try {
            inputImagenFondo = new FileInputStream("./src/imagenes/background.png");
            Image imagenFondo = new Image(inputImagenFondo);
            StackPane panel = new StackPane();
            Scene scene = new Scene(panel, 1440, 819);
            BackgroundImage backgroundimage = new BackgroundImage(imagenFondo,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            panel.setBackground(background);
            stage.setScene(scene);
            stage.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void pedirCantidadJugadores(Stage stage){
        stage.setResizable(false);
        stage.setTitle("A.L.T.E.G.O");
        var mensajeTitulo = new Label("Bienvenido a  A.L.T.E.G.O");
        mensajeTitulo.setStyle("-fx-font: 24 arial;");
        var mensajeDescripcion1 = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore\n" +
                " et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip\n" +
                " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu\n" +
                " fugiat nulla pariatur. Excepteur sint occaecat cupidatat\n" +
                " non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        var mensajeCantidadJugadores = new Label("Cantidad de jugadores:");
        mensajeCantidadJugadores.setStyle("-fx-font: 22 arial;");
        Button botonInicio = new Button("Confirmar e Iniciar");
        TextField cantidadJugadores = new TextField("2");
        cantidadJugadores.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cantidadJugadores.setText("2");
                }
                newValue = cantidadJugadores.getText();
                if(Integer.parseInt(newValue) < 2 || Integer.parseInt(newValue) > 6){
                    cantidadJugadores.setText("2");
                }
            }
        });
        StackPane panelBienvenida = new StackPane(mensajeTitulo, mensajeDescripcion1, mensajeCantidadJugadores, cantidadJugadores, botonInicio);
        mensajeTitulo.setTranslateY(-100);
        mensajeCantidadJugadores.setTranslateY(70);
        cantidadJugadores.setTranslateY(100);
        botonInicio.setTranslateY(150);
        botonInicio.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");

        EventHandler<ActionEvent> eventoBoton = e -> realizarJuego(stage, Integer.parseInt(cantidadJugadores.getText()));
        botonInicio.setOnAction(eventoBoton);

        var scene = new Scene(panelBienvenida, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {
        pedirCantidadJugadores(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}