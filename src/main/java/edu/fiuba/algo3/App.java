package edu.fiuba.algo3;

import edu.fiuba.algo3.controlador.ControladorEjercito;
import edu.fiuba.algo3.infraestructura.Parser;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Objetivo;
import edu.fiuba.algo3.vista.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    private Tablero tablero;
    private Ronda ronda;
    private ArrayList<VistaEjercito> vistaEjercitos;

    public void inicializarJuego(int cantidadJugadores) throws IOException{
        Parser parser = inicializarParser();
        cargarInformacionDelJuego(cantidadJugadores, parser);
    }

    private void cargarInformacionDelJuego(int cantidadJugadores, Parser parser) throws IOException{
        HashMap<String, Continente> continentes = parser.getContinentes();
        HashMap<String, Pais> paises = parser.getPaisesParaTablero();
        HashMap<Pais, int[]> vistaPaises = parser.getPaisesParaVista();
        ArrayList<Objetivo> listaObjetivos = parser.getObjetivos();
        ArrayList<Tarjeta> tarjetas = parser.getTarjetas();

        ListaJugadores listaJugadores = new ListaJugadores(cantidadJugadores, new Randomizador(), listaObjetivos);

        RepartidorDePaises repartidorDePaises = new RepartidorDePaises(paises, listaJugadores);
        repartidorDePaises.repartirPaisesPorJugadores();

        Mazo mazo = new Mazo(tarjetas, new Randomizador());
        this.tablero = new Tablero(continentes,new ConstructorDeConjuntoDados(new Randomizador()), mazo);
        this.ronda = new Ronda(this.tablero, listaJugadores);
        ControladorEjercito.crearInstancia(this.ronda);
        crearInstanciasDeMenus();
        this.vistaEjercitos = new ArrayList<>();
        for (HashMap.Entry<Pais, int[]> entry : vistaPaises.entrySet()) {
            crearVistaEjercito(entry);
        }
    }

    private void crearInstanciasDeMenus() throws IOException {
        MenuAtaque.crearInstancia(this.ronda);
        MenuColocacion.crearInstancia(this.ronda);
        MenuReagrupar.crearInstancia(this.ronda);
        MenuReagruparPorConquista.crearInstancia(this.ronda);
        MenuJuegoTerminado.crearInstancia(this.ronda);
        VistaDados.crearInstancia(this.tablero);
        MenuObjetivo.crearInstancia(this.ronda);
        MenuCartas.crearInstancia(this.ronda);
    }

    private void crearVistaEjercito(Map.Entry<Pais, int[]> entry) {
        Pais unPais = entry.getKey();
        int[] coordenadas = entry.getValue();
        VistaEjercito nuevaVistaEjercito = new VistaEjercito(unPais);
        nuevaVistaEjercito.setCenterX(coordenadas[0]);
        nuevaVistaEjercito.setCenterY(coordenadas[1]);
        unPais.addObserver(nuevaVistaEjercito);
        this.vistaEjercitos.add(nuevaVistaEjercito);
    }

    private Parser inicializarParser() {
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutatarjetas = "./src/data/cartas.csv";

        return new Parser(rutaPaises, rutaObjetivos, rutatarjetas);
    }

    public void realizarJuego(Stage stage, int cantidadJugadores) throws IOException{
        inicializarJuego(cantidadJugadores);
        InterfazUsuario interfaz = new InterfazUsuario(this.ronda);
        this.ronda.addObserver(interfaz);
        Pane panel = new Pane(interfaz);
        Scene scene = new Scene(panel, 1440, 819);

        establecerBackground(panel);
        agregarVistasAlPanelPrincipal(panel);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        Reproductor.reproducirNuevaMusica("./src/sonidos/teg_soundtrack.wav");
    }

    private void agregarVistasAlPanelPrincipal(Pane panel) {
        agregarVistaDeEjercitos(panel);
        MenuColocacion menuColocacion = MenuColocacion.obtenerInstancia();
        MenuAtaque menuAtaque = MenuAtaque.obtenerInstancia();
        MenuReagrupar menuReagrupar = MenuReagrupar.obtenerInstancia();
        MenuReagruparPorConquista menuReagruparPorConquista = MenuReagruparPorConquista.obtenerInstancia();
        MenuCartas menuCartas = MenuCartas.obtenerInstancia();
        MenuJuegoTerminado menuJuegoTerminado = MenuJuegoTerminado.obtenerInstancia();
        VistaDados vistaDados = VistaDados.obtenerInstancia();
        MenuObjetivo menuObjetivo = MenuObjetivo.obtenerInstancia();
        agregarObservadores(menuColocacion, menuAtaque, menuReagrupar, menuReagruparPorConquista, menuCartas, menuJuegoTerminado, vistaDados);
        panel.getChildren().add(menuAtaque);
        panel.getChildren().add(menuColocacion);
        panel.getChildren().add(menuReagruparPorConquista);
        panel.getChildren().add(vistaDados);
        panel.getChildren().add(menuObjetivo);
        panel.getChildren().add(menuReagrupar);
        panel.getChildren().add(menuCartas);
        panel.getChildren().add(menuJuegoTerminado);

        panel.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            menuAtaque.ocultarMenu(e);
            menuColocacion.ocultarMenu(e);
            menuObjetivo.ocultarMenu(e);
            menuReagrupar.ocultarMenu(e);
            menuCartas.ocultarMenu(e);
        });
    }

    private void agregarObservadores(MenuColocacion menuColocacion, MenuAtaque menuAtaque, MenuReagrupar menuReagrupar, MenuReagruparPorConquista menuReagruparPorConquista, MenuCartas menuCartas, MenuJuegoTerminado menuJuegoTerminado, VistaDados vistaDados) {
        this.ronda.addObserver(menuColocacion);
        this.ronda.addObserver(menuAtaque);
        this.ronda.addObserver(menuReagrupar);
        this.ronda.addObserver(menuReagruparPorConquista);
        this.ronda.addObserver(menuCartas);
        this.ronda.addObserver(menuJuegoTerminado);
        this.tablero.addObserver(vistaDados);
    }

    private void establecerBackground(Pane panel) throws FileNotFoundException {
        FileInputStream inputImagenFondo = new FileInputStream("./src/imagenes/background.png");
        Image imagenFondo = new Image(inputImagenFondo);
        BackgroundImage backgroundimage = new BackgroundImage(imagenFondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        panel.setBackground(background);
    }

    private void agregarVistaDeEjercitos(Pane panel) {
        for (VistaEjercito vistaEjercito : this.vistaEjercitos) {
            panel.getChildren().add(vistaEjercito.getCirculoEjercito());
            panel.getChildren().add(vistaEjercito.getEtiquetaEjercito());
        }
    }

    public void iniciarMenuPrincipal(Stage stage) throws IOException{
        stage.setResizable(false);
        stage.setTitle("A.L.T.E.G.O");
        Reproductor.reproducirNuevaMusica("./src/sonidos/menu_principal.wav");
        FileInputStream inputImagenIcono = new FileInputStream("./src/imagenes/icono.png");
        Image imagenIcono = new Image(inputImagenIcono);
        stage.getIcons().add(imagenIcono);
        Label titulo = obtenerTitulo();
        Label mensajeDescripcion = obtenerEtiquetaDescripcion();
        mensajeDescripcion.setStyle("-fx-font: 12 arial; -fx-text-fill: white");

        Label mensajeCantidadJugadores = obtenerMensajeCantidadJugadores("Cantidad de jugadores:", "-fx-font: 22 arial; -fx-text-fill: white", 70);
        TextField cantidadJugadores = obtenerInputCantidadDeJugadores();
        Button botonInicio = obtenerBotonDeInicio(stage, cantidadJugadores);
        StackPane panelBienvenida = new StackPane(titulo, mensajeDescripcion, mensajeCantidadJugadores, cantidadJugadores, botonInicio);
        panelBienvenida.setStyle("-fx-background-color: black;");
        var scene = new Scene(panelBienvenida, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private Label obtenerMensajeCantidadJugadores(String s, String s2, int i) {
        var mensajeCantidadJugadores = new Label(s);
        mensajeCantidadJugadores.setStyle(s2);
        mensajeCantidadJugadores.setTranslateY(i);
        return mensajeCantidadJugadores;
    }

    private Label obtenerTitulo() {
        var mensajeTitulo = new Label("Bienvenido a  A.L.T.E.G.O");
        mensajeTitulo.setStyle("-fx-font: 36 arial; -fx-text-fill: white; -fx-font-weight: bold;");
        mensajeTitulo.setTranslateY(-200);
        return mensajeTitulo;
    }

    private Button obtenerBotonDeInicio(Stage stage, TextField cantidadJugadores) {
        Button botonInicio = new Button("Confirmar e Iniciar");
        botonInicio.setTranslateY(150);
        botonInicio.setStyle("-fx-font: 22 arial; -fx-base: red; -fx-text-fill: white");

        EventHandler<ActionEvent> eventoBoton = e -> {
            try {

                realizarJuego(stage, Integer.parseInt(cantidadJugadores.getText()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        botonInicio.setOnAction(eventoBoton);
        return botonInicio;
    }

    private Label obtenerEtiquetaDescripcion() {

        String mensajeDescripcion = "El juego propone un conflicto bélico que tiene lugar sobre un planisferio dividido en 50 países.\n" +
        " Para empezar se reparten los 50 países entre los jugadores, quienes ocupan sus dominios con ejércitos.\n" +
        " Cada jugador tiene un objetivo secreto a cumplir, que se le asigna al azar y que el resto de los jugadores desconocen.\n"+
        " Para cumplir el objetivo, el jugador debería ampliar sus dominios y reordenar sus fuerzas, lo que exigirá\n" +
        " realizar ataques y defenderse de ataques adversarios, y agrupar y desplazar ejércitos.\n" +
        " La conquista de nuevos territorios le permitirá aumentar el número de ejércitos a su disposición\n" +
        "otorgándole mayores chances para triunfar en los combates.\n";


        var mensajeDescripcion1 = new Label(mensajeDescripcion);
        mensajeDescripcion1.setTranslateY(-100);
        return mensajeDescripcion1;
    }

    private TextField obtenerInputCantidadDeJugadores() {
        TextField cantidadJugadores = new TextField("2");
        cantidadJugadores.setStyle("-fx-background-color: #f2f2e9; fx-border-width: 2px; fx-border-color: black;");
        cantidadJugadores.setMaxWidth(150);
        cantidadJugadores.setPrefWidth(50);
        cantidadJugadores.setMaxWidth(50);
        cantidadJugadores.setAlignment(Pos.CENTER);
        cantidadJugadores.setTranslateY(100);
        establecerParametroPermitidoDeCantidadDeJugadores(cantidadJugadores);
        return cantidadJugadores;
    }

    private void establecerParametroPermitidoDeCantidadDeJugadores(TextField cantidadJugadores) {


            cantidadJugadores.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    cantidadJugadores.setText("2");
                }
                
                try {
                    newValue = cantidadJugadores.getText();
                    if(Integer.parseInt(newValue) < 2 || Integer.parseInt(newValue) > 6){
                        cantidadJugadores.setText("2");
                    }
                } catch(NumberFormatException e) {
                }
                });
    }

    public static void empezarDevuelta() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            iniciarMenuPrincipal(stage);
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    public static void main(String[] args) {
        launch();
    }

}