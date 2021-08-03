package edu.fiuba.algo3.vista;


import edu.fiuba.algo3.controlador.ControladorMenuReagrupar;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import edu.fiuba.algo3.modelo.general.Pais;
import javafx.geometry.Pos;
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

public class MenuReagrupar extends StackPane implements Observer {
    private double puntoX;
    private double puntoY;
    private Label etiquetaMenuReagrupacion;
    private Button botonMenuReagrupacion;
    private Button botonCancelar;

    private TextField inputCantidad;

    private ImageView imagenDesdeAbajo;
    private ImageView imagenDesdeIzquierda;
    private ImageView imagenDesdeArriba;

    private Ronda ronda;
    private int jugadorActual;
    private Pais paisActual;

    private EstadoReagrupar estadoActual;
    private int CANTMAXIMAEJERCITOSTRANSFERIBLES;

    private void establecerImagenes() throws IOException {
        FileInputStream inputMenuAtaqueAbajo = new FileInputStream("./src/imagenes/desplegableAbajo.png");
        FileInputStream inputMenuAtaqueIzquierda = new FileInputStream("./src/imagenes/desplegableIzquierda.png");
        FileInputStream inputMenuAtaqueArriba = new FileInputStream("./src/imagenes/desplegableArriba.png");

        Image imagenMenuAtaqueAbajo = new Image(inputMenuAtaqueAbajo);
        Image imagenMenuAtaqueIzquierda = new Image(inputMenuAtaqueIzquierda);
        Image imagenMenuAtaqueArriba = new Image(inputMenuAtaqueArriba);

        this.imagenDesdeAbajo = new ImageView(imagenMenuAtaqueAbajo);
        this.imagenDesdeIzquierda = new ImageView(imagenMenuAtaqueIzquierda);
        this.imagenDesdeArriba = new ImageView(imagenMenuAtaqueArriba);

        this.getChildren().add(this.imagenDesdeAbajo);
        this.getChildren().add(this.imagenDesdeIzquierda);
        this.getChildren().add(this.imagenDesdeArriba);

        this.imagenDesdeAbajo.setVisible(false);
        this.imagenDesdeArriba.setVisible(false);
        this.imagenDesdeIzquierda.setVisible(false);
    }

    public MenuReagrupar(Ronda ronda) throws IOException {
        establecerImagenes();
        this.ronda = ronda;
        this.estadoActual = new EstadoReagrupar(ronda);
        this.jugadorActual = ronda.jugadorActual().getNumero();
        this.etiquetaMenuReagrupacion = new Label("");
        this.etiquetaMenuReagrupacion.setStyle("-fx-font: 22 arial;");
        this.etiquetaMenuReagrupacion.setTranslateY(-35);

        this.botonMenuReagrupacion = new Button("Mover ejercitos");
        this.botonMenuReagrupacion.setTranslateY(20);
        this.botonMenuReagrupacion.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            ControladorMenuReagrupar.realizarJugada(this.ronda, this.paisActual, this.paisActual, parseInt(this.inputCantidad.getText()));
            this.setVisible(false);
            e.consume();
        });

        this.botonCancelar = new Button("Cancelar reagrupacion");
        this.botonCancelar.setTranslateY(25);
        this.botonCancelar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            estadoActual.resetear();
            this.setVisible(false);
            e.consume();
        });

        this.inputCantidad = new TextField();
        this.inputCantidad.setTranslateY(-10);
        this.inputCantidad.setPrefWidth(50);
        this.inputCantidad.setMaxWidth(50);
        this.inputCantidad.setAlignment(Pos.CENTER);
        this.inputCantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputCantidad.setText("0");
            }
            newValue = inputCantidad.getText();
            if(parseInt(newValue) < 0 || parseInt(newValue) > CANTMAXIMAEJERCITOSTRANSFERIBLES){
                inputCantidad.setText("0");
            }
        });
        this.getChildren().add(this.etiquetaMenuReagrupacion);
        this.getChildren().add(this.botonMenuReagrupacion);
        this.getChildren().add(this.inputCantidad);
        this.setVisible(false);
    }

    public void establecerBotonesVisibles(Pais unPais) {
        this.botonCancelar.setVisible(false);
        this.botonMenuReagrupacion.setVisible(false);
        this.inputCantidad.setVisible(false);

        if(this.estadoActual.visibilizaDador(this.jugadorActual, unPais)){
            this.botonMenuReagrupacion.setVisible(true);
            this.botonMenuReagrupacion.setText("Realizar ataque desde aquí");
            this.inputCantidad.setVisible(true);
        }
        else if(this.estadoActual.visibilizaReceptor(this.jugadorActual, unPais)){
            this.botonMenuReagrupacion.setVisible(true);
            this.botonMenuReagrupacion.setText("Confirmar transferencia");
            this.botonCancelar.setVisible(true);
        }
    }

    public void aparecerMenu(MouseEvent evento, Pais unPais){
        String nombrePais = unPais.getNombre();
        this.paisActual = unPais;

        if (!estadoActual.paisOrigenIngresado())
        this.CANTMAXIMAEJERCITOSTRANSFERIBLES = unPais.getCantidadEjercitos()-1;

        this.setVisible(true);
        this.aparecer(evento.getSceneX(), evento.getSceneY());
        this.etiquetaMenuReagrupacion.setText(nombrePais);

        this.imagenDesdeAbajo.setVisible(false);
        this.imagenDesdeArriba.setVisible(false);
        this.imagenDesdeIzquierda.setVisible(false);

        establecerBotonesVisibles(unPais);
        if(evento.getSceneY() < 100){
            this.imagenDesdeArriba.setVisible(true);
        }
        else if(evento.getSceneX() < 100){
            this.imagenDesdeIzquierda.setVisible(true);
        }
        else{
            this.imagenDesdeAbajo.setVisible(true);
        }
    }

    public void ocultarMenu(MouseEvent evento){
        if(this.isVisible()){
            if(!this.adentro(evento.getSceneX(), evento.getSceneY())){
                this.setVisible(false);
            }
        }
    }

    public void aparecer( double mx, double my){
        if(my < 100){
            relocate(mx-100, my+10);
        }
        else if(mx < 100){
            relocate(mx, my-50);
        }
        else{
            relocate(mx-100, my-110);
        }
        this.puntoX = mx;
        this.puntoY = my;
    }

    public boolean adentro(double mx, double my){
        return(mx >= this.puntoX-100 && mx <= this.puntoX+100 && my >= this.puntoY-100 && my <= this.puntoY);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.jugadorActual = this.ronda.jugadorActual().getNumero();
    }
}
