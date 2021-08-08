package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MenuJuegoTerminado extends VistaMenu implements Observer {

    private static MenuJuegoTerminado instancia;
    private ImageView interfazJuegoTerminado;

    private MenuJuegoTerminado(Ronda ronda) throws IOException {
        super(ronda, 300, 100);
        inicializarInterfaz();
        this.getChildren().add(this.interfazJuegoTerminado);
    }

    private void inicializarInterfaz() throws FileNotFoundException {
        FileInputStream inputInterfazJuegoTerminado = new FileInputStream("./src/imagenes/interfazJuegoFinalizado.png");
        Image imagenInterfazJuegoTerminado = new Image(inputInterfazJuegoTerminado);
        this.interfazJuegoTerminado = new ImageView(imagenInterfazJuegoTerminado);
    }

    public static void crearInstancia(Ronda ronda) throws IOException{
        if(instancia == null){
            instancia = new MenuJuegoTerminado(ronda);
        }
    }

    public static MenuJuegoTerminado obtenerInstancia(){
        return instancia;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(this.ronda.juegoTerminado()){
            visibilizarMenu();
        }
    }

    private void visibilizarMenu() {
        this.setVisible(true);
        String ganador = AsignadorDeColores.jugadorActualSegunElNumero(this.ronda.jugadorGanador().getNumero());
        Label jugadorGanador = new Label("Jugador ganador:\n" + ganador);
        jugadorGanador.setStyle("-fx-text-fill: #f2f2e9");
        this.getChildren().add(jugadorGanador);
        jugadorGanador.relocate(400, 400);
    }

    public boolean adentro(double mx, double my){
        return true;
    }
}
