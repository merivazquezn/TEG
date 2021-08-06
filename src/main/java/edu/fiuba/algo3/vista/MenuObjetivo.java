package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuObjetivo extends VistaMenu {
    private static MenuObjetivo instancia;
    private Label nombreObjetivo;
    private Label descripcionObjetivo;
    private ImageView interfazObjetivo;

    private MenuObjetivo(Ronda ronda) throws IOException {
        super(ronda, 60, 530);
        inicializarInterfaz();
        inicializarEtiquetaNombreObjetivo();
        inicializarEtiquetaDescripcionObjetivo();
        agregarElementosAlMenu();
    }

    private void inicializarInterfaz() throws FileNotFoundException {
        FileInputStream inputImagenInterfaz = new FileInputStream("./src/imagenes/vistaDesplegable.png");
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.interfazObjetivo = new ImageView(imagenInterfaz);
    }

    private void inicializarEtiquetaNombreObjetivo() {
        this.nombreObjetivo = new Label("");
        this.nombreObjetivo.setStyle("-fx-text-fill: #f2f2e9; -fx-font-weight: bold;");
        this.nombreObjetivo.setTranslateY(-100);
    }

    private void inicializarEtiquetaDescripcionObjetivo() {
        this.descripcionObjetivo = new Label("");
        this.descripcionObjetivo.setStyle("-fx-text-fill: #f2f2e9;");
    }

    private void agregarElementosAlMenu() {
        this.getChildren().add(this.interfazObjetivo);
        this.getChildren().add(this.nombreObjetivo);
        this.getChildren().add(this.descripcionObjetivo);
    }

    public static void crearInstancia(Ronda ronda) throws IOException{
        if(instancia == null){
            instancia = new MenuObjetivo(ronda);
        }
    }

    public static MenuObjetivo obtenerInstancia(){
        return instancia;
    }

    public void aparecerMenu(MouseEvent evento){
        if(this.isVisible()){
            ocultarMenu(evento);
        }else{
            Jugador jugadorActual = this.ronda.jugadorActual();
            this.setVisible(true);
            this.nombreObjetivo.setText(jugadorActual.nombreObjetivo());
            this.descripcionObjetivo.setText(jugadorActual.descripcionObjetivo());
        }
    }

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX && mx <= this.puntoX+100 && my >= this.puntoY && my <= this.puntoY+200);
    }

}
