package edu.fiuba.algo3.vista;


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

public class MenuReagrupar extends VistaMenuDesplegable implements Observer {
    private Label etiquetaMenuReagrupacion;
    private Button botonMenuReagrupacion;
    private Button botonCancelar;

    private TextField inputCantidad;

    private int jugadorActual;
    private Pais paisActual;

    private EstadoReagrupar estadoActual;
    private int cantidadMaximaTransferible;

    public MenuReagrupar(Ronda ronda) throws IOException {
        super(ronda, 0, 0);
        this.estadoActual = new EstadoReagrupar(ronda);
        this.jugadorActual = ronda.jugadorActual().getNumero();
        this.etiquetaMenuReagrupacion = new Label("");
        this.etiquetaMenuReagrupacion.setStyle("-fx-text-fill: #f2f2e9; -fx-font-size: 22; -fx-font-weight: bold;");
        this.etiquetaMenuReagrupacion.setTranslateY(-35);

        this.botonMenuReagrupacion = new Button("Mover ejercitos");
        this.botonMenuReagrupacion.setTranslateY(-10);
        this.botonMenuReagrupacion.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            this.estadoActual.agregarPais(this.paisActual, parseInt(this.inputCantidad.getText()));
            this.setVisible(false);
            e.consume();
        });

        this.botonCancelar = new Button("Cancelar reagrupacion");
        this.botonCancelar.setTranslateY(25);
        this.botonCancelar.setStyle("-fx-font-size: 10; -fx-background-color: #f2f2e9;");
        this.botonCancelar.setMaxWidth(120);
        this.botonCancelar.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            estadoActual.resetear();
            this.setVisible(false);
            e.consume();
        });

        this.inputCantidad = new TextField("0");
        this.inputCantidad.setTranslateY(25);
        this.inputCantidad.setPrefWidth(50);
        this.inputCantidad.setMaxWidth(50);
        this.inputCantidad.setAlignment(Pos.CENTER);
        this.inputCantidad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputCantidad.setText("0");
            }
            newValue = inputCantidad.getText();
            if(parseInt(newValue) < 0 || parseInt(newValue) > cantidadMaximaTransferible){
                inputCantidad.setText("0");
            }
        });
        this.getChildren().add(this.etiquetaMenuReagrupacion);
        this.getChildren().add(this.botonMenuReagrupacion);
        this.getChildren().add(this.botonCancelar);
        this.getChildren().add(this.inputCantidad);
    }

    public void establecerBotonesVisibles(Pais unPais) {
        this.botonCancelar.setVisible(false);
        this.botonMenuReagrupacion.setVisible(false);
        this.inputCantidad.setVisible(false);

        if(this.estadoActual.visibilizaDestino(this.jugadorActual, unPais)){
            this.botonMenuReagrupacion.setVisible(true);
            this.botonMenuReagrupacion.setText("Confirmar transferencia");
            this.botonCancelar.setVisible(true);
        }
        else if(this.estadoActual.visibilizaOrigen(this.jugadorActual, unPais)){
            this.botonMenuReagrupacion.setVisible(true);
            this.botonMenuReagrupacion.setText("Mover ejercitos");
            this.inputCantidad.setVisible(true);
        }
    }

    public void aparecerMenu(MouseEvent evento, Pais unPais){
        this.inputCantidad.setText("0");
        String nombrePais = unPais.getNombre();
        this.paisActual = unPais;

        this.cantidadMaximaTransferible = unPais.getCantidadEjercitos()-1;

        this.setVisible(true);
        this.aparecer(evento.getSceneX(), evento.getSceneY());
        this.etiquetaMenuReagrupacion.setText(nombrePais);

        establecerBotonesVisibles(unPais);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.jugadorActual = this.ronda.jugadorActual().getNumero();
        this.estadoActual.resetear();
    }
}
