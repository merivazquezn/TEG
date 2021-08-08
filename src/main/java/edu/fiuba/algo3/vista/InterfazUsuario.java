package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controlador.ControladorInterfaz;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class InterfazUsuario extends StackPane implements Observer {
    private ImageView interfaz;
    private Label etiquetaNombreRonda;
    private Label etiquetaInformacionRonda;
    private Label etiquetaJugador;
    private Button botonObjetivo;
    private Button botonCartas;
    private Button botonTerminarTurno;
    private Ronda ronda;

    private void inicializarBotones(){
        inicializarBotonObjetivo();

        inicializarBotonCartas();

        inicializarBotonTerminarTurno();
    }

    private void inicializarBotonTerminarTurno() {
        this.botonTerminarTurno = new Button("Terminar Turno");

        this.botonTerminarTurno.setStyle("-fx-background-color: rgb(204, 51, 17);" +
                "-fx-border-color: rgb(0, 0, 0);" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: rgb(255,255,255);");

        this.botonTerminarTurno.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            ControladorInterfaz.finalizarTurno(this.ronda);
            e.consume();
        });
        this.botonTerminarTurno.setTranslateX(-175);
    }

    private void inicializarBotonCartas() {
        this.botonCartas = new Button("Ver Cartas");

        this.botonCartas.setStyle("-fx-background-color: rgb(204, 51, 17);" +
                "-fx-border-color: rgb(0, 0, 0);" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: rgb(255,255,255);");

        MenuCartas menuCartas = MenuCartas.obtenerInstancia();
        this.botonCartas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            menuCartas.aparecerMenu(e);
            e.consume();
        });
        this.botonCartas.setTranslateX(-350);
    }

    private void inicializarBotonObjetivo() {
        this.botonObjetivo = new Button("Ver Objetivo");

        this.botonObjetivo.setStyle("-fx-background-color: rgb(204, 51, 17);" +
                "-fx-border-color: rgb(0, 0, 0);" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: rgb(255,255,255);");

        MenuObjetivo menuObjetivo = MenuObjetivo.obtenerInstancia();
        this.botonObjetivo.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            menuObjetivo.aparecerMenu(e);
            e.consume();
        });

        this.botonObjetivo.setTranslateX(-500);
    }

    private void agregarElementosAlMenu() {
        this.getChildren().add(this.interfaz);
        this.getChildren().add(this.botonObjetivo);
        this.getChildren().add(this.botonCartas);
        this.getChildren().add(this.botonTerminarTurno);
        this.getChildren().add(this.etiquetaNombreRonda);
        this.getChildren().add(this.etiquetaJugador);
        this.getChildren().add(this.etiquetaInformacionRonda);
    }

    private void inicializarEtiquetas(){
        inicializarEtiquetaNombreRonda();

        inicializarEtiquetaJugador();

        inicializarEtiquetaInformacionRonda();
    }

    private void inicializarEtiquetaNombreRonda() {
        String nombreRonda = this.ronda.getNombreRonda();
        this.etiquetaNombreRonda = new Label(nombreRonda);
        this.etiquetaNombreRonda.setTranslateX(210);

        this.etiquetaNombreRonda.setStyle(
                "-fx-font-weight: bold;"+
                "-fx-text-fill: rgb(255,255,255);");
    }

    private void inicializarEtiquetaJugador() {
        Jugador jugadorActual = this.ronda.jugadorActual();
        int numeroJugadorActual = jugadorActual.getNumero();
        String colorJugadorActual = AsignadorDeColores.jugadorActualSegunElNumero(numeroJugadorActual);
        this.etiquetaJugador = new Label("Turno de "+ colorJugadorActual);

        this.etiquetaJugador.setStyle(
                "-fx-font-weight: bold;"+
                "-fx-text-fill: rgb(255,255,255);");
    }

    private void inicializarEtiquetaInformacionRonda() {
        String informacionRonda = this.ronda.accionARealizar();
        this.etiquetaInformacionRonda = new Label(informacionRonda);
        this.etiquetaInformacionRonda.setTranslateX(400);

        this.etiquetaInformacionRonda.setStyle(
                "-fx-font-weight: bold;"+
                "-fx-text-fill: rgb(255,255,255);");
    }

    public InterfazUsuario(Ronda ronda) throws IOException{
        establecerParametrosInterfaz(ronda);
        inicializarInterfaz();
        inicializarBotones();
        inicializarEtiquetas();
        agregarElementosAlMenu();
    }

    private void establecerParametrosInterfaz(Ronda ronda) {
        this.ronda = ronda;
        this.setTranslateY(785);
        this.setTranslateX(120);
    }

    private void inicializarInterfaz() throws FileNotFoundException {
        FileInputStream inputImagenInterfaz = new FileInputStream("./src/imagenes/menuUsuario.png");
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.interfaz = new ImageView(imagenInterfaz);
    }

    private void actualizarInformacionRonda(){
        String rondaActual = this.ronda.getNombreRonda();
        Jugador jugadorActual = this.ronda.jugadorActual();
        int numeroJugadorActual = jugadorActual.getNumero();
        String colorJugadorActual = AsignadorDeColores.jugadorActualSegunElNumero(numeroJugadorActual);
        this.etiquetaNombreRonda.setText(rondaActual);
        this.etiquetaJugador.setText("Turno de "+ colorJugadorActual);
        String accionARealizarActual = this.ronda.accionARealizar();
        this.etiquetaInformacionRonda.setText(accionARealizarActual);
    }

    private void verificarJuegoTerminado(){
        if(this.ronda.juegoTerminado()){
            ocultarElementos();
        }
    }

    private void ocultarElementos() {
        this.interfaz.setVisible(false);
        this.etiquetaInformacionRonda.setVisible(false);
        this.etiquetaJugador.setVisible(false);
        this.etiquetaInformacionRonda.setVisible(false);
        this.botonObjetivo.setVisible(false);
        this.botonCartas.setVisible(false);
        this.botonTerminarTurno.setVisible(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        actualizarInformacionRonda();
        verificarJuegoTerminado();
    }
}
