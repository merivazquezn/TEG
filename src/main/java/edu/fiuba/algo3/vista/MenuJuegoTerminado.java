package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MenuJuegoTerminado extends VistaMenu implements Observer {

    private ImageView interfazJuegoTerminado;

    public MenuJuegoTerminado(Ronda ronda) throws IOException {
        super(ronda, 500, 300);
        FileInputStream inputInterfazJuegoTerminado = new FileInputStream("./src/imagenes/interfazJuegoFinalizado.png");
        Image imagenInterfazJuegoTerminado = new Image(inputInterfazJuegoTerminado);
        this.interfazJuegoTerminado = new ImageView(imagenInterfazJuegoTerminado);
        this.getChildren().add(this.interfazJuegoTerminado);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(this.ronda.juegoTerminado()){
            this.setVisible(true);
            String ganador = AsignadorDeColores.jugadorActualSegunElNumero(this.ronda.jugadorGanador().getNumero());
            Label jugadorGanador = new Label("Jugador ganador:\n" + ganador);
            this.getChildren().add(jugadorGanador);
            jugadorGanador.relocate(400, 400);
        }
    }

    public boolean adentro(double mx, double my){
        return true;
    }
}
