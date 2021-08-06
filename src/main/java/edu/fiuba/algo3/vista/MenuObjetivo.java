package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuObjetivo extends VistaMenu {
    private static MenuObjetivo instancia;
    private Label etiquetaNombreObjetivo;
    private Label etiquetaDescripcionObjetivo;
    private ImageView interfazObjetivo;
    private Button botonRevelar;

    private MenuObjetivo(Ronda ronda) throws IOException {
        super(ronda, 60, 530);
        inicializarInterfaz();
        inicializarBotonRevelar();
        inicializarEtiquetas();
        agregarElementosAlMenu();
    }

    private void inicializarBotonRevelar(){
        this.botonRevelar = new Button("Revelar Objetivo");
        this.botonRevelar.setStyle("-fx-background-color: rgb(204, 51, 17);" +
                "-fx-border-color: rgb(0, 0, 0);" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: rgb(255,255,255);");
        this.botonRevelar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            this.etiquetaNombreObjetivo.setVisible(true);
            this.etiquetaDescripcionObjetivo.setVisible(true);
            this.botonRevelar.setVisible(false);
            e.consume();
        });
    }

    private void inicializarEtiquetas() {
        inicializarEtiquetaNombreObjetivo();
        inicializarEtiquetaDescripcionObjetivo();
    }

    private void inicializarInterfaz() throws FileNotFoundException {
        FileInputStream inputImagenInterfaz = new FileInputStream("./src/imagenes/vistaDesplegable.png");
        Image imagenInterfaz = new Image(inputImagenInterfaz);
        this.interfazObjetivo = new ImageView(imagenInterfaz);
    }

    private void inicializarEtiquetaNombreObjetivo() {
        this.etiquetaNombreObjetivo = new Label("");
        this.etiquetaNombreObjetivo.setStyle("-fx-text-fill: #f2f2e9; -fx-font-weight: bold;");
        this.etiquetaNombreObjetivo.setTranslateY(-100);
    }

    private void inicializarEtiquetaDescripcionObjetivo() {
        this.etiquetaDescripcionObjetivo = new Label("");
        this.etiquetaDescripcionObjetivo.setStyle("-fx-text-fill: #f2f2e9;");
    }

    private void agregarElementosAlMenu() {
        this.getChildren().add(this.interfazObjetivo);
        this.getChildren().add(this.etiquetaNombreObjetivo);
        this.getChildren().add(this.etiquetaDescripcionObjetivo);
        this.getChildren().add(this.botonRevelar);
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
            this.etiquetaNombreObjetivo.setText(jugadorActual.nombreObjetivo());
            this.etiquetaDescripcionObjetivo.setText(jugadorActual.descripcionObjetivo());
            this.etiquetaNombreObjetivo.setVisible(false);
            this.etiquetaDescripcionObjetivo.setVisible(false);
            this.botonRevelar.setVisible(true);
        }
    }

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX && mx <= this.puntoX+300 && my >= this.puntoY && my <= this.puntoY+250);
    }

}
