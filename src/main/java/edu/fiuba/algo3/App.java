package edu.fiuba.algo3;

import edu.fiuba.algo3.infraestructura.Parser;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.vista.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends Application {

    private Tablero tablero;
    private Ronda ronda;
    private ArrayList<Circle> vistaEjercitos;
    private ArrayList<Label> etiquetaEjercitos;
    private MenuAtaque panelMenuAtaque;

    public void inicializarJuego(int cantidadJugadores){

        try {
            String rutaPaises = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            String rutaObjetivos = "./src/main/java/edu/fiuba/algo3/infraestructura/objetivos.csv";
            String rutatarjetas = "./src/main/java/edu/fiuba/algo3/infraestructura/cartas.csv";

            Parser parser = new Parser(rutaPaises, rutaObjetivos, rutatarjetas);

            this.panelMenuAtaque = new MenuAtaque();

            HashMap<String, Continente> continentes = parser.getContinentes();
            //HashMap<String, Pais> paises = parser.getPaisesParaTablero();
            HashMap<Pais, int[]> vistaPaises = parser.getPaisesParaVista();

            this.vistaEjercitos = new ArrayList<>();
            this.etiquetaEjercitos = new ArrayList<>();
            for (HashMap.Entry<Pais, int[]> entry : vistaPaises.entrySet()) {
                Pais unPais = entry.getKey();
                int[] coordenadas = entry.getValue();
                Label etiquetaEjercito = new Label("1");
                etiquetaEjercito.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    this.panelMenuAtaque.aparecerMenu(e, unPais);
                    e.consume();
                });
                etiquetaEjercito.setTranslateX(coordenadas[0]-4);
                etiquetaEjercito.setTranslateY(coordenadas[1]-6);
                Circle circuloPais = new Circle();
                circuloPais.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    this.panelMenuAtaque.aparecerMenu(e, unPais);
                    e.consume();
                });
                circuloPais.setCenterX(coordenadas[0]);
                circuloPais.setCenterY(coordenadas[1]);
                circuloPais.setRadius(10.0f);
                circuloPais.setFill(Color.YELLOW);
                this.vistaEjercitos.add(circuloPais);
                this.etiquetaEjercitos.add(etiquetaEjercito);
            }

            this.tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarJuego(Stage stage, int cantidadJugadores){
        inicializarJuego(cantidadJugadores);
        FileInputStream inputImagenFondo;
        FileInputStream inputImagenInterfaz;
        try {
            inputImagenFondo = new FileInputStream("./src/imagenes/background.png");
            Image imagenFondo = new Image(inputImagenFondo);
            inputImagenInterfaz = new FileInputStream("./src/imagenes/menuUsuario.png");
            Image imagenInterfaz = new Image(inputImagenInterfaz);
            ImageView imagenInterfazVisible = new ImageView(imagenInterfaz);
            Pane panel = new Pane(imagenInterfazVisible);
            imagenInterfazVisible.setTranslateY(785);
            imagenInterfazVisible.setTranslateX(120);
            Scene scene = new Scene(panel, 1440, 819);
            BackgroundImage backgroundimage = new BackgroundImage(imagenFondo,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background = new Background(backgroundimage);
            panel.setBackground(background);
            for (Circle circuloEjercitos : this.vistaEjercitos) {
                panel.getChildren().add(circuloEjercitos);
            }
            for (Label etiqueta : this.etiquetaEjercitos) {
                panel.getChildren().add(etiqueta);
            }
            panel.getChildren().add(this.panelMenuAtaque);
            panel.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                this.panelMenuAtaque.ocultarMenu(e);
            });
            stage.setScene(scene);
            stage.centerOnScreen();
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