package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
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
    private Ronda ronda;

    private void inicializarBotones(){
        this.botonObjetivo = new Button("Ver Objetivo");
        this.botonCartas = new Button("Ver Cartas");
        this.botonObjetivo.setTranslateX(-500);
        this.botonCartas.setTranslateX(-250);
        this.getChildren().add(this.botonObjetivo);
        this.getChildren().add(this.botonCartas);
    }

    private void inicializarEtiquetas(){
        String nombreRonda = this.ronda.getNombreRonda();
        this.etiquetaNombreRonda = new Label(nombreRonda);
        this.etiquetaNombreRonda.setTranslateX(210);
        Jugador jugadorActual = this.ronda.jugadorActual();
        int numeroJugadorActual = jugadorActual.getNumero();
        String colorJugadorActual = AsignadorDeColores.jugadorActualSegunElNumero(numeroJugadorActual);
        this.etiquetaJugador = new Label("Turno de "+ colorJugadorActual);
        String informacionRonda = this.ronda.accionARealizar();
        this.etiquetaInformacionRonda = new Label(informacionRonda);
        this.etiquetaInformacionRonda.setTranslateX(400);
        this.getChildren().add(this.etiquetaNombreRonda);
        this.getChildren().add(this.etiquetaJugador);
        this.getChildren().add(this.etiquetaInformacionRonda);
    }

    public InterfazUsuario(Ronda ronda){
        this.ronda = ronda;
        try{
            FileInputStream inputImagenInterfaz = new FileInputStream("./src/imagenes/menuUsuario.png");
            Image imagenInterfaz = new Image(inputImagenInterfaz);
            this.interfaz = new ImageView(imagenInterfaz);
            this.getChildren().add(this.interfaz);
            this.setTranslateY(785);
            this.setTranslateX(120);
            inicializarBotones();
            inicializarEtiquetas();
        }
        catch (IOException e){

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String rondaActual = this.ronda.getNombreRonda();
        Jugador jugadorActual = this.ronda.jugadorActual();
        int numeroJugadorActual = jugadorActual.getNumero();
        String colorJugadorActual = AsignadorDeColores.jugadorActualSegunElNumero(numeroJugadorActual);
        this.etiquetaNombreRonda.setText(rondaActual);
        this.etiquetaJugador.setText("Turno de "+ colorJugadorActual);
        String accionARealizarActual = this.ronda.accionARealizar();
        this.etiquetaInformacionRonda.setText(accionARealizarActual);
    }
}
